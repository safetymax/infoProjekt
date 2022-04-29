import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    //Key pressed variables
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, enterPressed;

    //Key Pressed (If new Key Added, dont forget to turn off in keyReleased)
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
        if(code == KeyEvent.VK_SHIFT) {
            //System.out.println("Tab key pressed");
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_ENTER) {
            //System.out.println("Tab key pressed");
            enterPressed = true;
        }
    }

    //Key Released
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
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}