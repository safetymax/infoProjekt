import java.io.*;

import javax.sound.sampled.*;

public class SoundHandler{
    AudioInputStream laserShoot = null;
    public SoundHandler(){
        try{
            laserShoot = AudioSystem.getAudioInputStream(new File("laserShoot.wav").getAbsoluteFile());
        }catch(Exception e){

            e.printStackTrace();
        }


    }
    public void playSound(String fileName){
        try{
            
            Clip clip = AudioSystem.getClip();
            if(fileName == "laserShoot"){
            clip.open(laserShoot);
            
        }
            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
