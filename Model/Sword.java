package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
/*
 TODO :
 - implement sword draw function and animation
 */
public class Sword extends Weapon {

    public Sword(int damage){
        super(damage);
    }

    public BufferedImage draw(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/sword.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
