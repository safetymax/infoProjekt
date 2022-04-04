import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Player {
    
    public float posX;
    public float posY;
    public double direction;

    public float speed;

    public Player() {   
        posX = 0;
        posY = 0;
        direction = 0;
        speed = 1;
    }


    public void move(boolean up, boolean down, boolean left, boolean right) {
        if(up) {
            posX += Math.cos(direction) * speed;
            posY += Math.sin(direction) * speed;
        }
        if(down) {
            posX -= Math.cos(direction) * speed;
            posY -= Math.sin(direction) * speed;
        }
        if(left) {
            direction -= 0.05f;
        }
        if(right) {
            direction += 0.05f;
        }
    }

}
