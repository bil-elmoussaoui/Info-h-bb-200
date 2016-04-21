package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
TODO:
- Implement box opening animation
- Implement random box item in the WoodBox class instead of the Game class!
 */

public class WoodBox extends Item{
    public WoodBox(int positionX, int positionY){
        super(positionX, positionY);
    }

    public void open(){
        System.out.println("opened");
    }

    public static void draw(Graphics g, int x, int y) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/Wood_Box.png"));
            g.drawImage(img, x * 24, y * 24, 24, 24, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
