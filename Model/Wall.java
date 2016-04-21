package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;

public class Wall extends Tile{
    private static BufferedImage img;

    public Wall(int positionX, int positionY){
        super(positionX, positionY);
        try {
            img = ImageIO.read(new File("Images/StoneWallStandard.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void draw(Graphics g, int x, int y) {
        g.drawImage(img, x * 24, y * 24, 24, 24, null);
    }
}
