package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
TODO:
- draw image as for other classes!
- implement the salesman!
 */
public class Salesman extends Item {

    public Salesman(int positionX, int positionY){
        super(positionX, positionY);
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
