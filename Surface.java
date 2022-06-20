import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Surface extends JPanel implements Runnable {
    public static Surface surface;
    public static boolean loaded;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Game Elements
    Player player = new Player();  
    boolean weaponShootable = false;
    double weaponCooldown = 0;
    

    Enemy enemy1 = new Enemy(250, 250, 3);
    Enemy enemy2 = new Enemy(350, 250, 4);
    //TEMP
    SoundHandler sound = new SoundHandler();

    boolean overlayActive = true;

    Overlay overlay = new Overlay();
    Hud hud = new Hud();
    
    //Empty level Information
    String[] info;

    Boundary[] boundaries = new Boundary[10000];
    Boundary[] sprites = new Boundary[10000];
    int[] spriteOrder = new int[10000];
    double[] spriteDistance = new double[10000];

    int[][] collisions = new int[10000][10000];

    int frameCount = 0;

    int currentLevel= 1;

    int levelScore = 0;
    int totalScore = 0;
    public Surface() {
        //Setup function, runs before game loop (Put everything in here that is only supposed to run once)
        addKeyListener(keyH);
        setFocusable(true);
        setBackground(Color.BLACK);
        
        collisions = LevelGeneration.loadNextLevel(currentLevel, boundaries, sprites, collisions, player);

        Overlay.surface = this;
        Overlay.keyH = keyH;
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
                if(sprites[i] != null) {
                    g2d.setPaint(new Color(255f/255f,255f/255f,255f/255f));
                    g2d.drawLine(sprites[i].x1, sprites[i].y1, sprites[i].x2, sprites[i].y2);

                }
            }
            


        }

        if(!keyH.controlPressed){  //controlPressed = minimap
            //Himmel
            g2d.setPaint(new Color(0,0,0));
            g2d.fillRect(0, 0, 900, 900);
        }

        //Player
        player.draw(g2d, keyH.controlPressed);
        player.cast(boundaries, sprites, g2d, keyH.controlPressed);

//ingame hub

hud.draw(g2d);
//save/load
if(Overlay.save){
    saveScore();
    System.out.println("a");
}
if(Overlay.loading){
    loadScore();
    System.out.println("a");
}

        //overlay
      
        overlay.drawMainMenue(g2d, keyH.downPressed, keyH.rightPressed, keyH.upPressed, keyH.leftPressed, keyH.enterPressed, keyH.escapePressed);
        
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
        
        player.updatePlayer(sprites, collisions);

        
        
        if(keyH.ePressed){
            player.shootKey(keyH.ePressed, sprites);
            
        }
        player.move(keyH.upPressed, keyH.downPressed, keyH.leftPressed, keyH.rightPressed, keyH.lookLeftPressed, keyH.lookRightPressed, keyH.shiftPressed, collisions);
        player.reload(keyH.rPressed);
        for(int i = 0; i < sprites.length; i++){
            if(sprites[i] != null){
                if(sprites[i].type == 3 || sprites[i].type == 4){
                    ((Enemy)sprites[i]).move(sprites, player);
                }
            }
        }
        if(keyH.pPressed){
            player.isFinished = true;
            sound.playSound("doorOpen", -1);
            for(int i = 0; i<boundaries.length; i++){
                if(boundaries[i] !=null){
                    if(boundaries[i].type == 5){
                        boundaries[i].type = 6;
                    }

                }
            }
            
        }
        if(player.touchedDoor){
            levelScore = frameCount/10;
            System.out.println("Level Score: " + levelScore);
            totalScore += levelScore;
            currentLevel++;
            collisions =LevelGeneration.loadNextLevel(currentLevel, boundaries, sprites, collisions, player);
            frameCount = 0;
            player.touchedDoor = false;
        }
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
                //System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
        
    }
    public void saveScore() {
        try {
            FileOutputStream fos = new FileOutputStream("save.bat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            Storage storage = new Storage();
          
            storage.surface = surface;
            oos.writeObject(storage);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadScore() {
        try {
            FileInputStream fis = new FileInputStream("save.bat");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            Storage storage = (Storage) ois.readObject();

            surface = storage.surface;

            ois.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}