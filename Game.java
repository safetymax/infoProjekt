
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


class Surface extends JPanel {

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
}


public class Game extends JFrame{

    public int screenW;
    public int screenH;

    public Game(){
        screenW = 700;
        screenH = 700;
        initUI();
    }   

    private void initUI() {

        add(new Surface());

        setTitle("Project Pink Spyda");
        setSize(screenW, screenH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Game will run in this class
    public static void main(String[] args) {
        System.out.println("Hello World");

         EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Game game = new Game();
                game.setVisible(true);
            }
        });
    }
}