package pokemonTextBased;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;

public class Sound {
    public static boolean disableSound = false;

    private static final HashMap<String, Clip> clips = new HashMap<>();
    private static final HashMap<String, MediaPlayer> mediaPlayers = new HashMap<>();
    public static void loadSound(String filePath) {
        if (filePath.endsWith(".mp3")) return; // MP3s are streamed, not loaded
        try {
            if (clips.containsKey(filePath)) return;

            File musicFile = new File(filePath);
            if (!musicFile.exists()) {
                System.out.println("Music file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clips.put(filePath, clip);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading WAV sound: " + filePath);
        }
    }
    public static void playSoundOnce(String filePath) {
        if (disableSound) return;

        if (filePath.endsWith(".mp3")) {
            playMP3(filePath, false);
            return;
        }

        try {
            File musicFile = new File(filePath);
            if (!musicFile.exists()) {
                System.out.println("Music file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing WAV sound: " + filePath);
        }
    }
    public static void playMusicOnLoop(String filePath) {
        if (disableSound) return;

        if (filePath.endsWith(".mp3")) {
            playMP3(filePath, true);
            return;
        }

        Clip clip = clips.get(filePath);
        if (clip == null) {
            loadSound(filePath);
            clip = clips.get(filePath);
        }

        if (clip != null && !clip.isRunning()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }
    private static void playMP3(String filePath, boolean loop) {
        try {
            MediaPlayer existing = mediaPlayers.get(filePath);
            if (existing != null) {
                existing.stop();
                existing.dispose();
                mediaPlayers.remove(filePath);
            }

            Media media = new Media(new File(filePath).toURI().toURL().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            if (loop) mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            mediaPlayers.put(filePath, mediaPlayer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Invalid MP3 path: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing MP3: " + filePath);
        }
    }
    public static void stopMusic(String filePath) {
        if (disableSound) return;

        if (filePath.endsWith(".mp3")) {
            MediaPlayer player = mediaPlayers.remove(filePath);
            if (player != null) {
                player.stop();
                player.dispose();
            }
            return;
        }

        Clip clip = clips.remove(filePath);
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
    public static void setVolume(Clip clip, float volume) {
        if (disableSound || clip == null) return;

        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = Math.max(gainControl.getMinimum(), Math.min(gainControl.getMaximum(), volume));
            gainControl.setValue(dB);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adjusting volume.");
        }
    }
    public static Clip getClip(String filePath) {
        if (!clips.containsKey(filePath)) {
            loadSound(filePath);
        }
        return clips.get(filePath);
    }
    public static void stopAllSounds() {
        if (disableSound) return;

        for (Clip clip : clips.values()) {
            if (clip.isRunning()) clip.stop();
            clip.close();
        }
        clips.clear();

        for (MediaPlayer player : mediaPlayers.values()) {
            player.stop();
            player.dispose();
        }
        mediaPlayers.clear();
    }
    // specific
    public static void click() {
        if (disableSound) return;
        playSoundOnce("src/main/music/click.wav");
    }
}
