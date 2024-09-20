import javax.swing.*;
import java.awt.*;

public class PlayGame extends JFrame {
    String operation;
    PlayMenu playMenu;
    boolean isTwoPlayerGame;





    public PlayGame(String operation, PlayMenu playMenu, boolean isTwoPlayerGame) {
        this.playMenu = playMenu;
        this.operation = operation;
        this.isTwoPlayerGame = isTwoPlayerGame;
        initUI();
    }
    public PlayGame(String operation, PlayMenu playMenu) {
        this(operation, playMenu, false); // Default to single-player game
    }



    public void initUI() {
        setLayout(new BorderLayout());
        setSize(1920, 1080);

        setResizable(true);
        add(new Board(operation, this, playMenu, isTwoPlayerGame));
        setTitle("Math Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
    }
}


//import javax.swing.*;
//import java.awt.*;
//
//public class PlayGame extends JFrame {
//    String operation;
//    PlayMenu playMenu;
//
//    PlayGame(String operation, PlayMenu playMenu) {
//        this.playMenu = playMenu;
//        this.operation = operation;
//
//        initUI();
//    }
//
//
//    public void initUI() {
//        setLayout(new BorderLayout());
//        setSize(1920, 1080);
//
//        setResizable(true);
//        add(new Board(operation, this, playMenu));
//        setTitle("Math Game");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(false);
//    }
//}


















//import javax.swing.*;
//import java.awt.*;
//
//public class PlayGame extends JFrame {
//    String operation;
//    PlayMenu playMenu;
//
//    PlayGame(String operation, PlayMenu playMenu) {
//        this.playMenu = playMenu;
//        this.operation = operation;
//        initUI();
//    }
//
//    public void initUI() {
//        setLayout(new BorderLayout());
//        setSize(1920, 1080);
//
//        setResizable(true);
//        add(new Board(operation, this, playMenu));
//        setTitle("Math Game");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(false);
//    }
//
//}

//import javax.swing.*;
//import java.awt.*;
//
//public class PlayGame extends JFrame {
//    String operation;
//    PlayMenu playMenu;
//
//    PlayGame(String operation, PlayMenu playMenu) {
//        this.playMenu = playMenu;
//        this.operation = operation;
//
//        initUI();
//    }
//
//
//    public void initUI() {
//        setLayout(new BorderLayout());
//        setSize(1920, 1080);
//
//        setResizable(true);
//        add(new Board(operation, this, playMenu));
//        setTitle("Math Game");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(false);
//    }
//}


















//import javax.swing.*;
//import java.awt.*;
//
//public class PlayGame extends JFrame {
//    String operation;
//    PlayMenu playMenu;
//
//    PlayGame(String operation, PlayMenu playMenu) {
//        this.playMenu = playMenu;
//        this.operation = operation;
//        initUI();
//    }
//
//    public void initUI() {
//        setLayout(new BorderLayout());
//        setSize(1920, 1080);
//
//        setResizable(true);
//        add(new Board(operation, this, playMenu));
//        setTitle("Math Game");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(false);
//    }
//
//}
