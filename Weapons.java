import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;
public abstract class Weapons {

    float x;
    float y;
    double direction;
    Bullet b[];
    
    public Weapons( int size){
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        b = new Bullet[size];



    }

    public abstract void updateWeapons(Player player);
    public abstract void shoot();
    

}
