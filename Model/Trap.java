package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by bilal la keh on 27/04/16.
 */
public class Trap extends Tile{
    private int x = 0;
    private int y = 0;
    private int PositionX;
    private int PositionY;
    public Counter counter;
    public BufferedImage img = null;
    private int damage;
    public String imgPath = "Images/tornado.png";

    public Trap(int PositionX, int PositionY){
        super(PositionX, PositionY);
        setIsDangerous(true);
        counter = new Counter(15);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void attack(Person person){
        person.setHealth(person.getHealth()-1);
    }



    public BufferedImage getImage() {
        /*if(counter.getCounter() %3 == 0){
            y += 1;
        }*/
        BufferedImage tournadoImage;
        if(counter.getCounter() <= 3) {
            tournadoImage = img.getSubimage(counter.getCounter() * 128, 0, 128, 128);
        } else if(counter.getCounter() <= 7 && counter.getCounter() > 3 ){
            tournadoImage = img.getSubimage((counter.getCounter() - 4) * 128, 128, 128, 128);
        } else if(counter.getCounter() <= 11 && counter.getCounter() > 7 ){
            tournadoImage = img.getSubimage((counter.getCounter() - 8)* 128, 256, 128, 128);
        } else {
            tournadoImage = img.getSubimage((counter.getCounter() - 12) * 128, 384, 128, 128);
        }
        return tournadoImage;
    }
}
