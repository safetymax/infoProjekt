import java.io.*;

import javax.sound.sampled.*;

public class SoundHandler{
    AudioInputStream laserShoot = null;
    AudioInputStream playerWalking = null;
    AudioInputStream alien1Walking = null;
    AudioInputStream alien2Walking = null;
    AudioInputStream doorOpen = null;
    AudioInputStream emptyMag = null;
    AudioInputStream melee = null;
    AudioInputStream RammNein = null;

    Clip playerWalkingclip = null;
    Clip laserShootclip = null;
    Clip alien1Walkingclip = null;
    Clip alien2Walkingclip = null;
    Clip doorOpenclip = null;
    Clip emptyMagclip = null;
    Clip meleeclip = null;
    Clip RammNeinclip = null;

    FloatControl laserShootvolume;
    FloatControl playerWalkingvolume;
    FloatControl alien1Walkingvolume;
    FloatControl alien2Walkingvolume;
    FloatControl doorOpenvolume;
    FloatControl emptyMagvolume;
    FloatControl meleevolume;
    FloatControl RammNeinvolume;

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
            
            alien2Walking = AudioSystem.getAudioInputStream(new File("alien2Walking.wav").getAbsoluteFile());
            alien2Walkingclip = AudioSystem.getClip();
            alien2Walkingclip.open(alien2Walking);
            
            doorOpen = AudioSystem.getAudioInputStream(new File("doorOpen.wav").getAbsoluteFile());
            doorOpenclip = AudioSystem.getClip();
            doorOpenclip.open(doorOpen);

            emptyMag = AudioSystem.getAudioInputStream(new File("emptyMag.wav").getAbsoluteFile());
            emptyMagclip = AudioSystem.getClip();
            emptyMagclip.open(emptyMag);

            melee = AudioSystem.getAudioInputStream(new File("melee.wav").getAbsoluteFile());
            meleeclip = AudioSystem.getClip();
            meleeclip.open(melee);
            
            RammNein = AudioSystem.getAudioInputStream(new File("RammNein.wav").getAbsoluteFile());
            RammNeinclip = AudioSystem.getClip();
            RammNeinclip.open(RammNein);

            laserShootvolume = (FloatControl) laserShootclip.getControl(FloatControl.Type.MASTER_GAIN);
            playerWalkingvolume = (FloatControl) playerWalkingclip.getControl(FloatControl.Type.MASTER_GAIN);
            alien1Walkingvolume = (FloatControl) alien1Walkingclip.getControl(FloatControl.Type.MASTER_GAIN);
            alien2Walkingvolume = (FloatControl) alien2Walkingclip.getControl(FloatControl.Type.MASTER_GAIN);
            doorOpenvolume = (FloatControl) doorOpenclip.getControl(FloatControl.Type.MASTER_GAIN);
            emptyMagvolume = (FloatControl) emptyMagclip.getControl(FloatControl.Type.MASTER_GAIN);
            meleevolume = (FloatControl) meleeclip.getControl(FloatControl.Type.MASTER_GAIN);
            RammNeinvolume = (FloatControl) RammNeinclip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch(Exception e){

            e.printStackTrace();
        }


    }
    public void playSound(String fileName, int param){
        try{
            int totalVolumeSFX = Overlay.VolumeA*Overlay.SFXA;
            
            if(fileName == "laserShoot"){
                laserShootvolume.setValue(-10f/totalVolumeSFX);
                laserShootclip.setFramePosition(0);
                laserShootclip.start();
               
                
            } else if(fileName == "playerWalking"){
                playerWalkingvolume.setValue(-25f/totalVolumeSFX);
                playerWalkingclip.setFramePosition(0);
                playerWalkingclip.start();

            } else if(fileName == "alien1Walking"){
                alien1Walkingvolume.setValue(-40f/totalVolumeSFX);
                alien1Walkingclip.setFramePosition(0);
                alien1Walkingclip.start();
                
            } else if(fileName == "alien2Walking"){
                alien2Walkingvolume.setValue(-30f/totalVolumeSFX);
                alien2Walkingclip.setFramePosition(0);
                alien2Walkingclip.start();
                
            } else if(fileName == "doorOpen"){
                doorOpenvolume.setValue(-25f/totalVolumeSFX);
                doorOpenclip.setFramePosition(0);
                doorOpenclip.start();
                


            } else if(fileName == "emptyMag"){
                emptyMagvolume.setValue(-25f/totalVolumeSFX);
                emptyMagclip.setFramePosition(0);
                emptyMagclip.start();
                

            } else if(fileName == "melee"){
                meleevolume.setValue(-25f/totalVolumeSFX);
                meleeclip.setFramePosition(0);
                meleeclip.start();
                
            } else if(fileName == "RammNein"){
                RammNeinvolume.setValue(-25f/(Overlay.VolumeA*Overlay.MusicA));
                RammNeinclip.loop(Clip.LOOP_CONTINUOUSLY);
                
            }
            
        
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
