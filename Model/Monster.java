package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monster extends Person{
    private BufferedImage img;
    private String imgPath = "Images/monster.png";
    FieldOfView FOV;

	public Monster(int positionX, int positionY) {
        super(positionX, positionY, 8);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


    public BufferedImage getImage() {
         return img.getSubimage(counter.getCounter()*64, (direction - 1)*64, 64, 64);
    }

}
