package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by bilal on 20/04/16.
 */
public class Heart extends Item {
    private int positionX;
    private int positionY;
    private int health = 1;

    public Heart(int positionX, int positionY){
        super(positionX, positionY);
    }

    public int getHealth(){
        return this.health;
    }


    public static void draw(Graphics g, int x, int y) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/Images/heart.png"));
            g.drawImage(img, x * 24, y * 24, 24, 24, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
