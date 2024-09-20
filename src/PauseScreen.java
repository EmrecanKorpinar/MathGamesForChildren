import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends JDialog {
    private Board board;

    public PauseScreen(Board board) {
        super((JFrame) SwingUtilities.getWindowAncestor(board), "Pause", true);
        this.board = board;
        setLayout(new BorderLayout());

        // Background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\tropikal.png");
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setPreferredSize(new Dimension(800, 500));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 5)); // Minimal gap

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 40));
        continueButton.setBackground(new Color(0, 128, 0));
        continueButton.setForeground(Color.WHITE);
        continueButton.setPreferredSize(new Dimension(200, 80));
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resumeGame();
                dispose();
            }
        });

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 40));
        mainMenuButton.setBackground(new Color(128, 0, 0));
        mainMenuButton.setForeground(Color.WHITE);
        mainMenuButton.setPreferredSize(new Dimension(200, 80));
        mainMenuButton.setContentAreaFilled(false);
        mainMenuButton.setBorderPainted(false);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.initUI();
                mainMenu.showMainMenu();
                mainMenu.setVisible(true);
                board.getPlayGame().dispose();
                dispose();
            }
        });

        buttonPanel.add(continueButton);
        buttonPanel.add(mainMenuButton);

        // Difficulty panel
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setOpaque(false);
        difficultyPanel.setLayout(new GridLayout(1, 3, 1, 1));

        JButton easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Arial", Font.BOLD, 24));
        easyButton.setBackground(new Color(0, 0, 128));
        easyButton.setForeground(Color.WHITE);
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.setDifficulty("Easy");
            }
        });

        JButton mediumButton = new JButton("Medium");
        mediumButton.setFont(new Font("Arial", Font.BOLD, 24));
        mediumButton.setBackground(new Color(0, 0, 128));
        mediumButton.setForeground(Color.WHITE);
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.setDifficulty("Medium");
            }
        });

        JButton hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Arial", Font.BOLD, 24));
        hardButton.setBackground(new Color(0, 0, 128));
        hardButton.setForeground(Color.WHITE);
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.setDifficulty("Hard");
            }
        });

        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(difficultyPanel, BorderLayout.SOUTH);

        add(backgroundPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(board);
    }
}