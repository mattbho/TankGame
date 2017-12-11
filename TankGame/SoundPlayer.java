package TankGame;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class SoundPlayer {
    public static void player(String file, boolean Loop) {
        try {
            URL url = TankMain.getFrame().getClass().getClassLoader()
                    .getResource(file);
            AudioClip clip = Applet.newAudioClip(url);
            if (Loop) {
                AudioInputStream inputStream = AudioSystem
                        .getAudioInputStream(url);
                Clip soundLoop = AudioSystem.getClip();
                soundLoop.open(inputStream);
                soundLoop.loop(Clip.LOOP_CONTINUOUSLY);
            } else
                clip.play();
        } catch (Exception e) {

        }
    }
}

