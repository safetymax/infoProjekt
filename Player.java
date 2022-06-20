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
    public boolean isFinished = false;
    public float speed;
    public boolean touchedDoor = false;
    float[] currentPos;
    float[] lastPos;
    public int fov = 90;
    public Ray[] rays = new Ray[900];
    public double DV = (900/Math.tan(Math.toRadians(fov)/2));
    public double DVprime;

    public double correctionFactor;
    public double alpha;
    public double[][] hypotenuse = new double[900][905];

    public static int health;
    MachineGun mg = new MachineGun(150);
    Rifle rfl = new Rifle(150);
    Melee ml = new Melee(150);
    int weaponType;
    float[] damage;
    SoundHandler ps = new SoundHandler();
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

    File f5 = new File("closedDoor.png");
    BufferedImage closedDoorImage = null;
    int[][][] closedDoorData = new int[64][64][4];

    File f6 = new File("openDoor.png");
    BufferedImage openDoorImage = null;
    int[][][] openDoorData = new int[64][64][4];

    File f7 = new File("floor.png");
    BufferedImage floorImage = null;
    int[][][] floorData = new int[64][64][4];

    File f8 = new File("ceiling.png");
    BufferedImage ceilingImage = null;
    int[][][] ceilingData = new int[64][64][4];

    File f9 = new File("bullet2.png");
    BufferedImage bullet2Image = null;
    int[][][] bullet2Data = new int[64][64][4];

    File f10 = new File("nothing.png");
    BufferedImage nothingImage = null;
    int[][][] nothingData = new int[64][64][4];

    File f11 = new File("boss1.png");
    BufferedImage boss1Image = null;
    int[][][] boss1Data = new int[64][64][4];

    File f12 = new File("bullet3.png");
    BufferedImage bullet3Image = null;
    int[][][] bullet3Data = new int[64][64][4];

    File f13 = new File("boss2.png");
    BufferedImage boss2Image = null;
    int[][][] boss2Data = new int[64][64][4];

    File f14 = new File("bullet4.png");
    BufferedImage bullet4Image = null;
    int[][][] bullet4Data = new int[64][64][4];

    int[][][][] textureData = new int[256][64][64][4];


    int[] ZBuffer = new int[900];
    float[] spriteAngle = new float[2048];
    float[] spriteDistance = new float[2048];
    int[] spriteOrder = new int[2048];


    public Player() {   
        posX = 350;
        posY = 400;
        direction = 1;
        speed = 2;

        currentPos = new float[2];
        lastPos = new float[2];
        lastPos[0] = 10;
        lastPos[1] = 10;
        
        health = 10;
        //0 = type 10; 1 = type 11;
        damage = new float[]{1,1,1};
        weaponType = 1;
        //create Rays
        for(int i = 0; i < rays.length; i++) {
            rays[i] = new Ray(posX, posY, direction+Math.atan((rays.length/2-i)/(450/Math.tan(Math.toRadians(fov)/2))));
        }

        
        
        //loading textures
        try{
            wallImage = ImageIO.read(f1);
            bulletImage = ImageIO.read(f3);
            alien1Image = ImageIO.read(f2);
            alien2Image = ImageIO.read(f4);
            closedDoorImage = ImageIO.read(f5);
            openDoorImage = ImageIO.read(f6);
            floorImage = ImageIO.read(f7);
            ceilingImage = ImageIO.read(f8);
            bullet2Image = ImageIO.read(f9);
            nothingImage = ImageIO.read(f10);
            boss1Image = ImageIO.read(f11);
            bullet3Image = ImageIO.read(f12);
            boss2Image = ImageIO.read(f13);
            bullet4Image = ImageIO.read(f14);
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
                closedDoorData[i][j] = closedDoorImage.getData().getPixel(i, j, (int[]) null);
                openDoorData[i][j] = openDoorImage.getData().getPixel(i, j, (int[]) null);
                floorData[i][j] = floorImage.getData().getPixel(i, j, (int[]) null);
                ceilingData[i][j] = ceilingImage.getData().getPixel(i, j, (int[]) null);
                bullet2Data[i][j] = bullet2Image.getData().getPixel(i, j, (int[]) null);
                nothingData[i][j] = nothingImage.getData().getPixel(i, j, (int[]) null);
                boss1Data[i][j] = boss1Image.getData().getPixel(i, j, (int[]) null);
                bullet3Data[i][j] = bullet3Image.getData().getPixel(i, j, (int[]) null);
                boss2Data[i][j] = boss2Image.getData().getPixel(i, j, (int[]) null);
                bullet4Data[i][j] = bullet4Image.getData().getPixel(i, j, (int[]) null);
            }
        }

        //Loading textures into array
        textureData[1] = wallData;
        textureData[2] = bulletData;
        textureData[3] = alien1Data;
        textureData[4] = alien2Data;
        textureData[5] = closedDoorData;
        textureData[6] = openDoorData;
        textureData[7] = floorData;
        textureData[8] = ceilingData;
        textureData[9] = bullet2Data;
        textureData[10] = nothingData;
        textureData[11] = boss1Data;
        textureData[12] = bullet3Data;
        textureData[13] = boss2Data;
        textureData[14] = bullet4Data;


        
    for(int i = 0; i < 900; i++){
        DVprime = Math.sqrt(Math.pow(DV,2)+Math.pow(i-450,2));
        correctionFactor = this.direction - rays[i].direction;
        for(int y = 450; y < 900; y++){
            alpha = Math.atan((y-449)/DVprime);
            hypotenuse[i][y] = Math.abs(16/(Math.tan(alpha)*Math.cos(correctionFactor)));
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
        if(map[(int)posY/64][(int)posX/64] == 2 && isFinished) {
            currentPos[0] = posX;
            currentPos[1] = posY;
            touchedDoor = true;
        } else if(map[(int)posY/64][(int)posX/64] == 2 && !isFinished) {
            posX = currentPos[0];
            posY = currentPos[1];
        }
        for(int i = 0; i < rays.length; i++) {
            rays[i].update(posX, posY, direction+Math.atan((rays.length/2-i)/(450/Math.tan(Math.toRadians(fov)/2))));
        }
        
        
        float distX = posX - lastPos[0];
        float distY = posY - lastPos[1];

        if(Math.sqrt(Math.pow(distX,2) +Math.pow(distY,2)) > 50)  {
            lastPos[0] = posX;
            lastPos[1] = posY;
            ps.playSound("playerWalking", -1);
            

        }
        
    }
    //shoot function
    public void shootKey(boolean shoot, Boundary[] sprites) {

        if(shoot){
            if(weaponType ==1){
                mg.shoot(sprites, -1);
            } else if(weaponType == 2){
                rfl.shoot(sprites, -1);
            } else if(weaponType == 3){
                ml.shoot(sprites, -1);
            }
            
               
           
        }

    }
    public void reload(boolean reload) {
        if(reload) {
            mg.reload();
            rfl.reload();
        }
    }
    public void draw(Graphics2D g2d, boolean minimap) {

        //Draw Player on minimap
        if(minimap == true){
            g2d.setPaint(Color.WHITE);
            g2d.fillOval((int) (posX-10)/6, (int) (posY-10)/6, 5, 5);
            mg.drawWeapons(g2d);
        }
        
    }

    //Casts all Rays
    public void cast(Boundary[] boundaries,Boundary[] sprites, Graphics2D g2d) {
        for(int i = 0; i < rays.length; i++) {

            //results: [0] = distance, [1] = x, [2] = y, [3] = index of closest boundary
            float[] results = rays[i].cast(boundaries, g2d, i==0 || i==rays.length-1);
            //try {
                    float dist = results[0];

                        //map the distance to a color
                        float colour = (float) dist/1000;
                        colour = 1 - colour;

                        if(colour < 0 || colour > 1){
                            colour = 0;
                        }

                        //factor to counteract fish-eye effect
                        double correctionFactor = this.direction - rays[i].direction;
                        
                        //Renders pseudo 3d
                        if(results[3] != -1){
                            ZBuffer[899-i] = (int)dist;
                            //The point on the wall mod 64
                            int pointOnWall = (int)Math.sqrt(Math.pow(results[1]-boundaries[(int)results[3]].x1,2) + Math.pow(results[2]-boundaries[(int)results[3]].y1,2))%64;
                            for(int k = 0; k < 64; k++){
                                g2d.setPaint(new Color(
                                (float)(textureData[boundaries[(int)results[3]].type][pointOnWall][k][0])/255*colour,
                                (float)(textureData[boundaries[(int)results[3]].type][pointOnWall][k][1])/255*colour,
                                (float)(textureData[boundaries[(int)results[3]].type][pointOnWall][k][2])/255*colour));
                                
                                // else if(boundaries[(int)results[3]].type == 2) {
                                //     g2d.setPaint(new Color((float)(wallData[(int)results[j][2]%64][k][0])/255*colour, (float)(wallData[(int)results[j][2]%64][k][1])/255*colour, (float)(wallData[(int)results[j][2]%64][k][2])/255*colour, (float)(wallData[(int)results[j][2]%64][k][3])/255));
                                // }
                                
                                //                                     pixels/distance*correctionFactor*projection plane distance
                                g2d.drawLine(rays.length-i, (int) (450-((64-k*2)*DV/4)/(dist*Math.cos(correctionFactor))), rays.length-i, (int) (450-((64-(k+1)*2)*DV/4)/(dist*(Math.cos(correctionFactor)))));
                            }

                        }

                        //floor / ceiling
                        if(results[3] == -1){
                            dist = 1000;
                        }                         
                        int wallHeight = (int)(450+(64*DV/4)/(dist*Math.cos(correctionFactor)));
                        for(int y = wallHeight; y <= 904; y+=4){
                            double xFloor = hypotenuse[i][y]*Math.cos(rays[i].direction) + posX;
                            double yFloor = hypotenuse[i][y]*Math.sin(rays[i].direction) + posY;

                            colour = (float) hypotenuse[i][y]/1000;
                            colour = 1 - colour;

                            if(colour < 0 || colour > 1){
                                colour = 0;
                            }

                            if(xFloor < 0){
                                xFloor = 64 - xFloor%64;
                            }
                            if(yFloor < 0){
                                yFloor = 64 - yFloor%64;
                            }
                            xFloor %= 64;
                            yFloor %= 64;

                            //floor texture
                            g2d.setPaint(new Color(
                            (float)(textureData[7][(int)xFloor][(int)yFloor][0])/255*colour,
                            (float)(textureData[7][(int)xFloor][(int)yFloor][1])/255*colour,
                            (float)(textureData[7][(int)xFloor][(int)yFloor][2])/255*colour));
                            
                            g2d.drawLine(rays.length-i, y, rays.length-i, y+5);

                            //ceiling texture
                            g2d.setPaint(new Color(
                            (float)(textureData[8][(int)xFloor][(int)yFloor][0])/255*colour,
                            (float)(textureData[8][(int)xFloor][(int)yFloor][1])/255*colour,
                            (float)(textureData[8][(int)xFloor][(int)yFloor][2])/255*colour));

                            g2d.drawLine(rays.length-i, 900-y, rays.length-i, 900-y+5);
                        }
            //}
            /*catch(Exception e){
                System.out.println("Error: " + e);
                System.exit(0);
            }*/
        }

        for(int i = 0; i < sprites.length; i++){
            if(sprites[i] != null){
                spriteAngle[i] = (float)(Math.atan2(sprites[i].posY-posY, sprites[i].posX-posX) + Math.PI);
                spriteDistance[i] = (float)Math.abs((sprites[i].posY-posY)/Math.sin(spriteAngle[i]));
                spriteOrder[i] = i;
            }
        }
        sortSprites(spriteDistance, spriteOrder);

        //sprites
        for(int i = 0; i < spriteOrder.length; i++){
            int ii = spriteOrder[i];
            if(sprites[ii] != null){
                double alpha = spriteAngle[ii];
                double radius = spriteDistance[ii];//Math.abs((sprites[i].posY-posY)/Math.sin(alpha));
                double dir = (direction%(Math.PI*2));
                if(dir < 0){
                    dir = Math.PI*2 + dir;
                }
                // if(posY>sprites[i].posY){
                //     alpha += Math.PI*2;
                // }
                int a = 1;
                if(posY < sprites[ii].posY && alpha > dir){
                    a = -1;
                }
                if(alpha-dir+a*Math.PI > Math.PI){
                    alpha -= Math.PI*2;
                }
                double offset = (int)((alpha-dir+a*Math.PI) / Math.toRadians(fov)*900+450);

                if(alpha-dir+a*Math.PI > -Math.toRadians(fov)/2 && alpha-dir+a*Math.PI < Math.toRadians(fov)/2){
                    for(int x = 0; x<64; x++){
                        int offsetX = (int) (450-((64-(x)*2)*DV/4)/(radius)-450+offset);
                        if(offsetX < 900 && offsetX >= 0 && ZBuffer[offsetX] > radius){
                            for(int y = 0; y<64; y++){
                                if(x+offset<900 && x+offset>0){
                                    g2d.setPaint(new Color(
                                    (float)(textureData[sprites[ii].type][x][y][0])/255,
                                    (float)(textureData[sprites[ii].type][x][y][1])/255,
                                    (float)(textureData[sprites[ii].type][x][y][2])/255,
                                    (float)(textureData[sprites[ii].type][x][y][3])/255));
                                    g2d.fillRect(offsetX, (int) (450-((64-y*2)*DV/4)/(radius)), (int)((450-((64-(x+1)*2)*DV/4)/(radius))-(450-((64-x*2)*DV/4)/(radius)))+1, (int)((450-((64-(y+1)*2)*DV/4)/(radius))-(450-((64-y*2)*DV/4)/(radius)))+1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void sortSprites(float[] spriteD, int[] spriteO){
        for(int i = 0; i < spriteO.length-1; i++){
            for(int j = 0; j < spriteO.length-1; j++){
                if(spriteD[spriteOrder[j]] < spriteD[spriteO[j+1]]){
                    int temp = spriteO[j];
                    spriteO[j] = spriteO[j+1];
                    spriteO[j+1] = temp;
                }
            }
        }
    }
    
    public void updatePlayer(Boundary[] sprites, int[][] collisions){
        mg.updateWeapons(this, sprites, collisions);
        rfl.updateWeapons(this, sprites, collisions);
        ml.updateWeapons(this, sprites, collisions);
        takeDamage(sprites, this);

    }
    public void takeDamage(Boundary[] sprites, Player player) {
        
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] != null) {
                
                
                if (sprites[i].type == 10 || sprites[i].type == 11 || sprites[i].type == 12) {
                    int disttoBullet = (int) Math.sqrt(
                            Math.pow(posX - sprites[i].posX, 2) + Math.pow(posY - sprites[i].posY, 2));
                    if (disttoBullet < 32) {
                        
                        health--;
                        sprites[i] =null;
                        

                    }
                    
                        

                    }
                } 
            }
            
            if (health <= 0) {
                System.out.println("u ded");
                System.exit(0);

            } 
            
       
        }
    }

    
    

