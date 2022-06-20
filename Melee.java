import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Melee extends Weapons {
    
    float x;
    float y;
    double direction;
    Bullet b[] = new Bullet[1000];
    
    int frameCount = 0;
    SoundHandler mgs = new SoundHandler();
    boolean weaponShootable = false;
    double weaponCooldown = 0;
    public Melee( int size){
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
       if (frameCount % 100 == (0 + weaponCooldown) && !weaponShootable) {
        weaponShootable = true;
    }
    }
    public void shoot(Boundary[] boundaries){
        if(weaponShootable){
            Bullet bullet = new Bullet((int)x,(int) y, direction, 5f,5f, 10);
        
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
                mgs.playSound("melee",-1);
            
               
            
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
    


}
