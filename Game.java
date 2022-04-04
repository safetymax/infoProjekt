import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game{

    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setTitle("v0.1 Alpha Miracle Genesis -PonZius Inc.");
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Surface surface = new Surface();
        frame.add(surface);

        frame.setVisible(true);
        surface.startGameThread();

    }
}