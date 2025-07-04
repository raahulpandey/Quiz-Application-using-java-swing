import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundUtils {
    public static void playSound(String filepath) {
        try {
            File soundFile = new File(filepath);
            if (!soundFile.exists()) {
                System.out.println("Sound file not found: " + filepath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
