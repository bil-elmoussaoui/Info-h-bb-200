package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
TODO:
- Implement box opening animation
- Implement random box item in the WoodBox class instead of the Game class!
 */

public class WoodBox extends Item{
    public boolean breakable = true;
    public BufferedImage img;
    public String imgPath = "Images/Wood_Box.png";

    public WoodBox(int positionX, int positionY){
        super(positionX, positionY);
        this.setIsCollectable(false);
        this.setIsBreakable(true);
        this.setIsWalkable(false);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return img;
    }
}
