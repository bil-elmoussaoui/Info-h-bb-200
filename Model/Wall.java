package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall extends Item{

    public Wall(int positionX, int positionY){
        super(positionX, positionY);
    }

    public static BufferedImage draw(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/StoneWallStandard.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

}
