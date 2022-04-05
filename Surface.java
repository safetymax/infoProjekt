import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Surface extends JPanel implements Runnable {

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Game Elements
    Player player = new Player();

    Boundary[] boundaries = new Boundary[256];

    int frameCount = 0;

    public Surface() {
        addKeyListener(keyH);
        setFocusable(true);
        setBackground(Color.BLACK);
        for(int i = 0; i < 6; i++) {
            boundaries[i] = new Wall((int) (Math.random() * 700), (int) (Math.random() * 700), (int) (Math.random() * 700), (int) (Math.random() * 700));
        }
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, 700, 700);

        if(keyH.shiftPressed){
        //Enviroment
        g2d.setPaint(Color.BLUE);
        for(int i = 0; i < boundaries.length; i++) {
            if(boundaries[i] != null) {
                g2d.drawLine(boundaries[i].x1, boundaries[i].y1, boundaries[i].x2, boundaries[i].y2);
            }
        }

        //Player
        player.draw(g2d);
        player.cast(boundaries, g2d);
    }

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
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

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