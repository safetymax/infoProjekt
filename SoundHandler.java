import java.io.*;

import javax.sound.sampled.*;

public class SoundHandler{
    AudioInputStream laserShoot = null;
    Clip laserShootclip = null;
    AudioInputStream playerWalking = null;
    Clip playerWalkingclip = null;
    public SoundHandler(){
        try{
            laserShoot = AudioSystem.getAudioInputStream(new File("laserShoot.wav").getAbsoluteFile());
            laserShootclip = AudioSystem.getClip();
            laserShootclip.open(laserShoot);
            playerWalking = AudioSystem.getAudioInputStream(new File("walk.wav").getAbsoluteFile());
            playerWalkingclip = AudioSystem.getClip();
            playerWalkingclip.open(playerWalking);
        
        }catch(Exception e){

            e.printStackTrace();
        }


    }
    public void playSound(String fileName){
        try{
            
            
            if(fileName == "laserShoot"){
                
                laserShootclip.loop(1);
                
                
            } else if(fileName == "playerWalking"){

                playerWalkingclip.loop(1);

            }
            
        
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
