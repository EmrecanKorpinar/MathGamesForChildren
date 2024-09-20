import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private PlayGame playGame;
    String operation;

    private int score;
    private int score2;
    private Random random = new Random();
    private final int DELAY = 10;
    private final int PlayerX = 200;
    private final int PlayerY = 450;
    private int tmp = 10;

    private int sec;
    ImageIcon imageIcon = new ImageIcon("C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\background.png");
    protected Image backGround = imageIcon.getImage();

    private ArrayList<Fruit> fruitArrayList = new ArrayList<>();
    Player player;
    Timer timer;
    Player2 player2;
    private boolean isTwoPlayerGame;
    PlayMenu playMenu;
    private boolean endgame;

    public void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                gameSpeed = 1;
                break;
            case "Medium":
                gameSpeed = 2;
                break;
            case "Hard":
                gameSpeed = 3;
                break;
        }
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    private void initBoard() {
        setFocusable(true);
        setBackground(Color.WHITE); // Set background color to white
        addKeyListener(new TAdapter());
        player = new Player(100, 100); // Example coordinates
        if (isTwoPlayerGame) {
            player2 = new Player2(1600, 100); // Example coordinates
        }
        timer = new Timer(10, this);
        timer.start();
    }

    public static int gameSpeed = 1;

    public Board(String operation, PlayGame playGame, PlayMenu playMenu, boolean isTwoPlayerGame) {
        this.operation = operation;
        this.playGame = playGame;
        this.playMenu = playMenu;
        this.isTwoPlayerGame = isTwoPlayerGame;
        initBoard();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isTwoPlayerGame && player2 != null) {
            player2.move();
        }
        if (player != null) {
            player.move();
        }
        updateFruits();
        checkCollisionWithPrize();
        sec();
        repaint();
        endGame();
    }

    private void showPauseScreen() {
        timer.stop();
        PauseScreen pauseScreen = new PauseScreen(this);
        pauseScreen.setVisible(true);
    }

    public void resumeGame() {
        timer.start();
    }

    public PlayGame getPlayGame() {
        return playGame;
    }

    private void drawObjects(Graphics g) {
        g.drawImage(backGround, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d = (Graphics2D) g;
        if (player.isVisible()) {
            g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        if (isTwoPlayerGame && player2 != null && player2.isVisible()) {
            g2d.drawImage(player2.getImage(), player2.getX(), player2.getY(), this);
        }
        if (!fruitArrayList.isEmpty()) {
            for (int i = fruitArrayList.size() - 1; i >= 0; i--) {
                if (fruitArrayList.get(i).isVisible()) {
                    g2d.drawImage(fruitArrayList.get(i).getImage(), fruitArrayList.get(i).getX(), fruitArrayList.get(i).getY(), this);
                }
            }
        }
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));

        String operationText = "İşlem = " + operation;
        g2d.drawString(operationText, 50, 50);

        String scoreText = "Player 1 = " + score;
        g2d.drawString(scoreText, 200, 50);

        if (isTwoPlayerGame) {
            String score2Text = "Player 2 = " + score2;
            g2d.drawString(score2Text, 350, 50);
        }

        String secText = "Süre = " + sec;
        g2d.drawString(secText, 500, 50);

        if (endgame) {
            g2d.setColor(new Color(139, 69, 19));
            g2d.fillRect((getWidth() - 550) / 2, (getHeight() - 550) / 2, 550, 550);
            g2d.setColor(Color.black);
            g2d.drawString("Player1", 180, 140);
            g2d.drawString("Player2", 380, 140);
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    g2d.drawString((i + 1) + " süre: " + GameScore.sec[i] + " Sonuç: " + GameScore.score[i], 150, (150 + (i + 1) * 20));
                } else {
                    // Additional drawing logic
                }
            }
        }
        for (int i = 0; i < GameScore.name.length; i++) {
            g2d.drawString(GameScore.name[i], 0, (150 + (i + 1) * 20));
        }
    }

    private void updateMyCharacter() {
        if (player.isVisible()) {
            player.move();
        }
        if (isTwoPlayerGame && player2 != null && player2.isVisible()) {
            player2.move();
        }
    }

    private void updateFruits() {
        if (tmp == 0 && sec % 1 == 0) {
            fruitArrayList.add(new Fruit(random.nextInt(getWidth() - 100) + 100, -200));
            fruitArrayList.add(new Fruit(random.nextInt(getWidth() - 60) + 70, -50));// Ad
        }
        if (!fruitArrayList.isEmpty()) {
            for (int i = fruitArrayList.size() - 1; i >= 0; i--) {
                fruitArrayList.get(i).move();
                if (fruitArrayList.get(i).getY() >= 1000 || !fruitArrayList.get(i).isVisible()) {
                    fruitArrayList.remove(i);
                }
            }
        }
    }

    private void sec() {
        if (tmp < 1000)
            tmp += DELAY;
        else {
            tmp = 0;
            sec++;
        }
    }

    public void updateScore(Object player, int point) {
        if (player instanceof Player) {
            score += point;
        } else if (player instanceof Player2) {
            score2 += point;
        }
    }

    public void checkCollisionWithPrize() {
        checkCollisionForPlayer(player);
        if (isTwoPlayerGame && player2 != null) {
            checkCollisionForPlayer(player2);
        }
    }

    private void checkCollisionForPlayer(Object player) {
        Rectangle r3;
        if (player instanceof Player) {
            r3 = ((Player) player).getBounds();
        } else {
            r3 = ((Player2) player).getBounds();
        }
        int playerMiddleY = r3.y + r3.height / 2;

        for (Fruit fruit : fruitArrayList) {
            Rectangle r2 = fruit.getBounds();

            if (r2.intersects(r3.x, playerMiddleY, r3.width, r3.height / 2)) {
                if (fruit.rnd > 2) {
                    updateScore(player, fruit.rnd - 2);
                } else {
                    updateScore(player, fruit.rnd - 3);
                }
                fruit.setVisible(false);
                playCollisionMusic();
            }
        }
    }
    private void playCollisionMusic() {
        try {
            File musicFile = new File("C:\\Users\\dell\\Downloads\\fx.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(6.0f);

            clip.start();
            new Timer(900, e -> clip.close()).start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private int checkPoint() {
        int c = 0;
        if (operation.indexOf("+") > 0) {
            c = operation.indexOf("+");
        }
        if (operation.indexOf("-") > 0) {
            c = operation.indexOf("-");
        }
        if (operation.indexOf("x") > 0) {
            c = operation.indexOf("x");
        }
        if (operation.indexOf("/") > 0) {
            c = operation.indexOf("/");
        }

        int x = Integer.parseInt(operation.substring(0, c));
        int y = Integer.parseInt(operation.substring(c + 1));
        if ("+".equals(operation.substring(c, c + 1)))
            return x + y;
        if ("-".equals(operation.substring(c, c + 1)))
            return x - y;
        if ("x".equals(operation.substring(c, c + 1)))
            return x * y;

        return x / y;
    }

    private void endGame() {
        int result = checkPoint();
        if (score == result || score2 == result) {
            GameScore.score[GameScore.round] = Math.max(score, score2);
            GameScore.sec[GameScore.round] = sec;
            System.out.println(GameScore.score[GameScore.round]);
            GameScore.round++;
            if (GameScore.round == 10) {
                endgame = true;
                String winner = score > score2 ? "Player 1" : "Player 2";
                GameOverScreen gameOverScreen = new GameOverScreen(winner, sec);
                gameOverScreen.setVisible(true);
                this.setVisible(false);
            } else {
                this.setVisible(false);
                playGame.setVisible(false);
                playMenu.setVisible(true);
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                showPauseScreen();
            } else {
                player.keyPressed(e);
                if (isTwoPlayerGame && player2 != null) {
                    player2.keyPressed(e);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            if (isTwoPlayerGame && player2 != null) {
                player2.keyReleased(e);
            }
        }
    }
}