import java.util.Random;

public class Fruit extends Sprite{


    private final int dy = 3;
    Random random = new Random();
    int rnd = random.nextInt(4) +1;
    public Fruit(int x, int y) {
        super(x, y);
        initFruit();
    }

    private void initFruit() {
        loadImage("C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\meyve" + rnd + ".png");
        getImageDimensions();
    }

    public void move() {
        y += dy * Board.gameSpeed*1.3;
    }
}
