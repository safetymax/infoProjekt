import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, speedUp, speedDown;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            //System.out.println("W key pressed");
            upPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            //System.out.println("A key pressed");
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            //System.out.println("S key pressed");
            downPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            //System.out.println("D key pressed");
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}