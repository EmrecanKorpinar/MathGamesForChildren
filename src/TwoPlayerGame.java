import javax.swing.*;

public class TwoPlayerGame extends JFrame {

    private String operation;
    private PlayMenu playMenu;

    public TwoPlayerGame(String operation, PlayMenu playMenu) {
        this.operation = operation;
        this.playMenu = playMenu;
        initUI();
    }


    public void initUI() {
        setTitle("Two Player Game");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boolean isTwoPlayerGame = true; // Set this flag for two-player game
        PlayGame playGame = new PlayGame(operation, playMenu, isTwoPlayerGame);
        Board board = new Board(operation, playGame, playMenu, isTwoPlayerGame);
        add(board);

        setVisible(true); // Ensure the frame is visible
    }
}