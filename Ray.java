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

    public float[][] cast(Boundary[] boundaries, Graphics2D g2d, boolean minimap, boolean drawRay) {
        float[][] allResults = new float[1][4];
        int count = 0;
        float distance = 1000;

        for(int i = 0; i < allResults.length; i++) {
            allResults[i][0] = -1;
            allResults[i][1] = -1;
            allResults[i][2] = -1;
            allResults[i][3] = -1;
        }

        float closestDistance = distance;
        float closestIndex = -1;
        float record1 = -1;
        float record2 = -1;

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
                    float distanceToWall = (float)Math.abs((ix-x)/Math.cos(direction));
                    if(distanceToWall < closestDistance){
                        allResults[count][0] = (float)Math.abs((ix-x)/Math.cos(direction));
                        allResults[count][1] = ix;
                        allResults[count][2] = iy;
                        allResults[count][3] = i;
                        closestDistance = distanceToWall;
                    }
                    
                    if(count<allResults.length-1){
                        count++;
                    }             
                }
            }
        }

        //draw the ray in minimap form
        g2d.setPaint(Color.WHITE);
            if(minimap && drawRay){
                if(closestDistance != distance){
                    g2d.drawLine((int)x, (int)y, (int)allResults[0][1], (int)allResults[0][2]);
                }
                else{
                    g2d.drawLine((int)x, (int)y, (int) ((int)x + (float)Math.cos(direction) * distance), (int) ((int)y + (float)Math.sin(direction) * distance));
                }
            }

        //allResults[0] = result;

        return allResults;
    }

}
