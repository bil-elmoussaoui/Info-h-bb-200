package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/*
TODO:
- implement a random value of health! (only int)
- handle exceptions
- change from health to (une potion??)
 */

public class Heart extends Item {
    private double health ;
    private BufferedImage img;
    private String imgPath = "Images/health-potion.png";

    public Heart(int positionX, int positionY){
        super(positionX, positionY);
        setIsCollectable(true);
        setIsBreakable(false);
        setHealth(Math.round(Math.random()));
        setIsWalkable(true);
        try {
            img = ImageIO.read(new File(imgPath));
        }catch (Exception e) {
        }
    }

    public double getHealth(){
        return health;
    }

    public void setHealth(double health){
        this.health = health;
    }

    public BufferedImage getImage() {
        return img;
    }

}
