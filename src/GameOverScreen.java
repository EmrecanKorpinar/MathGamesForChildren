import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame {
    public GameOverScreen(String winner, int duration) {
        setTitle("Game Over");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel winnerLabel = new JLabel("Winner: " + winner, JLabel.CENTER);
        winnerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        JLabel durationLabel = new JLabel("Duration: " + duration + " seconds", JLabel.CENTER);
        JLabel thanksLabel = new JLabel("Emrecan Körpınar", JLabel.CENTER);
        thanksLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 48));

        JButton restartButton = new JButton("Back to Menu");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the GameOverScreen
                new PlayMenu().setVisible(true); // Restart the game by showing the PlayMenu
            }
        });

        setLayout(new GridLayout(4, 1));
        add(winnerLabel);
        add(durationLabel);
        add(thanksLabel);
        add(restartButton);
    }
}