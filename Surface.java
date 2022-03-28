import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Surface extends JPanel implements Runnable {

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player();

    int frameCount = 0;

    public Surface() {
        addKeyListener(keyH);
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.PINK);
        g2d.fillRect(0, 0, 700, 700);

        //Player
        g2d.setPaint(Color.RED);
        g2d.fillOval((int) player.posX, (int) player.posY, 20, 20);
        g2d.setPaint(Color.BLACK);
        g2d.drawLine((int) player.posX+10, (int) player.posY+10, (int) (player.posX+10 + Math.cos(player.direction) * 20), (int) (player.posY+10 + Math.sin(player.direction) * 20));

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