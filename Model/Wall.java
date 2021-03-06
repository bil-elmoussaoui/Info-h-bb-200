package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Wall extends Tile {
    private String imgPath = "Images/wall.png";
    private transient BufferedImage img;

    public Wall(int positionX, int positionY) {
        super(positionX, positionY);
        setIsBreakable(false);
        setIsWalkable(false);
        setIsDangerous(false);
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
        return img;
    }
}
