import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Bullet extends Boundary{
    
    public double x;
    public double y;
    public double direction;
    public float speed;
    public float damage;
    public float[] lastPos;
    public Bullet(int x, int y, double direction, float s, float d, int type) {
        super(x, y ,x, y, x+5, y+5,type);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = s;
        this.damage = d;
        if(type == 10){
            lastPos = new float[2];
            lastPos[0] = x;
            lastPos[1] = y;
            



        }
    }
    public void moveBullet(Player player){
        double alpha;

        x +=  (Math.cos(direction) * speed);
        y +=  (Math.sin(direction) * speed);
        posX = (int) x;
        posY = (int) y;

        alpha = Math.atan((x-player.posX)/(y-player.posY));

        x1 = (int) (Math.cos(Math.PI-alpha) * (-32) + x);
        y1 = (int) (Math.sin(Math.PI-alpha) * (-32) + y);
        x2 = (int) (Math.cos(Math.PI-alpha) * 32 + x);
        y2 = (int) (Math.sin(Math.PI-alpha) * 32 + y);     
        
        
    }
    public void draw(Graphics2D g2d){
        g2d.setPaint(Color.RED);
        g2d.fillOval(x1, y1, x2, y2);

    }
    public void isHitByRay(int x, int y){
        
    }
    public void updatePosition(int x1, int y1, int x2, int y2){
       
    }
    public void drawBullet(Graphics2D g2d){
        
                g2d.setPaint(Color.RED);
                g2d.drawLine(x1, y1, x2, y2);
            
        
    }
    // 900 is window size
    public Bullet isOutOfBounds(Bullet b, Boundary[] boundaries, int[][] collisions){
        /*if(b.x < 0 || b.x > 900 || b.y < 0 || b.y > 900){
            destroyBullet(boundaries);
            return null;
        }*/
        
        if(x >= 800 || y >= 800 || x/64 < 0 || y/64 < 0) {
            destroyBullet(boundaries);
            
            return null;
        }else if(collisions[(int)y/64][(int)x/64] == 1) {
            destroyBullet(boundaries);
            
            return null;
        } else if(type == 10){
            if(Math.sqrt(Math.pow(x-lastPos[0],2) +Math.pow(y-lastPos[1],2)) > 50)  {
                destroyBullet(boundaries);
                System.out.println("plopp");
                return null;
                
            } else{
                return b;


            }
        }
        else{
            return b;
        } 
        
    }

    public void destroyBullet(Boundary[] boundaries){
        for(int i = 0; i < boundaries.length; i++){
            if(boundaries[i] == this){
                boundaries[i] = null;
                break;
            }
        }
    }
}
