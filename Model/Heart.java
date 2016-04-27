package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/*
TODO:
- implement a random value of health! (only int)
- handle exceptions
 */

public class Heart extends Item {
    private int positionX;
    private int positionY;
    private int health = 1;
    private BufferedImage img;
    private String imgPath = "Images/health-potion.png";

    public Heart(int positionX, int positionY){
        super(positionX, positionY);
        this.setIsCollectable(true);
        this.setIsBreakable(false);
        this.setIsWalkable(true);
        try {
            img = ImageIO.read(new File(imgPath));
        }catch (Exception e) {
        }
    }

    public int getHealth(){
        return this.health;
    }

    public BufferedImage getImage() {
        return img;
    }

}
