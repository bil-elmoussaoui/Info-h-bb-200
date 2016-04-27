package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
 TODO :
 - implement sword draw function and animation
 */
public class Arrow extends Bow {
    public String imgPath = "Images/weapon-arrow.png";
    public BufferedImage img = null;
    public Counter counter;
    private int positionX;
    private int positionY;

    public Arrow (Integer positionX, Integer positionY, int damage){
        super(null, null, damage);
        counter = new Counter(12);
        try {
            img = ImageIO.read(new File(imgPath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return img.getSubimage(counter.getCounter() *64, (this.getDirection() - 1)*64, 64, 64);
    }
}
