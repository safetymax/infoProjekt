import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MachineGun extends Weapons {
    
    float x;
    float y;
    double direction;
    Bullet b[] = new Bullet[1000];
    
    static int magazine = 25;
    int frameCount = 0;
    SoundHandler mgs = new SoundHandler();
    boolean weaponShootable = false;
    double weaponCooldown = 0;
    public MachineGun( int size){
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
    public void shoot(Boundary[] sprites){
        if(weaponShootable){
        
        if(magazine >0){
            Bullet bullet = new Bullet((int)x,(int) y, direction, 5f,5f, 2);
            magazine--;
            for(int i = 0; i < b.length; i++){
                if(b[i] == null){
                    b[i] = bullet;
                    break;
                }
            }
    
                for(int i = 1; i < sprites.length; i++){
                    if(sprites[i] == null){
                        sprites[i] = bullet;
                        break;
                    }
                }
                mgs.playSound("laserShoot",-1);
            } else{
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
        
        
        magazine = 25;


        


    }


}
