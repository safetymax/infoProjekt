import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import javax.imageio.ImageIO;

public class Player {
    
    public float posX;
    public float posY;
    public double direction;

    public float speed;

    float[] currentPos;

    public int fov = 90;
    public Ray[] rays = new Ray[900];
    public double DV = (900/Math.tan(Math.toRadians(fov)/2));

    //DONT FORGET TO LOAD PIXELS INTO ARRAY
    File f1 = new File("wall.png");
    BufferedImage wallImage = null;
    int[][][] wallData = new int[64][64][4];

    File f2 = new File("Alien1.png");
    BufferedImage alien1Image = null;
    int[][][] alien1Data = new int[64][64][4];

    File f3 = new File("Bullet.png");
    BufferedImage bulletImage = null;
    int[][][] bulletData = new int[64][64][4];

    File f4 = new File("Alien2.png");
    BufferedImage alien2Image = null;
    int[][][] alien2Data = new int[64][64][4];

    public Player() {   
        posX = 350;
        posY = 400;
        direction = 0;
        speed = 2;

        currentPos = new float[2];

        //create Rays
        for(int i = 0; i < rays.length; i++) {
            rays[i] = new Ray(posX, posY, direction+Math.atan((i-rays.length/2)/DV));
        }

        
        
        //loading textures
        try{
            wallImage = ImageIO.read(f1);
            bulletImage = ImageIO.read(f3);
            alien1Image = ImageIO.read(f2);
            alien2Image = ImageIO.read(f4);


        }
        catch(Exception e){
            System.out.println("Error");
        }

        //Loading pixels into array
        for(int i = 0; i < wallImage.getWidth(); i++) {
            for(int j = 0; j < wallImage.getHeight(); j++) {
                wallData[i][j] = wallImage.getData().getPixel(i, j, (int[]) null);
                alien1Data[i][j] = alien1Image.getData().getPixel(i, j, (int[]) null);
                bulletData[i][j] = bulletImage.getData().getPixel(i, j, (int[]) null);
                alien2Data[i][j] = alien2Image.getData().getPixel(i, j, (int[]) null);

            }
        }
        
    }

    //Player move function
    public void move(boolean up, boolean down, boolean left, boolean right,boolean lookLeft, boolean lookRight, boolean sprint, int[][] map) {

        //Forwards and back
        if(up) {
            posX += Math.cos(direction) * speed;
            posY += Math.sin(direction) * speed;
        }
        if(down) {
            posX -= Math.cos(direction) * speed;
            posY -= Math.sin(direction) * speed;
        }
        //Left and right
        if(left) {
            posX += Math.cos(direction-Math.PI/2) * speed;
            posY += Math.sin(direction-Math.PI/2) * speed;
        }
        if(right) {
            posX += Math.cos(direction+Math.PI/2) * speed;
            posY += Math.sin(direction+Math.PI/2) * speed;
        }
        //Turning
        if(lookLeft) {
            direction -= 0.05f;
        }
        if(lookRight) {
            direction += 0.05f;
        }
        if(sprint) {
            speed = 4;
        }
        else {
            speed = 2;
        }

        //collision detection
        if((int)posY/64 >= map.length || (int)posX/64 >= map[0].length || (int)posY/64 < 0 || (int)posX/64 < 0) {
            posX = currentPos[0];
            posY = currentPos[1];
            System.out.println("Collision");
        }
        if(map[(int)posY/64][(int)posX/64] == 1) {
            //slide on walls / ignore one axis
            if(map[(int)currentPos[1]/64][(int)posX/64] == 0) {
                posY = currentPos[1];
            }
            else if(map[(int)posY/64][(int)currentPos[0]/64] == 0) {
                posX = currentPos[0];
            }
            else {
                posX = currentPos[0];
                posY = currentPos[1];
            }
        }
        if(map[(int)posY/64][(int)posX/64] == 0) {
            currentPos[0] = posX;
            currentPos[1] = posY;
        }

        for(int i = 0; i < rays.length; i++) {
            rays[i].update(posX, posY, direction+Math.atan((rays.length/2-i)/(450/Math.tan(Math.toRadians(fov)/2))));
        }
        

    }
    //shoot function
    public void shootKey(boolean shoot, Weapons w, Boundary[] boundaries, SoundHandler s) {

        if(shoot){
            w.shoot(boundaries);
            s.playSound("laserShoot");
        }

    }
    public void draw(Graphics2D g2d, boolean minimap) {

        //Draw Player on minimap
        if(minimap == true){
            g2d.setPaint(Color.WHITE);
            g2d.fillOval((int) posX-10, (int) posY-10, 20, 20);
        }
        
    }

