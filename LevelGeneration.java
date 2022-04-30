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
            if(level.charAt(i) == 'W'){
                walls[c] = new Wall(x,y,x,y+size,2);  //top left to bot left
                walls[c+1] = new Wall(x,y,x+size,y,1); //top left to top right
                walls[c+2] = new Wall(x+size,y,x+size,y+size,2); //top right to bot right
                walls[c+3] = new Wall(x,y+size,x+size,y+size,1); //bot left to bot right
            }
            x= x+size;
            if(x>=startx+size*columns){
                x = startx;
                
                y+=size;
            }
        }
        
        return walls;
        
    }
    //removes walls which are on top of each other
    public static Boundary[] removeDuplicateWalls(Boundary[] b){
        
        for(int i = 0; i<b.length; i++){
            for(int j = 0; j<b.length; j++){
                if(b[i] != null && b[j] != null){
                    //split x and y coordinate check for more clarity
                    //check if x coordinates match
                    if(b[i].x1 == b[j].x1 && b[i].x2 == b[j].x2){
                        //check if y coordinates match
                        if(b[i].y1 == b[j].y1 && b[i].y2 == b[j].y2){
                            //check indices to avoid checks with itself
                            if(i!=j){
                                b[j] = null;
                                b[i] = null;
                            }
                        }
                    }


                    
                }
            }
        }
       
    

        return b;



    }

}
