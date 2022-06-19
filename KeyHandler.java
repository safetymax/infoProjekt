import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    //Key pressed variables
    public boolean upPressed, downPressed, leftPressed, rightPressed, lookLeftPressed, lookRightPressed, shiftPressed, controlPressed, enterPressed, escapePressed,ePressed,pPressed,rPressed;

    public int up, down, left, right, lookLeft, lookRight, shift, control, enter, escape, eKey, pKey, rKey;
    public boolean[] change = new boolean[13];
    public boolean setKey;
    public KeyHandler(){
        up = KeyEvent.VK_W;
        down = KeyEvent.VK_S;
        left = KeyEvent.VK_A;
        right = KeyEvent.VK_D;
        lookLeft = KeyEvent.VK_J;
        lookRight = KeyEvent.VK_L;
        shift = KeyEvent.VK_SHIFT;
        control = KeyEvent.VK_CONTROL;
        enter = KeyEvent.VK_ENTER;
        escape = KeyEvent.VK_ESCAPE;
        eKey = KeyEvent.VK_E;
        pKey = KeyEvent.VK_P;
        rKey = KeyEvent.VK_R;

        for(int i = 0; i < change.length; i++){
            change[i] = false;
        }

        setKey = false;
    }

    //Key Pressed (If new Key Added, dont forget to turn off in keyReleased)
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == up) {
            //System.out.println("W key pressed");
            upPressed = true;
        }
        if(code == left) {
            //System.out.println("A key pressed");
            leftPressed = true;
        }
        if(code == down) {
            //System.out.println("S key pressed");
            downPressed = true;
        }
        if(code == right) {
            //System.out.println("D key pressed");
            rightPressed = true;
        }
        if(code == lookLeft) {
            //System.out.println("J key pressed");
            lookLeftPressed = true;
        }
        if(code == lookRight) {
            //System.out.println("L key pressed");
            lookRightPressed = true;
        }
        if(code == eKey) {
            //System.out.println("E key pressed");
            ePressed = true;
        }

        if(code == shift) {
            //System.out.println("Shift key pressed");
            shiftPressed = true;
        }
        if(code == control) {
            //System.out.println("Control key pressed");
            controlPressed = true;
        }
        if(code == enter) {
            //System.out.println("Tab key pressed");
            enterPressed = true;
        }
        if(code == escape) {
            //System.out.println("Tab key pressed");
            escapePressed = true;
        }
        if(code == pKey) {
            pPressed = true;

        }
        if(code == rKey) {
            rPressed = true;


        }

        if(setKey){
            changeKey(change, code);
        }
    }

    //Key Released
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == up) {
            upPressed = false;
        }
        if(code == left) {
            leftPressed = false;
        }
        if(code == down) {
            downPressed = false;
        }
        if(code == right) {
            rightPressed = false;
        }
        if(code == lookLeft) {
            lookLeftPressed = false;
        }
        if(code == lookRight) {
            lookRightPressed = false;
        }
        if(code == eKey) {
            ePressed = false;
        }
        if(code == shift) {
            shiftPressed = false;
        }
        if(code == control) {
            controlPressed = false;
        }
        if(code == enter) {
            enterPressed = false;
        }
        if(code == escape) {
            escapePressed = false;
        }
        if(code == pKey) {
            pPressed = false;
        }
        if(code == rKey) {
            rPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    
    }

    public void changeKey(boolean[] change, int code){
        for(int i = 0; i < change.length; i++){
            if(change[i] == true){
                change[i] = false;
                switch(i){
                    case 0:
                        up = code;
                        break;
                    case 1:
                        down = code;
                        break;
                    case 2:
                        left = code;
                        break;
                    case 3:
                        right = code;
                        break;
                    case 4:
                        lookLeft = code;
                        break;
                    case 5:
                        lookRight = code;
                        break;
                    case 6:
                        shift = code;
                        break;
                    case 7:
                        control = code;
                        break;
                    case 8:
                        enter = code;
                        break;
                    case 9:
                        escape = code;
                        break;
                    case 10:
                        eKey = code;
                        break;
                    case 11:
                        pKey = code;
                        break;
                    case 12:
                        rKey = code;
                        break;
                }
                setKey = false;
                break;
            }
        }
    }
}