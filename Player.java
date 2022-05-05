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

    File f1 = new File("wall.png");
    BufferedImage wallImage = null;
    int[][][] wallData = new int[64][64][3];

    public Player() {   
        posX = 350;
        posY = 400;
        direction = 0;
        speed = 2;

        currentPos = new float[2];

        //create Rays
        for(int i = 0; i < rays.length; i++) {
            rays[i] = new Ray(posX, posY, direction+Math.toRadians(fov)/2-Math.toRadians(fov)/(rays.length-1)*i);
        }

        //loading textures
        try{
            wallImage = ImageIO.read(f1);
        }
        catch(Exception e){
            System.out.println("Error");
        }

        for(int i = 0; i < wallImage.getWidth(); i++) {
            for(int j = 0; j < wallImage.getHeight(); j++) {
                wallData[i][j] = wallImage.getData().getPixel(i, j, (int[]) null);
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
            posX = currentPos[0];
            posY = currentPos[1];
        }
        else{
            currentPos[0] = posX;
            currentPos[1] = posY;
        }

        for(int i = 0; i < rays.length; i++) {
            rays[i].update(posX, posY, direction+Math.toRadians(fov)/2-Math.toRadians(fov)/(rays.length-1)*i);
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
            float[] results = rays[i].cast(boundaries, g2d, minimap, i==0 || i==rays.length-1);
            float dist = results[0];

            //calls isHitByRay() on hit Boundary
            if(results[3] != -1){
                boundaries[(int)results[3]].isHitByRay((int)results[1], (int)results[2]);
            }

            if(minimap == false){
                //map the distance to a color
                float colour = (float) dist/1000;
                colour = 1 - colour;

                //factor to counteract fish-eye effect
                double correctionFactor = this.direction - rays[i].direction;
                
                //Renders pseudo 3d
                if(results[3] != -1){
                    for(int j = 0; j < 64; j++){
                        if(boundaries[(int)results[3]].type == 1) {             
                            g2d.setPaint(new Color((float)(wallData[(int)results[1]%64][j][0])/255*colour, (float)(wallData[(int)results[1]%64][j][1])/255*colour, (float)(wallData[(int)results[1]%64][j][2])/255*colour));
                        }
                        else if(boundaries[(int)results[3]].type == 2) {
                            g2d.setPaint(new Color((float)(wallData[(int)results[2]%64][j][0])/255*colour, (float)(wallData[(int)results[2]%64][j][1])/255*colour, (float)(wallData[(int)results[2]%64][j][2])/255*colour));
                        }
                        
                        //                                     pixels/distance*correctionFactor*projection plane distance
                        g2d.drawLine(rays.length-i, (int) (450-(64-j*2)/(dist*Math.cos(correctionFactor))*277), rays.length-i, (int) (450-(64-(j+1)*2)/(dist*(Math.cos(correctionFactor)))*277));
                     }
                }


            }
        }
    }



}
