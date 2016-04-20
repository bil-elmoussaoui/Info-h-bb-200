package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WoodBox extends Tile {

    public WoodBox(int positionX, int positionY){
        super(positionX, positionY);
    }

    public static BufferedImage draw(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/Wood_Box.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
