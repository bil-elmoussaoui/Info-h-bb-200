package Model;

import View.MainWindow;
import sun.applet.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
/*
TODO :
 - implement a random value for coins! and an image for each coin interval value :)
 */


public class Coin extends Item{
    public int value = 10;
    private int counter = 0;
    private BufferedImage img;
    private Game game;
    private Thread thread;

    public Coin(int positionX, int positionY){
        super(positionX, positionY);
        try {
            img = ImageIO.read(new File("Images/coin_gold.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upCounter() {
        counter += 1;
        if (counter > 8) {
            counter = 0;
        }
    }

    public int getValue(){
        return this.value;
    }

    public void setImage(BufferedImage img){
        this.img = img;
    }

    public BufferedImage getImage() {
        return img.getSubimage(counter *32, 0, 32, 32);
    }

}
