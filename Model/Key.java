package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by bilal on 20/04/16.
 */
public class Key extends Item{
    private int positionX;
    private int positionY;

    public Key(int positionX, int positionY){
        super(positionX, positionY);
    }


    public static void draw(Graphics g, int x, int y) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/Images/Key.png"));
            g.drawImage(img, x * 24, y * 24, 24, 24, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
