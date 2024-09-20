import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private Music music;

    public void initUI() {
        if (music != null) {
            music.stopMusic();
        }
        music = new Music();
        music.playMusic("resources/music.wav");

        // Tropikal arka plan resmini içeren JLabel
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\tropikal2.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);    

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // "Math Game" yazısını içeren JLabel
        JLabel titleLabel = new JLabel("Math Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        titleLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yapar

        // Tahta resmini içeren JLabel
        ImageIcon boardIcon = new ImageIcon("C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\sing.png");
        JLabel boardLabel = new JLabel(boardIcon);

        // JPanel kullanarak butonları içeren kısım
        ImageIcon playIcon = new ImageIcon("C:\\Users\\dell\\Downloads\\TwoPlayer.png");
        JButton playButton = new JButton(playIcon);
        playButton.setPreferredSize(new Dimension(300, 100)); // Set preferred size
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPlayMenu(false);
            }
        });

        ImageIcon twoPlayerIcon = new ImageIcon("C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\Play.png");
        JButton twoPlayerButton = new JButton(twoPlayerIcon);
        twoPlayerButton.setPreferredSize(new Dimension(300, 100)); // Set preferred size
        twoPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPlayMenu(true);
            }
        });

        JPanel playButtonPanel = new JPanel();
        playButtonPanel.setOpaque(false);
        playButtonPanel.setPreferredSize(new Dimension(300, 200)); // Enlarge panel
        playButtonPanel.setLayout(new GridBagLayout()); // Center button
        playButtonPanel.add(playButton);

        JPanel twoPlayerButtonPanel = new JPanel();
        twoPlayerButtonPanel.setOpaque(false);
        twoPlayerButtonPanel.setPreferredSize(new Dimension(300, 200)); // Enlarge panel
        twoPlayerButtonPanel.setLayout(new GridBagLayout()); // Center button
        twoPlayerButtonPanel.add(twoPlayerButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(playButtonPanel);
        buttonPanel.add(twoPlayerButtonPanel);

        // LayeredPane kullanarak JLabel'leri üst üste koyma
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));

        // Z-order'ı belirleyerek JLabel'leri layeredPane'e ekleme
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(boardLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(titleLabel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        // JLabel'leri pozisyonlandırma
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        boardLabel.setBounds(0, 0, 1920, 500);
        titleLabel.setBounds(0, 0, 1920, 600);
        buttonPanel.setBounds(0, 715, 1920, 500);

        // Ana panele LayeredPane'i ekleme
        add(layeredPane);

        setTitle("Math Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showPlayMenu(boolean isTwoPlayerGame) {
        PlayMenu playMenu = new PlayMenu();
        playMenu.setTwoPlayerGame(isTwoPlayerGame);
        playMenu.initUI();
        playMenu.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.initUI();
            mainMenu.setVisible(true);
        });
    }

    public void showMainMenu() {
        if (music != null) {
            music.stopMusic();
        }
        this.setVisible(true);
    }
}