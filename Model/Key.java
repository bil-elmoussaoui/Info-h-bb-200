package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Key extends Item{
    public boolean animated = false;
    private BufferedImage img;
    private String imgPath = "Images/key.png";

    public Key(int positionX, int positionY){
        super(positionX, positionY);
        setIsCollectable(true);
        setIsBreakable(false);
        setIsWalkable(true);
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
