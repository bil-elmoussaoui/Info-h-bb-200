package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;

public class Wall extends Tile{
    private String imgPath;
    private BufferedImage img;

    public Wall(int positionX, int positionY){
        super(positionX, positionY);
        Game.freePositions[positionX][positionY] = 1;
        imgPath = "Images/StoneWallStandard.png";
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return img;
    }
}
