import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoxThrower extends Weapons {
    
    float x;
    float y;
    double direction;
    public Bullet b[] = new Bullet[1000];
    
    int frameCount = 0;
    SoundHandler mgs = new SoundHandler();
    boolean weaponShootable = false;
    double weaponCooldown = 0;
    public BoxThrower( int size){
        super(size);
        
        b = new Bullet[size];
        
        

    }
    public void updateWeapons(Player player, Boundary[] sprites, int[][] collisions){
      
    }
    
    public void shoot(Boundary[] boundaries,int type){
        if(weaponShootable){
            Bullet bullet = new Bullet((int)x,(int) y, direction, 3f,5f, type);
            Bullet bullet2 = new Bullet((int)x,(int) y, direction+(Math.PI/5), 3f,5f, type);
            Bullet bullet3 = new Bullet((int)x,(int) y, direction-(Math.PI/5), 3f,5f, type);
            for(int i = 0; i < b.length-2; i++){
                if(b[i] == null && b[i+1] == null&& b[i+2] == null ){
                    b[i] = bullet;
                    b[i+1] = bullet2;
                    b[i+2] = bullet3;
                    break;
                }
            }
    
                for(int i = 1; i < boundaries.length-2; i++){
                    if(boundaries[i] == null && boundaries[i+1] == null && boundaries[i+2] ==null){
                        boundaries[i] = bullet;
                        boundaries[i+1] = bullet2;
                        boundaries[i+2] = bullet3;
                        break;
                    }
                }
                mgs.playSound("melee",-1);
            
               
            
            weaponShootable = false;
            weaponCooldown = frameCount%85;
        
            } 
        } 
    public void drawWeapons(Graphics2D g2d){
        for(int i = 0; i < b.length; i++){
            if(b[i] != null){
                b[i].drawBullet(g2d);
            }
        }
    }
    public void updateWeaponsEnemy(Enemy enemy,Player player, Boundary[] sprites, int[][] collisions){
        x = enemy.posX;
        y = enemy.posY;
          direction = enemy.alpha;
          frameCount++;
          
          for(int i = 0; i < b.length; i++){
              if(b[i] != null){
                  b[i].moveBullet(player);
                  b[i] = b[i].isOutOfBounds(b[i], sprites, collisions);
              }
         }
         if (frameCount % 85 == (0 + weaponCooldown) && !weaponShootable) {
          weaponShootable = true;
      }
      }


}
