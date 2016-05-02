package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
 TODO :
 - implement arrow animation
 */
public class Arrow extends Bow {
    public String imgPath = "Images/weapon-arrow.png";
    public BufferedImage img = null;
    public Counter counter;
    public boolean beenThrown = false;

    public Arrow (int damage){
        super(null, null, damage);
        counter = new Counter(12);
        try {
            img = ImageIO.read(new File(imgPath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return img.getSubimage(counter.getCounter() *64, (getDirection() - 1)*64, 64, 64);
    }
}
