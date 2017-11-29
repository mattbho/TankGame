package TankGame;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {
    private static AudioInputStream soundStream;
    private static String soundFile;
    private static Clip clip;

    public static void player(String file, boolean loop){
        soundFile = file;
        try{
            soundStream = AudioSystem.getAudioInputStream(new File(soundFile));
            clip = AudioSystem.getClip();

            if(loop){
                clip.open(soundStream);
                clip.loop(clip.LOOP_CONTINUOUSLY);
            } else{
                clip.start();
            }
        }catch(Exception e){
            System.err.print(e);
        }
    }
}
