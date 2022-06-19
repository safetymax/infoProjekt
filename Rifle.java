import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rifle extends Weapons {
    
    float x;
    float y;
    double direction;
    Bullet b[] = new Bullet[1000];
    int totalAmmo = 100;
    int magazine = 15;
    int frameCount = 0;
    SoundHandler mgs = new SoundHandler();
    boolean weaponShootable = false;
    double weaponCooldown = 0;
    public Rifle( int size){
        super(size);
        
        b = new Bullet[size];
        
        

    }
    public void updateWeapons(Player player, Boundary[] boundaries, int[][] collisions){
      x = player.posX;
      y = player.posY;
        direction = player.direction;
       frameCount++;
        for(int i = 0; i < b.length; i++){
            if(b[i] != null){
                b[i].moveBullet(player);
                b[i] = b[i].isOutOfBounds(b[i], boundaries, collisions);
            }
       }
       if (frameCount % 20 == (0 + weaponCooldown) && !weaponShootable) {
        weaponShootable = true;
    }
    }
    public void shoot(Boundary[] boundaries){
        
        
        if(weaponShootable){
            if(magazine >0){
                Bullet bullet = new Bullet((int)x,(int) y, direction, 4,8f);
                magazine--;
                for(int i = 0; i < b.length; i++){
                    if(b[i] == null){
                        b[i] = bullet;
                        break;
                    }
                }
    
                for(int i = 1; i < boundaries.length; i++){
                    if(boundaries[i] == null){
                        boundaries[i] = bullet;
                        break;
                    }
                }
                mgs.playSound("laserShoot",-1);
            } else{
                System.out.println("U empty");
                mgs.playSound("emptyMag",-1);
            }
            weaponShootable = false;
            weaponCooldown = frameCount%20;
        }
        
        
       
    }
    public void drawWeapons(Graphics2D g2d){
        for(int i = 0; i < b.length; i++){
            if(b[i] != null){
                b[i].drawBullet(g2d);
            }
        }
    }
    public void reload(){
        
        totalAmmo = totalAmmo -(15-magazine);
        magazine = 15;


        


    }


}
