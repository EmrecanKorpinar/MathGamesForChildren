import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {

    public Main() {
        MainMenu mainMenu=new MainMenu();
        mainMenu.initUI();
        // PlayGame mainMenu =new PlayGame();
        mainMenu.setVisible(true);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main main = new Main();

            JFrame frame=new JFrame();

            GraphicsDevice device=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            device.setFullScreenWindow(main);
            device.setFullScreenWindow(frame);
            frame.setVisible(true);

        });
    }
}







//import javax.swing.*;
//import java.awt.*;
//
//
//public class Main extends JFrame {
//
//    public Main() {
//        MainMenu mainMenu=new MainMenu();
//        mainMenu.initUI();
//       // PlayGame mainMenu =new PlayGame();
//        mainMenu.setVisible(true);
//
//    }
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            Main main = new Main();
//
//            JFrame frame=new JFrame();
//
//            GraphicsDevice device=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//            device.setFullScreenWindow(main);
//            device.setFullScreenWindow(frame);
//            frame.setVisible(true);
//
//        });
//    }
//}
