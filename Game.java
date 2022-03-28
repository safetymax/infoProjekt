
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;



class Surface extends JPanel implements Runnable {

    Thread gameThread;

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.PINK);
        g2d.fillRect(0, 0, 700, 700);
        g2d.setPaint(Color.BLACK);
        g2d.drawString("Test 0.1", 350, 350);

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread!=null) {
            System.out.println("Game Thread is running");
        }
        
    }
}


public class Game{

    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setTitle("Project Pink Spyda");
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Surface surface = new Surface();
        frame.add(surface);

        frame.setVisible(true);
        surface.startGameThread();

    }
}