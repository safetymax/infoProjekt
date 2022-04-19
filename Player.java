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
    public Ray[] rays = new Ray[900];

    public Player() {   
        posX = 350;
        posY = 350;
        direction = 0;
        speed = 2;

        //create Rays
        for(int i = 0; i < rays.length; i++) {
            rays[i] = new Ray(posX, posY, direction+Math.toRadians(fov)/2-Math.toRadians(fov)/(rays.length-1)*i);
        }
    }

    //Player move function
    public void move(boolean up, boolean down, boolean left, boolean right) {
        //Forwards and back
        if(up) {
            posX += Math.cos(direction) * speed;
            posY += Math.sin(direction) * speed;
        }
        if(down) {
            posX -= Math.cos(direction) * speed;
            posY -= Math.sin(direction) * speed;
        }
        //Turning
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

        //Draw Player on minimap
        if(minimap == true){
            g2d.setPaint(Color.WHITE);
            g2d.fillOval((int) posX-10, (int) posY-10, 20, 20);
        }
        
    }

    //Casts all Rays
    public void cast(Boundary[] boundaries, Graphics2D g2d, boolean minimap) {
        for(int i = 0; i < rays.length; i++) {

            //results: [0] = distance, [1] = x, [2] = y, [3] = index of closest boundary
            int[] results = rays[i].cast(boundaries, g2d, minimap);
            int dist = results[0];

            //calls isHitByRay() on hit Boundary
            if(results[3] != -1){
                boundaries[results[3]].isHitByRay(results[1], results[2]);
            }

            if(minimap == false){
                //map the distance to a color
                float colour = (float) dist/1000;
                colour = 1 - colour;
                g2d.setPaint(new Color(colour, colour, colour));

                //factor to counteract fish-eye effect
                double correctionFactor = this.direction - rays[i].direction;
                
                //Renders pseudo 3d
                g2d.setPaint(new Color(colour, colour, colour));
                g2d.drawLine(rays.length-i, (int) (450-30000/(dist*Math.cos(correctionFactor))), rays.length-i, (int) (450+30000/(dist*(Math.cos(correctionFactor)))));
            }
        }
    }



}
