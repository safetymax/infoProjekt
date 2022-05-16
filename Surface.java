import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Surface extends JPanel implements Runnable {

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Game Elements
    Player player = new Player();
    Enemy enemy = new Enemy(100, 100);
    MachineGun weapons = new MachineGun(150);
    //TEMP
     

    boolean overlayActive = true;

    Overlay overlay = new Overlay();
    
    //Empty level Information
    String[] info;
    Boundary[] boundaries = new Boundary[2048];
    int collisions[][];

    int frameCount = 0;

    public Surface() {
        //Setup function, runs before game loop (Put everything in here that is only supposed to run once)
        addKeyListener(keyH);
        setFocusable(true);
        setBackground(Color.BLACK);
        
        try {
            info = LevelGeneration.readFile("level1.txt");
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        Boundary[] walls = LevelGeneration.generateLevel(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
        collisions = LevelGeneration.generateLevelInt(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
        

        walls =LevelGeneration.removeDuplicateWalls(walls);
        
        for(int i = 0;i < walls.length; i++){
            boundaries[i] = walls[i];
        }

        //fill holes in boundaries array
        for(int i = 0; i < boundaries.length; i++){
            if(boundaries[i] == null){
                for(int j = i; j < boundaries.length; j++){
                    if(boundaries[j] != null){
                        boundaries[i] = boundaries[j];
                        boundaries[j] = null;
                        break;
                    }
                }
            }
        }
        
        for(int i = 0; i < boundaries.length; i++){
            if(boundaries[i] == null){
                boundaries[i] = enemy;
                break;
            }
        }
    }

    private void doDrawing(Graphics g) {
        //Everything drawing related in here

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(30,30,30));
        g2d.fillRect(0, 0, 900, 900);

        //Enviroment
        g2d.setPaint(Color.BLUE);
        if(keyH.controlPressed){  //controlPressed = minimap
            //Loop through all game entities
            for(int i = 0; i < boundaries.length; i++) {
                if(boundaries[i] != null) {
                    g2d.setPaint(new Color(90f/255f,90f/255f,90f/255f));
                    g2d.drawLine(boundaries[i].x1, boundaries[i].y1, boundaries[i].x2, boundaries[i].y2);
                }
            }
            //Draw bullets on minimap
            weapons.drawWeapons(g2d);
        }

        if(!keyH.controlPressed){  //controlPressed = minimap
            //Himmel
            g2d.setPaint(new Color(240f/255f, 140f/255f, 140f/255f));
            g2d.fillRect(0, 0, 900, 450);

            //Boden
            g2d.setPaint(new Color(30, 30, 30));
            g2d.fillRect(0, 450, 900, 450);
        }

        //Player
        player.draw(g2d, keyH.controlPressed);
        player.cast(boundaries, g2d, keyH.controlPressed);


        //overlay
      
        overlay.draw(g2d, keyH.downPressed, keyH.rightPressed, keyH.upPressed, keyH.leftPressed, keyH.enterPressed, keyH.escapePressed);
        
        //debug
        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    public void update() {
        //update the game
        frameCount++;
        
        weapons.updateWeapons(player);
        
        for(int i = 0; i < boundaries.length; i++){
            if(boundaries[i] != null){
                if(boundaries[i].type == 4){
                    ((Enemy) (boundaries[i])).move(boundaries);
                }
            }
        }

        //RPM = 60/cooldown*60
        int cooldown = 3;
        if (frameCount % cooldown == 0) {
        player.shootKey(keyH.ePressed,weapons);
        }
        player.move(keyH.upPressed, keyH.downPressed, keyH.leftPressed, keyH.rightPressed, keyH.lookLeftPressed, keyH.lookRightPressed, keyH.shiftPressed, collisions);
    }

    public void startGameThread() {
        //Start the game thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        //Determines how often the game updates (FPS)
        double drawInterval = 1000000000/60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        long drawCount = 0;

        while(gameThread!=null) {
            //game Thread
            //System.out.println("Game Thread is running");

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
                //Game Loop
                if(Overlay.getMove()){
                //update information
                update();
                }
                //draw the screen
                repaint();
                
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
        
    }
}