import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {
    private Clip clip;

    public void playMusic(String filePath) {
        try {
            ClassLoader classLoader = Board.class.getClassLoader();
            URL resource = classLoader.getResource(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(resource);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}