import javax.swing.*;
import java.awt.*;

class TwoPlayerMenu extends JFrame {

    public void initUI() {
        setTitle("Two Player Menu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Example UI components
        JLabel label = new JLabel("Two Player Mode", JLabel.CENTER);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        add(label, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.initUI();
            mainMenu.setVisible(true);
            this.setVisible(false);
        });
        add(backButton, BorderLayout.SOUTH);



    }

}