package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Wall extends Tile{
    private String imgPath = "Images/wall.png";
    private BufferedImage img;

    public Wall(int positionX, int positionY){
        super(positionX, positionY);
        this.setIsBreakable(false);
        this.setIsWalkable(false);
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
