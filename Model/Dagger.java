package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dagger extends Weapon {

    public transient BufferedImage img = null;
    public String imgPath = "Images/weapon-dagger.png";
    public transient BufferedImage staticImg = null;
    public String staticImgPath = "Images/upg_dagger.png";

    public Dagger(Integer positionX, Integer positionY) {
        super(positionX, positionY, 0.5, 5);
        setIsDistanceWeapon(false);
        try {
            img = ImageIO.read(new File(imgPath));
            staticImg = ImageIO.read(new File(staticImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return img.getSubimage(this.counter.getCounter() * 64, (this.getDirection() - 1) * 64, 64, 64);
    }

    public BufferedImage getStaticImg() {
        return staticImg;
    }
}
