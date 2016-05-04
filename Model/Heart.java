package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

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

    public Heart(Integer positionX, Integer positionY){
        super(positionX, positionY);
        setIsCollectable(true);
        setIsBreakable(false);
        int ran = new Random().nextInt(2);
        if(ran >= 0 && ran < 0.5){
            setHealth(0.5);
        }else{
            setHealth(1);
        }        setIsWalkable(true);
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
