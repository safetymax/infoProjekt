import java.io.*;

import javax.sound.sampled.*;
public class SoundHandler{
    
    public static void playSound(String fileName){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            
            clip.open(audioInputStream);

            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
