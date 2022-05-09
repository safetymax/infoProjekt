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
    private int downA,enterA,optionsA,counter,BindingsA;
    
    private boolean a,b,c,d,e,f,g,h,i,j;

    private static boolean move;
    Image img1 = Toolkit.getDefaultToolkit().getImage("Bild.png");
  
    Image img5 = Toolkit.getDefaultToolkit().getImage("Sound 3.png");
    Image img6 = Toolkit.getDefaultToolkit().getImage("Tastatur.png");
   
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
    Button button12 = new Button();
    Button button13 = new Button();
    public void draw(Graphics2D g2d, Boolean down,Boolean right,Boolean up, Boolean left,boolean enter,boolean menue){
       
        if(menue){
            enterA=0;
            downA = 0;
            optionsA = 0;
        }
        if(enter && !i && !j){
            i = true;
            j = true;
            
        }
        if(i){
            enterA = downA + 1;
            downA = 0;
            
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
        if(downA == 2){
            counter = 0;
        }
       
       move = false;
        
      //counter++;
      
      
        if(enterA == 0){
           
            g2d.drawImage(img1, 0, 0, null);
            button1.draw(g2d,300,170 ,300,45,0, "New Game" );
            button2.draw(g2d,300,250 ,300,45,0, "Load" );
            button3.draw(g2d,300,330,300,45,0, "Options");
            button4.draw(g2d,300,410,125,45,0, "Credits");
            button5.draw(g2d,475,410,125,45,0, "Quit");
        
        
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
            if(downA >= 4 ){
                downA = 4;
            }
            if(downA <= 0 && enterA == 0){
                downA = 0;
            }
            
            
            
            
            
            if(downA == 0){ 
                button1.selectedChange(g2d,300,170,300,45);
            }else if(downA == 1){
                button2.selectedChange(g2d, 300,250,300,45);
            }else if(downA == 2){
                button3.selectedChange(g2d, 300,330,300,45);
                optionsA = 0;
                        }else if(downA == 3){
                button4.selectedChange(g2d, 300,410,125,45);
            }else if(downA == 4){
                button5.selectedChange(g2d,475 ,410,125,45);
            }
        }
        //new Game
        if(enterA == 1){
            move = true;
        }
        //Load
        if(enterA == 2){
            g2d.drawImage(img1, 0, 0, null);
            //exit
    
            button6.draw(g2d, 20, 20, 100, 45,0,  "<Exit");   
            if(optionsA == 0){ 
                //exit
                button6.selectedChange(g2d,20,20,100,45);
                downA = -1;
                
            
            }else if(optionsA == 1){
                //Bindings
                button12.selectedChange(g2d, 250+20,200,150,150);
                downA = 5;
            }else if(optionsA == 2){
                //Sound
                button13.selectedChange(g2d, 450+20,200,150,150);
                downA = 6;
            }
           
            
        }
            if(right && !e && !f){
                e = true;
                f = true;
            }
            if(e){
                optionsA++;
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
                optionsA--;
            }
            if(left && h){
                g = false;
            }
            if(!left){
                h = false;
            }
            if(optionsA <= 0){
                optionsA = 0;
            }
            if(optionsA >= 2 ){
                optionsA = 2;
            }
        
        //Optiones
        if(enterA == 3){
          
            g2d.drawImage(img1, 0, 0, null);
            //Exit
            button6.draw(g2d, 20, 20, 100, 45,0, "<Exit");    
            //Bindings
            button12.draw(g2d, 250+20, 200, 150, 150,0,"Bindings");
            g2d.drawImage(img6, 225+50+3+20, 250, null);
            //Sound
            button13.draw(g2d, 450+20, 200, 150, 150,0,"Sound");
            g2d.drawImage(img5, 225+50+3+200+20, 250 , null);
            
            if(optionsA == 0){ 
                //exit
                button6.selectedChange(g2d,20,20,100,45);
                downA = -1;
            
            }else if(optionsA == 1){
                //Bindings
                button12.selectedChange(g2d, 250+20,200,150,150);
                downA = 5;
            }else if(optionsA == 2){
                //Sound
                button13.selectedChange(g2d, 450+20,200,150,150);
                downA = 6;
            }
           
            
        }
            if(right && !e && !f){
                e = true;
                f = true;
            }
            if(e){
                optionsA++;
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
                optionsA--;
            }
            if(left && h){
                g = false;
            }
            if(!left){
                h = false;
            }
            if(optionsA <= 0){
                optionsA = 0;
            }
            if(optionsA >= 2 ){
                optionsA = 2;
            }
        //Bindings 1mm = 5 pixel
        if(enterA == 6){
            g2d.drawImage(img1, 0, 0, null);
            button6.draw(g2d, 20, 20, 100, 45,0,  "<Exit");
            button1.draw(g2d,300,80 ,300,45,-45, "Move Forward" );
            button1.draw(g2d,515,80,85,45,0,"W");
            button2.draw(g2d,300,160 ,300,45,-55-10-10, "Move Left" );
            button1.draw(g2d,515,160,85,45,0,"A");
            button3.draw(g2d,300,240,300,45,-55-8-5, "Move Back");
            button1.draw(g2d,515,240,85,45,0,"S");
            button1.draw(g2d,300,320 ,300,45, -55-8,"Move Right" );
            button1.draw(g2d,515,320,85,45,0,"D");
            button2.draw(g2d,300,400 ,300,45,-60-15-5, "Turn Left" );
            button1.draw(g2d,515,400,85,45,0,"J");
            button3.draw(g2d,300,480,300,45,-57-15+5, "Turn Right");
            button1.draw(g2d,515,480,85,45,0,"L");
            button1.draw(g2d,300,560 ,300,45,-75-20-5, "Sprint" );
            button1.draw(g2d,515,560,85,45,0,"Shift");
            button2.draw(g2d,300,640 ,300,45,-85-20-10, "Map" );
            button1.draw(g2d,515,640,85,45,0,"Strg");
            button3.draw(g2d,300,720,300,45,-80-20, "Shoot");
            button1.draw(g2d,515,720,85,45,0,"Space");
            if(down && !a && !b){
                a = true;
                b = true;
            }
            if(a){
                BindingsA++;
            }
            if(down && b){
                a = false;
            }
            if(!down){
                b = false;
            }
            if(up && !c && !d  ){
                c = true;
                d = true;
            }
            if(c){
                BindingsA--;
            }
            if(up && d){
                c = false;
            }
            if(!up){
                d = false;
            }
            if(BindingsA <= 0){
                BindingsA = 0;
            }
            if(BindingsA >= 9){
                BindingsA = 9;
            }
            if(BindingsA == 0){
                button6.selectedChange(g2d, 20, 20, 100, 45);
                downA = 2;
                optionsA = 0;
            }else if(BindingsA == 1){
                button1.selectedChange(g2d, 515, 80, 85, 45);
            }else if(BindingsA == 2){
                button1.selectedChange(g2d, 515, 80*2, 85, 45);
            }else if(BindingsA == 3){
                button1.selectedChange(g2d, 515, 80*3, 85, 45);
            }else if(BindingsA == 4){
                button1.selectedChange(g2d, 515, 80*4, 85, 45);
            }else if(BindingsA == 5){
                button1.selectedChange(g2d, 515, 80*5, 85, 45);
            }else if(BindingsA == 6){
                button1.selectedChange(g2d, 515, 80*6, 85, 45);
            }else if(BindingsA == 7){
                button1.selectedChange(g2d, 515, 80*7, 85, 45);
            }else if(BindingsA == 8){
                button1.selectedChange(g2d, 515, 80*8, 85, 45);
            }else if(BindingsA == 9){
                button1.selectedChange(g2d, 515, 80*9, 85, 45);
            }
            
        }
        //Sound
        if(enterA == 7){
            
        }
        //Credits
        if(enterA == 4){

   
            g2d.drawImage(img1, 0, 0, null);
            button7.draw(g2d,35,150+20,250,130,0, "" );
            button8.draw(g2d,325,150+20,250,130,0, "" );
            button9.draw(g2d,615,150+20,250,130,0, "");
            button10.draw(g2d,160,270+50+25,250,130,0, "");
            button11.draw(g2d,490,270+50+25,250,130,0, "");
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
            button6.draw(g2d, 20, 20, 100, 45,0,  "<Exit");
            button6.selectedChange(g2d, 20, 20, 100, 45);

            downA = -1;
            
           
        }
        //quit
        if(enterA == 5){
           
            System.exit(0);
        }
    }

    public static boolean getMove(){
        return move;
    }
  
       
}
    
    
        









    
    
