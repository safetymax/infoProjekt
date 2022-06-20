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
import java.util.ArrayList;

public class LevelGeneration {

    public static String[] readFile(String levelName) throws FileNotFoundException, IOException {
        String[] levelInfo = new String[9];

        BufferedReader r = new BufferedReader(new FileReader(levelName));

        for (int i = 0; i < levelInfo.length; i++) {

            try {
                levelInfo[i] = r.readLine();
            } catch (Exception e) {
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
        levelInfo[6] = levelInfo[6].replace("Enemies: ", "");
        levelInfo[7] = levelInfo[7].replace("Player X: ", "");
        levelInfo[8] = levelInfo[8].replace("Player Y: ", "");
        
        return levelInfo;

    }

    public static Boundary[] generateLevel(String level, int startx, int starty, int size, int rows, int columns) {

        // du structurer levelInfo:

        Boundary[] walls = new Wall[rows * columns * 4];
        // generate Walls from starting x, changes y when x*colums is reached
        int x = startx;
        int y = starty;

        for (int i = 0; i < level.length(); i++) {
            int c = i * 4;
            if (level.charAt(i) == 'W') {
                walls[c] = new Wall(x, y, x, y + size, 1); // top left to bot left
                walls[c + 1] = new Wall(x, y, x + size, y, 1); // top left to top right
                walls[c + 2] = new Wall(x + size, y, x + size, y + size, 1); // top right to bot right
                walls[c + 3] = new Wall(x, y + size, x + size, y + size, 1); // bot left to bot right
            }
            else if(level.charAt(i) == 'E'){

                walls[c] = new Wall(x, y, x, y + size, 5); // top left to bot left
                walls[c + 1] = new Wall(x, y, x + size, y, 5); // top left to top right
                walls[c + 2] = new Wall(x + size, y, x + size, y + size, 5); // top right to bot right
                walls[c + 3] = new Wall(x, y + size, x + size, y + size, 5); // bot left to bot right




            }




            
            x = x + size;
            if (x >= startx + size * columns) {
                x = startx;

                y += size;
            }
        }

        return walls;

    }

    // int array of walls
    public static int[][] generateLevelInt(String level, int startx, int starty, int size, int rows, int columns) {
        int[][] walls = new int[rows][columns];
        int x = startx;
        int y = starty;
        for (int i = 0; i < level.length(); i++) {
            if (level.charAt(i) == 'W') {
                walls[y][x] = 1;
            } else if (level.charAt(i) == 'O') {
                walls[y][x] = 0;
            } else if(level.charAt(i) == 'E'){
                walls[y][x] = 2;


            }
            
            x += 1;
            if (x >= columns) {
                x = startx;
                y += 1;
            }
        }

        return walls;
    }

    // removes walls which are on top of each other
    public static Boundary[] removeDuplicateWalls(Boundary[] b) {

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[i] != null && b[j] != null) {
                    // split x and y coordinate check for more clarity
                    // check if x coordinates match
                    if (b[i].x1 == b[j].x1 && b[i].x2 == b[j].x2) {
                        // check if y coordinates match
                        if (b[i].y1 == b[j].y1 && b[i].y2 == b[j].y2) {
                            // check indices to avoid checks with itself
                            if (i != j) {
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

    public static Enemy[] LoadEnemies(String Enemies) {
        Enemies = Enemies.replace("[", "").replace("]", "");
        String[] enemyInfo = Enemies.split(",");
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        for (int i = 0; i < enemyInfo.length; i += 3) {
            int x = Integer.parseInt(enemyInfo[i]);
            int y = Integer.parseInt(enemyInfo[i + 1]);
            int type = Integer.parseInt(enemyInfo[i + 2]);
            enemyList.add(new Enemy(x, y, type));
        }
        Enemy[] enemyArray = new Enemy[enemyList.size()];
        for (int i = 0; i < enemyArray.length; i++) {
            enemyArray[i] = enemyList.get(i);
        }
        return enemyArray;
    }
    
    
    
    
    public static int[][] loadNextLevel(int level, Boundary[] b, Boundary[] s, int[][] cols, Player player){
        
        String[] levels = new String[]{"Level1.txt", "Level2.txt","Level3.txt","Level4.txt","level5.txt","level6.txt","level7.txt","level8.txt","level9.txt"};
        
        String[] nextLevelInfo = null;
        int[][] nextcols = null;
        try {
            nextLevelInfo = LevelGeneration.readFile(levels[level]);
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        player.posX = Integer.parseInt(nextLevelInfo[7]);
        player.posY = Integer.parseInt(nextLevelInfo[8]);
        Boundary[] walls = LevelGeneration.generateLevel(nextLevelInfo[0], Integer.parseInt(nextLevelInfo[1]), Integer.parseInt(nextLevelInfo[2]), Integer.parseInt(nextLevelInfo[3]), Integer.parseInt(nextLevelInfo[4]), Integer.parseInt(nextLevelInfo[5]));
        walls = LevelGeneration.removeDuplicateWalls(walls);
        Enemy[] nextenemies = LevelGeneration.LoadEnemies(nextLevelInfo[6]);
        nextcols = LevelGeneration.generateLevelInt(nextLevelInfo[0],Integer.parseInt(nextLevelInfo[1]), Integer.parseInt(nextLevelInfo[2]), Integer.parseInt(nextLevelInfo[3]), Integer.parseInt(nextLevelInfo[4]), Integer.parseInt(nextLevelInfo[5]));
        
        
        //DEBUG
        cols = nextcols;
        for(int i =0;i< walls.length;i ++){
            try{
            b[i] = null;
            b[i] = walls[i];
            }
            catch(Exception e){
                e.printStackTrace();
            }


        }
        
        
        //fill holes in boundaries array
        for(int i = 0; i < b.length; i++){
            if(b[i] == null){
                for(int j = i; j < b.length; j++){
                    if(b[j] != null){
                        b[i] = b[j];
                        b[j] = null;
                        break;
                    }
                }
            }
        }
        
        for(int i = 1; i < nextenemies.length+1; i++){
            
            s[i] = nextenemies[i-1];
        }

        return cols;
    }

}
