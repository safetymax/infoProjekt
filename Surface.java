import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Surface extends JPanel implements Runnable {

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
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
        g2d.setPaint(Color.BLACK);
        g2d.drawString(""+frameCount, 350, 350);
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
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread!=null) {
            //game Thread
            //System.out.println("Game Thread is running");

            //update information
            update();

            //draw the screen
            repaint();
        }
        
    }
}