package pokemonTextBased;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Sound {
    public static boolean disableSound = false;

    private static final HashMap<String, Clip> clips = new HashMap<>();
    private static final HashMap<String, MediaPlayer> mediaPlayers = new HashMap<>();
    private static final HashMap<String, Boolean> loopingClips = new HashMap<>();
    private static final HashMap<String, Boolean> loopingMedia = new HashMap<>();

    public static void loadSound(String resourcePath) {
        if (resourcePath.endsWith(".mp3")) return; // MP3s streamed, not preloaded
        try {
            if (clips.containsKey(resourcePath)) return;

            InputStream is = Sound.class.getResourceAsStream("/" + resourcePath);
            if (is == null) {
                System.out.println("Music resource not found: " + resourcePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clips.put(resourcePath, clip);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading WAV sound: " + resourcePath);
        }
    }

    public static void playSoundOnce(String resourcePath) {
        if (disableSound) return;

        if (resourcePath.endsWith(".mp3")) {
            playMP3(resourcePath, false);
            return;
        }

        try {
            InputStream is = Sound.class.getResourceAsStream("/" + resourcePath);
            if (is == null) {
                System.out.println("Music resource not found: " + resourcePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing WAV sound: " + resourcePath);
        }
    }

    public static void playMusicOnLoop(String resourcePath) {
        if (disableSound) return;

        if (resourcePath.endsWith(".mp3")) {
            MediaPlayer existing = mediaPlayers.get(resourcePath);
            if (existing != null && existing.getStatus() == MediaPlayer.Status.PLAYING) {
                return; // already playing
            }
            playMP3(resourcePath, true);
            return;
        }

        Clip clip = clips.get(resourcePath);
        if (clip == null) {
            loadSound(resourcePath);
            clip = clips.get(resourcePath);
        }

        if (clip != null && !clip.isRunning()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            loopingClips.put(resourcePath, true);
            clip.start();
        }
    }

    private static void playMP3(String resourcePath, boolean loop) {
        try {
            MediaPlayer existing = mediaPlayers.get(resourcePath);
            if (existing != null) {
                existing.stop();
                existing.dispose();
                mediaPlayers.remove(resourcePath);
                loopingMedia.remove(resourcePath);
            }

            InputStream resourceStream = Sound.class.getResourceAsStream("/" + resourcePath);
            if (resourceStream == null) {
                System.out.println("MP3 resource not found: " + resourcePath);
                return;
            }

            File tempFile = File.createTempFile("sound_", ".mp3");
            tempFile.deleteOnExit();

            try (var out = new java.io.FileOutputStream(tempFile)) {
                resourceStream.transferTo(out);
            }

            Media media = new Media(tempFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            loopingMedia.put(resourcePath, loop);

            if (loop) {
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            } else {
                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.dispose();
                    mediaPlayers.remove(resourcePath);
                    loopingMedia.remove(resourcePath);
                });
            }

            mediaPlayer.play();
            mediaPlayers.put(resourcePath, mediaPlayer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing MP3: " + resourcePath);
        }
    }

    public static void stopMusic(String resourcePath) {
        if (disableSound) return;

        if (resourcePath.endsWith(".mp3")) {
            MediaPlayer player = mediaPlayers.remove(resourcePath);
            if (player != null) {
                player.stop();
                player.dispose();
                loopingMedia.remove(resourcePath);
            }
            return;
        }

        Clip clip = clips.remove(resourcePath);
        if (clip != null) {
            clip.stop();
            clip.close();
            loopingClips.remove(resourcePath);
        }
    }

    public static void stopAllSounds() {
        if (disableSound) return;

        var clipKeys = new ArrayList<>(loopingClips.keySet());
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

        var mediaKeys = new ArrayList<>(loopingMedia.keySet());
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

    // Specific sound examples - use relative paths from resources root
    public static void click() {
        if (disableSound) return;
        playSoundOnce("music/click.wav");
    }

    public static void exitBall(Pokemon pkm) {
        playSoundOnce("music/catchFail.mp3");
        if (pkm.isShiny()) playSoundOnce("music/shinySparkles.mp3");
    }
}
