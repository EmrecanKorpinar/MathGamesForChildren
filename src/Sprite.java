import javax.swing.*;
import java.awt.*;

public class Sprite {

    protected int x;
    protected int y;
    public int width;
    public int count;
    protected boolean visible;
    public Image image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }


    protected void loadImage(String imageName) {
        ImageIcon imageIcon = new ImageIcon(imageName);
        image = imageIcon.getImage();
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        count = image.getHeight(null);
    }


    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return count;
    }

    public Image getImage() {
        return image;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, count);
    }


}
