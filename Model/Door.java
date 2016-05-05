package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class Door extends Tile {
    private String imgPath = "Images/door.png";
    private transient BufferedImage img = null;
    private boolean isOpen = false;

    public Door(int positionX, int positionY) {
        super(positionX, positionY);
        setIsWalkable(false);
        setIsBreakable(false);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {

        }
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
        setIsWalkable(true);
    }

    public BufferedImage getImage() {
        return img;
    }
}
