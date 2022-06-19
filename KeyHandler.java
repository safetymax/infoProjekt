import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    //Key pressed variables
    public boolean upPressed, downPressed, leftPressed, rightPressed, lookLeftPressed, lookRightPressed, shiftPressed, controlPressed, enterPressed, escapePressed,ePressed,pPressed,rPressed;

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
        if(code == KeyEvent.VK_UP) {
            //System.out.println("UP key pressed");
            upPressed = true;
        }
        if(code == KeyEvent.VK_LEFT) {
            //System.out.println("LEFT key pressed");
            leftPressed = true;
        }
        if(code == KeyEvent.VK_DOWN) {
            //System.out.println("DOWN key pressed");
            downPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT) {
            //System.out.println("RIGHT key pressed");
            rightPressed = true;
        }
        if(code == KeyEvent.VK_NUMPAD4) {
            //System.out.println("4 key pressed");
            lookLeftPressed = true;
        }
        if(code == KeyEvent.VK_NUMPAD6) {
            //System.out.println("6 key pressed");
            lookRightPressed = true;
        }
        if(code == KeyEvent.VK_J) {
            //System.out.println("J key pressed");
            lookLeftPressed = true;
        }
        if(code == KeyEvent.VK_L) {
            //System.out.println("L key pressed");
            lookRightPressed = true;
        }
        if(code == KeyEvent.VK_E){
            //System.out.println("E key pressed");
            ePressed = true;
        }

        if(code == KeyEvent.VK_SHIFT) {
            //System.out.println("Shift key pressed");
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_CONTROL) {
            //System.out.println("Control key pressed");
            controlPressed = true;
        }
        if(code == KeyEvent.VK_ENTER) {
            //System.out.println("Tab key pressed");
            enterPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE) {
            //System.out.println("Tab key pressed");
            escapePressed = true;
        }
        if(code == KeyEvent.VK_P){
            pPressed = true;

        }
        if(code == KeyEvent.VK_R){
            rPressed = true;


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
        if(code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_NUMPAD4) {
            lookLeftPressed = false;
        }
        if(code == KeyEvent.VK_NUMPAD6) {
            lookRightPressed = false;
        }
        if(code == KeyEvent.VK_J) {
            lookLeftPressed = false;
        }
        if(code == KeyEvent.VK_L) {
            lookRightPressed = false;
        }
        if(code == KeyEvent.VK_E){
            ePressed = false;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        if(code == KeyEvent.VK_CONTROL) {
            controlPressed = false;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if(code == KeyEvent.VK_ESCAPE) {
            escapePressed = false;
        }
        if(code == KeyEvent.VK_P){
            pPressed = false;
        }
        if(code == KeyEvent.VK_R){
            rPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    
    }
    
}