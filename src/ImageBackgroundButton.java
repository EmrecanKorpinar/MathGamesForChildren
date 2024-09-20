import javax.swing.*;
import java.awt.*;

class ImageBackgroundButton extends JButton {
    private Image backgroundImage;

    public ImageBackgroundButton(String text, String imagePath) {
        super(text);
        setBackgroundImage(imagePath);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    public void setBackgroundImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}