    //Casts all Rays
    public void cast(Boundary[] boundaries, Graphics2D g2d, boolean minimap) {
        for(int i = 0; i < rays.length; i++) {

            //results: [0] = distance, [1] = x, [2] = y, [3] = index of closest boundary
            float[][] results = rays[i].cast(boundaries, g2d, minimap, i==0 || i==rays.length-1);
            try {
            for(int j = 0; j < results.length; j++){
                    float dist = results[j][0];

                    //calls isHitByRay() on hit Boundary
                    if(results[j][3] != -1){
                        boundaries[(int)results[j][3]].isHitByRay((int)results[j][1], (int)results[j][2]);
                    }

                    if(minimap == false){
                        //map the distance to a color
                        float colour = (float) dist/1000;
                        colour = 1 - colour;

                        if(colour < 0){
                            colour = 0;
                        }

                        //factor to counteract fish-eye effect
                        double correctionFactor = this.direction - rays[i].direction;
                        
                        //Renders pseudo 3d
                        if(results[j][3] != -1){
                            //The point on the wall mod 64
                            int pointOnWall = (int)Math.sqrt(Math.pow(results[j][1]-boundaries[(int)results[j][3]].x1,2) + Math.pow(results[j][2]-boundaries[(int)results[j][3]].y1,2))%64;
                            for(int k = 0; k < 64; k++){
                                if(boundaries[(int)results[j][3]].type == 1) {             
                                    g2d.setPaint(new Color(
                                    (float)(wallData[pointOnWall][k][0])/255*colour,
                                    (float)(wallData[pointOnWall][k][1])/255*colour,
                                    (float)(wallData[pointOnWall][k][2])/255*colour,
                                    (float)(wallData[pointOnWall][k][3])/255));
                                }
                                if(boundaries[(int)results[j][3]].type == 2) {             
                                    g2d.setPaint(new Color(
                                    (float)(bulletData[pointOnWall][k][0])/255*colour,
                                    (float)(bulletData[pointOnWall][k][1])/255*colour,
                                    (float)(bulletData[pointOnWall][k][2])/255*colour,
                                    (float)(bulletData[pointOnWall][k][3])/255));
                                }
                                if(boundaries[(int)results[j][3]].type == 3) {             
                                    g2d.setPaint(new Color(
                                    (float)(alien1Data[pointOnWall][k][0])/255*colour,
                                    (float)(alien1Data[pointOnWall][k][1])/255*colour,
                                    (float)(alien1Data[pointOnWall][k][2])/255*colour,
                                    (float)(alien1Data[pointOnWall][k][3])/255));
                                }
                                if(boundaries[(int)results[j][3]].type == 4) {             
                                    g2d.setPaint(new Color(
                                    (float)(alien2Data[pointOnWall][k][0])/255*colour,
                                    (float)(alien2Data[pointOnWall][k][1])/255*colour,
                                    (float)(alien2Data[pointOnWall][k][2])/255*colour,
                                    (float)(alien2Data[pointOnWall][k][3])/255));
                                }
                                // else if(boundaries[(int)results[j][3]].type == 2) {
                                //     g2d.setPaint(new Color((float)(wallData[(int)results[j][2]%64][k][0])/255*colour, (float)(wallData[(int)results[j][2]%64][k][1])/255*colour, (float)(wallData[(int)results[j][2]%64][k][2])/255*colour, (float)(wallData[(int)results[j][2]%64][k][3])/255));
                                // }
                                
                                //                                     pixels/distance*correctionFactor*projection plane distance
                                g2d.drawLine(rays.length-i, (int) (450-((64-k*2)*DV/4)/(dist*Math.cos(correctionFactor))), rays.length-i, (int) (450-((64-(k+1)*2)*DV/4)/(dist*(Math.cos(correctionFactor)))));
                            }
                        }
                    }

                }
            }
            catch(Exception e){
                System.out.println("Error: " + e);
            }
        }
    }

}
