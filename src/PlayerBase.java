import java.awt.*;
import java.awt.event.KeyEvent;

public interface PlayerBase {
    void move();
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
    int getX();
    int getY();
    boolean isVisible();
    Image getImage();
    Rectangle getBounds();
}