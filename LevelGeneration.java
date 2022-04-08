import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LevelGeneration {
    
    
    public static String[] readFile(String levelName) throws FileNotFoundException, IOException{
        String[] levelInfo = new String[6];

        
            BufferedReader r = new BufferedReader(new FileReader(levelName));

         
        
        for(int i = 0; i < levelInfo.length; i++){
            
            try{
            levelInfo[i] = r.readLine();
            }catch(Exception e){
                System.out.println("Error reading file");
            }
        
        }
        r.close();
        levelInfo[0] = levelInfo[0].replace("Level: ", "");
        levelInfo[1] = levelInfo[1].replace("Start X: ", "");
        levelInfo[2] = levelInfo[2].replace("Start Y: ", "");
        levelInfo[3] = levelInfo[3].replace("Size: ", "");
        levelInfo[4] = levelInfo[4].replace("Rows: ", "");
        levelInfo[5] = levelInfo[5].replace("Colums: ", "");
        
       
        return levelInfo;
    
    }

    public static Boundary[] generateLevel(String level, int startx, int starty, int size, int rows, int columns) {
    
        // du structurer levelInfo:
                
        Boundary[] walls = new Wall[rows * columns*4]; 
        //generate Walls from starting x, changes y when x*colums is reached
        int x = startx;
        int y = starty;
        
        for(int i = 0; i<level.length(); i++){
            int c = i*4;
            System.out.println("X: " + x);
            System.out.println("Y: " + y);
            System.out.println("Size: "+ size);
            if(level.charAt(i) == 'W'){
                walls[c] = new Wall(x,y,x,y+size);  //top left to bot left
                walls[c+1] = new Wall(x,y,x+size,y); //top left to top right
                walls[c+2] = new Wall(x+size,y,x+size,y+size); //top right to bot right
                walls[c+3] = new Wall(x,y+size,x+size,y+size); //bot left to bot right
            }
            x= x+size;
            if(x>=startx+size*columns){
                x = startx;
                
                y+=size;
            }
        }
        return walls;
    
    }
}
