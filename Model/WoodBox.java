package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/*
TODO:
- Implement box opening animation
 */

public class WoodBox extends Item{
    public boolean breakable = true;
    public BufferedImage img;
    public String imgPath = "Images/Wood_Box.png";
    public Item content;

    public WoodBox(int positionX, int positionY){
        super(positionX, positionY);
        this.setIsBreakable(true);
        this.setIsWalkable(false);
        Game.freePositions[positionX][positionY] = 1;
        Random randomItem = new Random();
        switch (randomItem.nextInt(4)) {
            case 0:
                content = new Coin(positionX, positionY);
            break;
            case 1:
                content = new Heart(positionX, positionY);
            break;
            case 2: // Weapons
                switch (0){ // rand.nextInt(4)
                    case 0:
                        content = new Dagger(positionX, positionY, 1);
                    break;
                    case 1:
                        content = new Staff(positionX, positionY, 1);
                    break;
                    case 2:
                        content = new Spear(positionX, positionY, 1);
                    break;
                    case 3:
                        content = new Bow(positionX, positionY, 1);
                    break;
                }
            break;
            case 3:
                content = null;
            break;
        }
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return img;
    }
}
