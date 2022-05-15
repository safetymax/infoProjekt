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
    
    public MachineGun( int size){
        super(size);
        
        b = new Bullet[size];
        
        

    }
    public void updateWeapons(Player player){
      x = player.posX;
      y = player.posY;
        direction = player.direction;
       
        for(int i = 0; i < b.length; i++){
            if(b[i] != null){
                b[i].moveBullet(player);
                b[i] = b[i].isOutOfBounds(b[i]);
            }
       }
    }
    public void shoot(Boundary[] boundaries){
        Bullet bullet = new Bullet((int)x,(int) y, direction, 5f,5f);
        for(int i = 0; i < b.length; i++){
            if(b[i] == null){
                b[i] = bullet;
                break;
            }
        }

        for(int i = 0; i < boundaries.length; i++){
            if(boundaries[i] != null){
                boundaries[i] = bullet;
                break;
            }
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
