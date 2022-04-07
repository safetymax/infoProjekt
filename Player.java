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

    public int fov = 90;
    public Ray[] rays = new Ray[700];

    public Player() {   
        posX = 0;
        posY = 0;
        direction = 0;
        speed = 2;

        for(int i = 0; i < rays.length; i++) {
            rays[i] = new Ray(posX, posY, direction+Math.toRadians(fov)/2-Math.toRadians(fov)/(rays.length-1)*i);
        }
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

        for(int i = 0; i < rays.length; i++) {
            rays[i].update(posX, posY, direction+Math.toRadians(fov)/2-Math.toRadians(fov)/(rays.length-1)*i);
        }

    }

    public void draw(Graphics2D g2d, boolean minimap) {
        if(minimap == true){
            g2d.setPaint(Color.WHITE);
            g2d.fillOval((int) posX-10, (int) posY-10, 20, 20);
        }
        
    }

    public void cast(Boundary[] boundaries, Graphics2D g2d, boolean minimap) {
        for(int i = 0; i < rays.length; i++) {
            float dist = rays[i].cast(boundaries, g2d, minimap);
            if(minimap == false){
                float colour = 1-(dist/1000);
                //factor to counteract fish-eye effect
                double correctionFactor = this.direction - rays[i].direction;
                
                g2d.setPaint(new Color(colour, colour, colour));
                g2d.drawLine(rays.length-i, (int) (350-30000/(dist*Math.cos(correctionFactor))), rays.length-i, (int) (350+30000/(dist*(Math.cos(correctionFactor)))));
            }
        }
    }



}
