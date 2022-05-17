import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Enemy extends Boundary{

    public int posX, posY;
    
    public int type;//1 = horizontal wall, 2 = vertical wall

    public Enemy(int x, int y, int type) {
        super(x, y, x+100, y+100, type);
        posX = x;
        posY = y;
    }

    public void move(Boundary[] boundaries, Player player){
        x1 = posX;
        double alpha;

        alpha = Math.atan((posX-player.posX)/(posY-player.posY));

        x1 = (int) (Math.cos(Math.PI-alpha) * (-32) + posX);
        y1 = (int) (Math.sin(Math.PI-alpha) * (-32) + posY);
        x2 = (int) (Math.cos(Math.PI-alpha) * 32 + posX);
        y2 = (int) (Math.sin(Math.PI-alpha) * 32 + posY);
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
