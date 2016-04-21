package Model;

import View.MainWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monster extends Person implements Runnable{
	private Thread thread;
    private int waitTime = 5000;
    private Game game;
    FieldOfView FOV;

	public Monster(int positionX, int positionY, Game game) {
        super(positionX, positionY);
        this.thread = new Thread(this);
        this.game = game;
        this.thread.start();
	}

    public void run(){
        try{
            while(true) {
                Thread.sleep(this.waitTime);
                int[] position = this.getRandomPosition();
                if (position != null && !MainWindow.gamePaused) {
                    move(position[0], position[1]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void draw(Graphics g, int x, int y) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/zombie.gif"));
            g.drawImage(img, x * 24, y * 24, 24, 24, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
