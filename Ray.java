import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ray {

    float x, y;
    double direction;
    KeyHandler keyH = new KeyHandler();
    
    public Ray(float x, float y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public void update(float x, float y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int[] cast(Boundary[] boundaries, Graphics2D g2d, boolean minimap) {
        int[] result = new int[4];
        result[1] = -1;
        result[2] = -1;
        float distance = 1000;

        float closestDistance = distance;
        result[0] = (int) closestDistance;
        int closestIndex = -1;
        int record1 = -1;
        int record2 = -1;

        for(int i = 0; i < boundaries.length; i++){
            if(boundaries[i] != null){
                float x1 = boundaries[i].x1;
                float y1 = boundaries[i].y1;
                float x2 = boundaries[i].x2;
                float y2 = boundaries[i].y2;
                float x3 = x;
                float y3 = y;
                float x4 = x + (float)Math.cos(direction) * distance*-1;
                float y4 = y + (float)Math.sin(direction) * distance*-1;

                //Explain: http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect
                float denom = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
                float t = ((x1-x3)*(y3-y4) - (y1-y3)*(x3-x4)) / denom;
                float u = ((x1-x2)*(y1-y3) - (y1-y2)*(x1-x3)) / denom;

                if(t > 0 && t < 1 && u > 0){
                    float ix = x1 + t*(x2-x1);
                    float iy = y1 + t*(y2-y1);
                    result[0] = (int) ix;
                    result[1] = (int) iy;               
                  
                }

                if(result[0]!=-1&&result[1]!=-1){
                    float distanceToWall = (float)Math.sqrt(Math.pow(result[0]-x, 2) + Math.pow(result[1]-y, 2));
                    if(distanceToWall < closestDistance){
                        closestDistance = distanceToWall;
                        record1 = result[0];
                        record2 = result[1];
                        closestIndex = i;
                    }
                }
            }
        }

        //draw the ray in minimap form
        g2d.setPaint(Color.WHITE);
            if(minimap){
                if(closestDistance != distance){
                    g2d.drawLine((int)x, (int)y, record1, record2);
                }
                else{
                    g2d.drawLine((int)x, (int)y, (int) ((int)x + (float)Math.cos(direction) * distance), (int) ((int)y + (float)Math.sin(direction) * distance));
                }
            }
        

        result[0] = (int) closestDistance;
        result[1] = record1;
        result[2] = record2;
        result[3] = closestIndex;

        return result;
    }

}
