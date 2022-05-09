import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Button{
  private boolean isSelected;
 
  
    
  public void draw(Graphics2D g2d,int x,int y,int widht,int hight,int bt,String text){
    g2d.setPaint(new Color((float)0.58,(float)0.58,(float)0.58));
   g2d.fillRect(x, y, widht, hight);
    isSelected = false; 
    g2d.setPaint(new Color((float)0.99999,(float)0.99990,(float)0.9999990));
    Font font = new Font("Arial",Font.BOLD,28);
    g2d.setFont(font);
    
    AffineTransform affinetransform = new AffineTransform();     
FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
g2d.drawString(text, x-(textwidth/2)+(widht /2)+bt, y+(32));
    g2d.setPaint(new Color((float)0.40,(float)0.40,(float)0.40));
    g2d.setStroke(new BasicStroke(6));
    g2d.drawRect(x,y-1,widht,hight);
  
 
  }
  
  public void selectedChange(Graphics2D g2d,int x,int y,int widht,int hight){
    if(isSelected == true){
      g2d.setStroke(new BasicStroke(7));
      g2d.drawRect(x,y-1,widht,hight);
      g2d.setPaint(new Color((float)0.40,(float)0.40,(float)0.40));
      isSelected = false;
    }else{
      g2d.setPaint(new Color((float)0.9999999,(float)0.9999999,(float)0.9999999999));
      g2d.setStroke(new BasicStroke(7));
      g2d.drawRect(x,y-1,widht,hight);
      g2d.setPaint(new Color((float)0.40,(float)0.40,(float)0.40));
      isSelected = true;
    }
  }
  
  public void write(Graphics2D g2d,int x,int y,String text,int size){
    g2d.setPaint(new Color((float)0.99999,(float)0.99990,(float)0.9999990));
    g2d.setFont(new Font("Consolas",Font.BOLD,size));
    g2d.drawString(text, x, y);
  }
  
 




  
}