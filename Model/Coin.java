package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Coin extends Item {
    public Counter counter;
    private int value;
    private transient BufferedImage img;
    private String imgPath = "Images/coin_gold.png";

    public Coin(int positionX, int positionY) {
        super(positionX, positionY);
        setIsCollectable(true);
        setIsBreakable(false);
        setIsWalkable(true);
        counter = new Counter(7);
        value = (int) Math.floor(Math.random() * 10) + 1;
        createImage();
    }

    public void createImage(){
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getValue() {
        return value;
    }

    public BufferedImage getImage() {
        return img.getSubimage(counter.getCounter() * (img.getWidth() / 8), 0, 32, 32);
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

}
