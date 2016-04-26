package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
 TODO :
 - implement sword draw function and animation
 */
public class Dagger extends Weapon {
    public String imgPath = "Images/weapon-dagger.png";
    public BufferedImage img = null;

    public Dagger(int damage){
        super(damage, 5);
        try {
            img = ImageIO.read(new File(imgPath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return img.getSubimage(this.counter.getCounter()*64, (this.getDirection() - 1)*64, 64, 64);
    }
}
