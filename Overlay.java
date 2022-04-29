import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;

public class Overlay{
    private int downA,enterA;
    private boolean a,b,c,d,e,f,g,h,i,j;

    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();
    Button button5 = new Button();

    public void draw(Graphics2D g2d, Boolean down,Boolean right,Boolean up, Boolean left,boolean enter){
        if(enter && !i && !j){
            i = true;
            j = true;
        }
        if(i){
            enterA = downA + 1;
        }
        if(enter && j){
            i = false;
        }
        if(!enter){
            j = false;
        }
        if(enterA <= 0){
            enterA = 0;
        }
        if(enterA == 0){
            Image img1 = Toolkit.getDefaultToolkit().getImage("Bild.png");
            g2d.drawImage(img1, 0, 0, null);
            button1.draw(g2d,300,170,300,45,400, "New Game" );
            button2.draw(g2d,300,250,300,45,425, "Load" );
            button3.draw(g2d,300,330,300,45,405, "Options");
            button4.draw(g2d,300,410,125,45,310, "Credits");
            button5.draw(g2d,475,410,125,45,510, "Quit");
        
            if(down && !a && !b){
                a = true;
                b = true;
            }
            if(a){
                downA++;
            }
            if(down && b){
                a = false;
            }
            if(!down){
                b = false;
            }
            if(downA == 4 && up){
                downA = 2;
                d=true;
            }
            if(up && !c && !d  ){
                c = true;
                d = true;
            }
            if(c){
                downA--;
            }
            if(up && d){
                c = false;
            }
            if(!up){
                d = false;
            }
            if(right && !e && !f){
                e = true;
                f = true;
            }
            if(e){
                downA++;
            }
            if(right && f){
                e = false;
            }
            if(!right){
                f = false;
            }
            if(left && !g && !h){
                g = true;
                h = true;
            }
            if(g){
                downA--;
            }
            if(left && h){
                g = false;
            }
            if(!left){
                h = false;
            }
            if(downA >= 4){
                downA = 4;
            }
            if(downA <= 0){
                downA = 0;
            }
            if(downA == 0){ 
                button1.selectedChange(g2d,300,170,300,45);
            }else if(downA == 1){
                button2.selectedChange(g2d, 300,250,300,45);
            }else if(downA == 2){
                button3.selectedChange(g2d, 300,330,300,45);
            }else if(downA == 3){
                button3.selectedChange(g2d, 300,410,125,45);
            }else if(downA == 4){
                button3.selectedChange(g2d,475 ,410,125,45);
            }
        }
        
        if(enterA == 5){
            System.exit(0);
        }
    }
}








    
    
