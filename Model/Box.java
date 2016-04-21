package Model;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Box extends Item{
    public Box(int positionX, int positionY){
        super(positionX, positionY);
    }

    public void open(){
        // do nothing!
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
