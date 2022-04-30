import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


//Wall between two points
public class Wall extends Boundary{

    public int x1, y1, x2, y2;
    public int type;//1 = horizontal wall, 2 = vertical wall

    public Wall(int x1, int y1, int x2, int y2, int type) {
        super(x1, y1, x2, y2, type);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        g2d.drawLine(x1, y1, x2, y2);
    }

    //called when a Ray hits the wall
    public void isHitByRay(int x, int y){}
}