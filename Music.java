import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {

    private File musicPath;
    private AudioInputStream audioInput;
    private Clip clip;
    private long currentPosition = 0;


    /**
     * Initializes an AudioInputStream to stream given file path.
     * @param musicLocation filepath to music file
     */
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
    
    /**
     * Plays music from last saved position.  
     */
    public void playMusic() {
        clip.setMicrosecondPosition(currentPosition);
        clip.start();
    }

    /**
     * Saves current position and pauses music.
     */
    public void pauseMusic() {
        currentPosition = clip.getMicrosecondPosition();
        clip.stop();
    }

    /**
     * Switches the song playing to the one at timestamp given.
     * @param time timestamp of current song
     */
    public void setSong(long time) {
        clip.stop();
        clip.setMicrosecondPosition(time);
        clip.start();
    }

    /**
     * Sets the volume of 
     * @param volume less than 0 lowers current volume
     * greater than 0 raises current volume
     */
    public void setVolume(float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        }
    }
}
