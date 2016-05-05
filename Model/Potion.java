package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/*
TODO:
- handle exceptions
 */

public class Potion extends Item {
    int type = 0;
    private double health;
    private transient BufferedImage img;
    private String imgPath = "Images/health-potion.png";

    public Potion(Integer positionX, Integer positionY) {
        super(positionX, positionY);
        setIsCollectable(true);
        setIsBreakable(false);
        int ran = new Random().nextInt(2);
        if (ran >= 0 && ran < 0.5) {
            setHealth(0.5);
        } else {
            setHealth(1);
        }
        setIsWalkable(true);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        switch (type) {
            case 0:
                imgPath = "Images/health-potion.png";
                break;
            case 1:
                imgPath = "Images/firelion-potion.png";
                break;
            case 2:
                imgPath = "Images/icetacle-potion.png";
                break;
        }
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public BufferedImage getImage() {
        return img;
    }

}
