import java.io.*;

import javax.sound.sampled.*;

public class SoundHandler{
    AudioInputStream laserShoot = null;
    Clip laserShootclip = null;
    public SoundHandler(){
        try{
            laserShoot = AudioSystem.getAudioInputStream(new File("laserShoot.wav").getAbsoluteFile());
            laserShootclip = AudioSystem.getClip();
            laserShootclip.open(laserShoot);
        }catch(Exception e){

            e.printStackTrace();
        }


    }
    public void playSound(String fileName){
        try{
            
            
            if(fileName == "laserShoot"){
                
                laserShootclip.loop(1);
                
                
            }
            
        
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
