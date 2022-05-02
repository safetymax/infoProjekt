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
    
    private boolean a,b,c,d,e,f,g,h,i,j,k,l;
    Image img1 = Toolkit.getDefaultToolkit().getImage("Bild.png");
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();
    Button button5 = new Button();
    Button button6 = new Button(); 
    Button button7 = new Button();
    Button button8 = new Button();
    Button button9 = new Button();
    Button button10 = new Button();
    Button button11 = new Button();

    public void draw(Graphics2D g2d, Boolean down,Boolean right,Boolean up, Boolean left,boolean enter,boolean menue){
        if(menue){
            enterA=0;
            downA = 0;
        }
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
           
            g2d.drawImage(img1, 0, 0, null);
            button1.draw(g2d,300,170 ,300,45,400, "New Game" );
            button2.draw(g2d,300,250 ,300,45,425, "Load" );
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
            if(downA >= 4 && enterA != 4){
                downA = 4;
            }
            if(downA <= 0 && enterA != 4){
                downA = 0;
            }
            
            
            
            
            
            if(downA == 0){ 
                button1.selectedChange(g2d,300,170,300,45);
            }else if(downA == 1){
                button2.selectedChange(g2d, 300,250,300,45);
            }else if(downA == 2){
                button3.selectedChange(g2d, 300,330,300,45);
            }else if(downA == 3){
                button4.selectedChange(g2d, 300,410,125,45);
            }else if(downA == 4){
                button5.selectedChange(g2d,475 ,410,125,45);
            }
        }
        if(enterA == 1){
            
        }
        if(enterA == 2){
            g2d.drawImage(img1, 0, 0, null);
            
        }
        if(enterA == 3){
            g2d.drawImage(img1, 0, 0, null);
           
        }
        if(enterA == 4){

   
            g2d.drawImage(img1, 0, 0, null);
            button7.draw(g2d,35,150+20,250,130,163-25, "" );
            button8.draw(g2d,325,150+20,250,130,433-25, "" );
            button9.draw(g2d,615,150+20,250,130,718-25, "");
            button10.draw(g2d,160,270+50+25,250,130,278-25, "");
            button11.draw(g2d,490,270+50+25,250,130,585-25, "");
            //Max
            button7.write(g2d, 163-30,170+29 , "Max:" ,33);
            button7.write(g2d, 163-85,170+29+29, "Game-Engine,",28 );
            button7.write(g2d, 163-50,170+29+29+29, "Texture",28 );
            button7.write(g2d, 163-107,170+29+29+29+29, "implementation",28 );
            //Moritz
            button8.write(g2d, 433-30,170+29 , "Moritz:" ,33);
            button8.write(g2d, 433-85,170+29+29, "Level Loading,",28 );
            button8.write(g2d, 433-25,170+29+29+29, "Weapons,",28 );
            button8.write(g2d, 433-105,170+29+29+29+29, "Sounds and Music",28 );
            //Mattis
            button9.write(g2d, 718-30,170+29 , "Mattis:" ,33);
            button9.write(g2d, 718-35,170+29+29+20, "Enemy AI",28);
            //Luca
            button10.write(g2d, 278-30,345+29 , "Luca:" ,33);
            button10.write(g2d, 278-60,345+29+29+15, "Main Menu,",28 );
            button10.write(g2d, 278-100,345+29+29+29+15, "User Interface",28 );
            
            //Jonathan
            button11.write(g2d, 585-45,345+29 , "Jonathan:" ,33);
            button11.write(g2d, 585-10,345+29+29+15, "Story,",28 );
            button11.write(g2d, 585-10,345+29+29+29+15, "Design",28 );

            //exit
            button6.draw(g2d, 20, 20, 100, 45, 30, "<Exit");
            button6.selectedChange(g2d, 20, 20, 100, 45);

            downA = -1;
            
           
        }
        
        if(enterA == 5){
           
            System.exit(0);
        }
    }
    
        
}








    
    
