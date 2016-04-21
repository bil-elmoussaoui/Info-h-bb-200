package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
TODO :
 - implement a random value for coins! and an image for each coin interval value :)
 */


public class Coin extends Item{

    private int positionX;
    private int positionY;
    private int value = 10;
    private static BufferedImage img;

    public Coin(int positionX, int positionY){
        super(positionX, positionY);

        try {
            img = ImageIO.read(new File("Images/coin1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getValue(){
        return this.value;
    }

    public static void draw(Graphics g, int x, int y) {
        g.drawImage(Coin.img, x * 24, y * 24, 24, 24, null);
    }
}
