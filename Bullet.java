import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Bullet extends Boundary{
    
    public int x;
    public int y;
    public double direction;
    public float speed;
    public float damage;

    public Bullet(int x, int y, double direction, float s, float d) {
        super(x, y, x+5, y+5,3);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = s;
        this.damage = d;
    }
    public void moveBullet(){
        x += (int) (Math.cos(direction) * speed);
        y += (int) (Math.sin(direction) * speed);
        
    }
    public void draw(Graphics2D g2d){
        g2d.setPaint(Color.RED);
        g2d.fillOval(x, y, 5, 5);

    }
    public void isHitByRay(int x, int y){
        
    }
    public void updatePosition(int x1, int y1, int x2, int y2){
       
    }
    public void drawBullet(Graphics2D g2d){
        
                g2d.setPaint(Color.RED);
                g2d.fillOval((int) x,(int) y, 5, 5);
            
        
    }
    // 900 is window size
    public Bullet isOutOfBounds(Bullet b){
        if(b.x < 0 || b.x > 900 || b.y < 0 || b.y > 900){
            
            return null;
        }
        else{
            return b;
        }

    }
}
