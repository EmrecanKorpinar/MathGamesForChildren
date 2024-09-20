import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Player extends Sprite implements PlayerBase {
    private int dx;
    private int dy;
    private int dSpeed = 2;
    private boolean jumping = false;
    private boolean canDoubleJump = false;
    private int jumpVelocity = -15;
    private int gravity = 1;
    private int groundY;
    private Clip jumpClip;
    public void drawPlayer(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public Player(int x, int y) {
        super(x, y);
        initPlayer();
        this.groundY = 1048 - this.getHeight();
        this.y = groundY;
        loadJumpSound("C:\\Users\\dell\\Downloads\\jump.wav");
    }

    private void initPlayer() {
        loadImage("C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\bukelamun.png");
        getImageDimensions();
        this.width = getWidth() * 2; // Double the width
        this.count = getHeight() * 2; // Double the height
        scaleImage();
    }

    private void scaleImage() {
        Image originalImage = getImage();
        Image scaledImage = originalImage.getScaledInstance(width, count, Image.SCALE_SMOOTH);
        setImage(scaledImage);
    }

    private void loadJumpSound(String soundPath) {
        try {
            File musicFile = new File(soundPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            jumpClip = AudioSystem.getClip();
            jumpClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playJumpMusic() {
        if (jumpClip != null) {
            jumpClip.setFramePosition(0); // Rewind to the beginning
            jumpClip.start();
        }
    }

    public void move() {
        x += dx;
        if (x < 0) {
            x = 0;
        }
        if (x > 1920 - getWidth()) {
            x = 1920 - getHeight();
        }

        if (jumping) {
            y += dy;
            dy += gravity;
            if (y >= groundY) {
                y = groundY;
                jumping = false;
                canDoubleJump = false;
                dy = 0;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = -3 * dSpeed;

        if (key == KeyEvent.VK_RIGHT)
            dx = 3 * dSpeed;

        if (key == KeyEvent.VK_SPACE) {
            if (!jumping) {
                jumping = true;
                dy = jumpVelocity;
                canDoubleJump = true;
                playJumpMusic();
            } else if (canDoubleJump) {
                dy = jumpVelocity;
                canDoubleJump = false;
                playJumpMusic();
            }
        }

        if (key == KeyEvent.VK_UP)
            if (dSpeed < 5)
                dSpeed = ++dSpeed;

        if (key == KeyEvent.VK_DOWN)
            if (dSpeed > 1)
                dSpeed = --dSpeed;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = 0;

        if (key == KeyEvent.VK_RIGHT)
            dx = 0;
    }
}




