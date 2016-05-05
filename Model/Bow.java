package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bow extends Weapon {
    public String imgPath = "Images/weapon-bow.png";
    public transient BufferedImage img = null;
    public transient BufferedImage staticImg = null;
    public String staticImgPath = "Images/upg_bow.png";
    public int arrowsCount = 1;
    public Arrow arrow;

    public Bow(Integer positionX, Integer positionY) {
        super(positionX, positionY, 1, 12);
        setIsDistanceWeapon(true);
        try {
            img = ImageIO.read(new File(imgPath));
            staticImg = ImageIO.read(new File(staticImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addArrows(int arrows) {
        this.arrowsCount += arrows;
        if (this.arrowsCount < 0) {
            this.arrowsCount = 0;
        }
    }

    public int getArrowsCount() {
        return arrowsCount;
    }

    public BufferedImage getStaticImg() {
        return staticImg;
    }

    public BufferedImage getImage() {
        BufferedImage buffer = img.getSubimage(counter.getCounter() * 64, (getDirection() - 1) * 64, 64, 64);
        BufferedImage bufferArrow = null;
        if (arrow != null && arrowsCount > 0) {
            if (!arrow.beenThrown) {
                bufferArrow = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2Arrow = bufferArrow.createGraphics();
                g2Arrow.drawImage(buffer, null, null);
                Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
                g2Arrow.setComposite(newComposite);
                g2Arrow.drawImage(arrow.getImage(), null, null);
                g2Arrow.dispose();
            }
        } else {
            bufferArrow = buffer;
        }
        return bufferArrow;
    }
}
