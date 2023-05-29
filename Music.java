import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Music {

    File musicPath;
    AudioInputStream audioInput;
    Clip clip;

    public Music(String musicLocation) {

        try {
        musicPath = new File(musicLocation);
        audioInput = AudioSystem.getAudioInputStream(musicPath);
        clip = AudioSystem.getClip();
        clip.open(audioInput);
        setVolume(-20);
       

        }
        catch (Exception ex) {
            ex.getStackTrace();
        }
    }
    
    public void playMusic() {
        
        clip.start();
    }

    public void setSong(long time) {
        clip.stop();
        clip.setMicrosecondPosition(time);
        clip.start();
    }

    public void setVolume(float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        }
    }
}
