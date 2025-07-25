package pokemonTextBased;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Sound {
    public static boolean disableSound = false;

    private static final HashMap<String, Clip> clips = new HashMap<>();
    private static final HashMap<String, MediaPlayer> mediaPlayers = new HashMap<>();
    private static final HashMap<String, Boolean> loopingClips = new HashMap<>();
    private static final HashMap<String, Boolean> loopingMedia = new HashMap<>();

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

            // Clean up once playback is done
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing WAV sound: " + filePath);
        }
    }
    public static void playMusicOnLoop(String filePath) {
        if (disableSound) return;

        if (filePath.endsWith(".mp3")) {
            MediaPlayer existing = mediaPlayers.get(filePath);
            if (existing != null && existing.getStatus() == MediaPlayer.Status.PLAYING) {
                // don't restart if already playing
                return;
            }
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
            loopingClips.put(filePath, true);
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
                loopingMedia.remove(filePath);
            }

            Media media = new Media(new File(filePath).toURI().toURL().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            loopingMedia.put(filePath, loop);
            if (loop) {
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            } else {
                // Dispose after playing once
                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.dispose();
                    mediaPlayers.remove(filePath);
                    loopingMedia.remove(filePath);
                });
            }

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
                loopingMedia.remove(filePath);
            }
            return;
        }

        Clip clip = clips.remove(filePath);
        if (clip != null) {
            clip.stop();
            clip.close();
            loopingClips.remove(filePath);
        }
    }
    public static void stopAllSounds() {
        if (disableSound) return;

        // Stop and dispose looping WAV clips
        ArrayList<String> clipKeys = new ArrayList<>(loopingClips.keySet());
        for (String key : clipKeys) {
            Clip clip = clips.get(key);
            if (clip != null) {
                try {
                    clip.stop();
                    clip.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error stopping WAV: " + key);
                }
            }
            clips.remove(key);
            loopingClips.remove(key);
        }

        // Stop and dispose looping MP3s
        ArrayList<String> mediaKeys = new ArrayList<>(loopingMedia.keySet());
        for (String key : mediaKeys) {
            MediaPlayer player = mediaPlayers.get(key);
            if (player != null) {
                try {
                    player.stop();
                    player.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error stopping MP3: " + key);
                }
            }
            mediaPlayers.remove(key);
            loopingMedia.remove(key);
        }
    }

    // specific
    public static void click() {
        if (disableSound) return;
        playSoundOnce("src/main/music/click.wav");
    }
    public static void exitBall(Pokemon pkm) {
        playSoundOnce("src/main/music/catchFail.mp3");
        if(pkm.isShiny()) playSoundOnce("src/main/music/shinySparkles.mp3");
    }
}