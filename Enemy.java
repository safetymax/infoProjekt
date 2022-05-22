import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Enemy extends Boundary{
    
    public int type;//1 = horizontal wall, 2 = vertical wall
    public double speed;

    public Enemy(int x, int y, int type) {
        super(x,y, x, y, x+100, y+100, type);
        speed = 3.141;
    }

    public void move(Boundary[] boundaries, Player player){
        double alpha;
        double dist;

        dist = Math.sqrt(Math.pow(posY-player.posY, 2) + Math.pow(posX-player.posX, 2));
        if(dist > 64){
        alpha = Math.atan2((posY-player.posY),(posX-player.posX)) + Math.PI;
        posX += Math.cos(alpha)*speed;
        posY += Math.sin(alpha)*speed;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        //g2d.setPaint(new Color(60f/255f,60f/255f,60f/255f));
        g2d.setPaint(Color.BLUE);
        g2d.drawLine(x1, y1, x2, y2);
    }

    

    //called when a Ray hits the wall
    public void isHitByRay(int x, int y){}

    public void updatePosition(int x1, int y1, int x2, int y2){}
}
