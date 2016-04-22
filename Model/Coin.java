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


public class Coin extends Item implements Runnable{

    private int positionX;
    private int positionY;
    private int value = 10;
    private int counter = 0;
    private static BufferedImage img;
    private Game game;
    private Thread thread;

    public Coin(int positionX, int positionY){
        super(positionX, positionY);
        try {
            Coin.img = ImageIO.read(new File("Images/coin_gold.png"));
            Coin.img = Coin.img.getSubimage(0, 0, 32, 32);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void setGame(Game game){
        this.game = game;
    }

    @Override
    public void run(){
        try{
        while(true){
            Thread.sleep(250);
            Coin.img = Coin.img.getSubimage(counter*32, counter*32, 32, 32);
            counter += 1;
            if(counter > 8){
                counter = 0;
            }
            MainWindow.levelMap.setVisible(false);
            MainWindow.levelMap.setVisible(true);
        }
        }catch (Exception e){

        }
    }

    public int getValue(){
        return this.value;
    }

    public static void draw(Graphics g, int x, int y) {
        g.drawImage(Coin.img, x * 32, y * 32, 32, 32, null);
    }
}
