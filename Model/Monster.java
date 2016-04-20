package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monster extends Person implements Runnable{
	private Thread thread;
    private int waitTime = 250;
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
                if (position != null) {
                    this.move(position[0], position[1]);
                    this.game.refreshMap();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static BufferedImage draw(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/zombie.gif"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

}
