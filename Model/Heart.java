package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/*
TODO:
- implement a random value of health! (only int)
- handle exceptions
 */

public class Heart extends Item {
    private int positionX;
    private int positionY;
    private int health = 1;
    private static BufferedImage img;

    public Heart(int positionX, int positionY){
        super(positionX, positionY);
        try {
            img = ImageIO.read(new File("Images/Images/heart.png"));
        }catch (Exception e) {
        }
    }

    public int getHealth(){
        return this.health;
    }

    public static void draw(Graphics g, int x, int y) {
        g.drawImage(img, x * 32, y * 32, 32, 32, null);
    }

}
