package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class Tornado extends Trap {
    public Counter counter;
    public transient BufferedImage img = null;
    public String imgPath = "Images/tornado.png";

    public Tornado(int positionX, int positionY) {
        super(positionX, positionY);
        counter = new Counter(15);
        setDamage(1);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createImage(){
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        BufferedImage tornadoImage;
        if (counter.getCounter() <= 3) {
            tornadoImage = img.getSubimage(counter.getCounter() * 128, 0, 128, 128);
        } else if (counter.getCounter() <= 7 && counter.getCounter() > 3) {
            tornadoImage = img.getSubimage((counter.getCounter() - 4) * 128, 128, 128, 128);
        } else if (counter.getCounter() <= 11 && counter.getCounter() > 7) {
            tornadoImage = img.getSubimage((counter.getCounter() - 8) * 128, 256, 128, 128);
        } else {
            tornadoImage = img.getSubimage((counter.getCounter() - 12) * 128, 384, 128, 128);
        }
        return tornadoImage;
    }

}
