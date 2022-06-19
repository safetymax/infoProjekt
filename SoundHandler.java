import java.io.*;

import javax.sound.sampled.*;

public class SoundHandler{
    AudioInputStream laserShoot = null;
    AudioInputStream playerWalking = null;
    AudioInputStream alien1Walking = null;
    AudioInputStream alien2Walking = null;
    AudioInputStream doorOpen = null;
    
    Clip playerWalkingclip = null;
    Clip laserShootclip = null;
    Clip alien1Walkingclip = null;
    Clip alien2Walkingclip = null;
    Clip doorOpenclip = null;

    FloatControl laserShootvolume;
    FloatControl playerWalkingvolume;
    FloatControl alien1Walkingvolume;
    FloatControl alien2Walkingvolume;
    FloatControl doorOpenvolume;
    public SoundHandler(){
        try{
            laserShoot = AudioSystem.getAudioInputStream(new File("laserShoot.wav").getAbsoluteFile());
            laserShootclip = AudioSystem.getClip();
            laserShootclip.open(laserShoot);
            
            playerWalking = AudioSystem.getAudioInputStream(new File("walk.wav").getAbsoluteFile());
            playerWalkingclip = AudioSystem.getClip();
            playerWalkingclip.open(playerWalking);
            
            alien1Walking = AudioSystem.getAudioInputStream(new File("alien1Walking.wav").getAbsoluteFile());
            alien1Walkingclip = AudioSystem.getClip();
            alien1Walkingclip.open(alien1Walking);
            
            doorOpen = AudioSystem.getAudioInputStream(new File("doorOpen.wav").getAbsoluteFile());
            doorOpenclip = AudioSystem.getClip();
            doorOpenclip.open(doorOpen);


            laserShootvolume = (FloatControl) laserShootclip.getControl(FloatControl.Type.MASTER_GAIN);
            playerWalkingvolume = (FloatControl) playerWalkingclip.getControl(FloatControl.Type.MASTER_GAIN);
            alien1Walkingvolume = (FloatControl) alien1Walkingclip.getControl(FloatControl.Type.MASTER_GAIN);
            //alien2Walkingvolume = (FloatControl) alien2Walkingclip.getControl(FloatControl.Type.MASTER_GAIN);
            doorOpenvolume = (FloatControl) doorOpenclip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch(Exception e){

            e.printStackTrace();
        }


    }
    public void playSound(String fileName, int param){
        try{
            
            
            if(fileName == "laserShoot"){
                laserShootvolume.setValue(-10f);
                laserShootclip.loop(1);
                
                
            } else if(fileName == "playerWalking"){
                playerWalkingvolume.setValue(-25f);
                playerWalkingclip.loop(1);

            } else if(fileName == "alien1Walking"){
                alien1Walkingvolume.setValue((-20)*(param/100));
                alien1Walkingclip.loop(1);
                
            } else if(fileName == "alien2Walking"){
                alien2Walkingvolume.setValue(-25f);
                alien2Walkingclip.loop(1);
                
            } else if(fileName == "doorOpen"){
                doorOpenvolume.setValue(-25f);
                doorOpenclip.loop(1);
                


            }
            
        
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
