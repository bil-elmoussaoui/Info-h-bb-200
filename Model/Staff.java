package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 TODO :
- get a nice static image
 */
public class Staff extends Weapon {
    public String imgPath = "Images/weapon-staff.png";
    public String staticImgPath = "Images/sword_iron.png";
    public BufferedImage img = null;
    public BufferedImage staticImg = null;

    public Staff(Integer positionX, Integer positionY, int damage) {
        super(positionX, positionY, damage, 7);
        setIsDistanceWeapon(false);
        try {
            img = ImageIO.read(new File(imgPath));
            staticImg = ImageIO.read(new File(staticImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getStaticImg() {
        return staticImg;
    }

    public BufferedImage getImage() {
        return img.getSubimage(this.counter.getCounter() * 64, (this.getDirection() - 1) * 64, 64, 64);
    }
}
