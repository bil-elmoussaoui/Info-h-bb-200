package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class Spikes extends Trap {
    public Counter counter;
    public BufferedImage img = null;
    public String imgPath = "Images/spikes.png";

    public Spikes(int positionX, int positionY) {
        super(positionX, positionY);
        counter = new Counter(9);
        setDamage(0.5);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImage() {
        return img.getSubimage(counter.getCounter() * 64, 64, 64, 64);
    }

}
