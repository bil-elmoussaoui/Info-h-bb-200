package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FireLion extends Spell {
    public transient BufferedImage img = null;
    public String imgPath;

    public FireLion(int positionX, int positionY, int direction) {
        super(positionX, positionY, direction);
        setIsMovingSpell(true);
        switch (direction) {
            case 1:
                imgPath = "Images/firelion_down.png";
                break;
            case 3:
                imgPath = "Images/firelion_up.png";
                break;
            case 2:
                imgPath = "Images/firelion_left.png";
                break;
            case 4:
                imgPath = "Images/firelion_right.png";
                break;
        }
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createImage() {
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
