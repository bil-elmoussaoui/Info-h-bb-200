package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Coin extends Item{
    public int value = 10;
    public boolean animated = true;
    private BufferedImage img;
    private String imgPath = "Images/coin_gold.png";
    public Counter counter;

    public Coin(int positionX, int positionY){
        super(positionX, positionY);
        this.setIsCollectable(true);
        this.setIsBreakable(false);
        this.setIsWalkable(true);
        counter = new Counter(7);
        value = (int) Math.floor(Math.random()*10);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getValue(){
        return this.value;
    }

    public void setImage(BufferedImage img){
        this.img = img;
    }

    public BufferedImage getImage() {
        return img.getSubimage(counter.getCounter() *(img.getWidth()/8), 0, 32, 32);
    }

}
