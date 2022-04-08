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
    
    //Empty level Information
    String[] info;
    Boundary[] boundaries = new Boundary[256];

    int frameCount = 0;

    public Surface() {
        //Setup function, runs before game loop (Put everything in here that is only supposed to run once)
        addKeyListener(keyH);
        setFocusable(true);
        setBackground(Color.BLACK);

        //random walls: debug
        /*for(int i = 0; i < 6; i++) {
            boundaries[i] = new Wall((int) (Math.random() * 700), (int) (Math.random() * 700), (int) (Math.random() * 700), (int) (Math.random() * 700));
        }
        boundaries[6] = new Wall(0, 0, 700, 0);
        boundaries[7] = new Wall(0, 0, 0, 700);
        boundaries[8] = new Wall(700, 0, 700, 700);
        boundaries[9] = new Wall(0, 700, 700, 700);
        */
        try {
            info = LevelGeneration.readFile("level1.txt");
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        Wall[] walls = LevelGeneration.generateLevel(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
        
        /*for(int i = 0; i < walls.length; i++) {
            if(walls[i] !=null){
            System.out.println(walls[i].x1 + " " + walls[i].y1 + " " + walls[i].x2 + " " + walls[i].y2);
            }
        }*/
    }

    private void doDrawing(Graphics g) {
        //Everything drawing related in here

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, 700, 700);

        //Enviroment
        g2d.setPaint(Color.BLUE);
        if(keyH.shiftPressed){  //shiftPressed = minimap
            //Loop through all game entities
            for(int i = 0; i < boundaries.length; i++) {
                if(boundaries[i] != null) {
                    g2d.drawLine(boundaries[i].x1, boundaries[i].y1, boundaries[i].x2, boundaries[i].y2);
                }
            }
        }

        //Player
        player.draw(g2d, keyH.shiftPressed);
        player.cast(boundaries, g2d, keyH.shiftPressed);

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
        player.move(keyH.upPressed, keyH.downPressed, keyH.leftPressed, keyH.rightPressed);
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

                //update information
                update();

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