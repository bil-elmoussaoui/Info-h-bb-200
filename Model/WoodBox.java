package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class WoodBox extends Item{
    private BufferedImage img;
    private String imgPath = "Images/Wood_Box.png";
    public Item content;
    public Counter counter;
    private boolean isBeingBroken;

    public WoodBox(int positionX, int positionY){
        super(positionX, positionY);
        setIsCollectable(false);
        setIsBreakable(true);
        setIsBeingBroken(false);
        Random randomItem = new Random();
        counter = new Counter(7);
        switch (randomItem.nextInt(4)) {
            case 0:
                content = new Coin(positionX, positionY);
            break;
            case 1:
                content = new Heart(positionX, positionY);
            break;
            case 2: // Weapons
                switch (randomItem.nextInt(4)){
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
        setIsWalkable(false);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIsBeingBroken(boolean isBeingBroken){
        this.isBeingBroken = isBeingBroken;
    }

    public boolean getIsBeingBroken(){
        return isBeingBroken;
    }

    public BufferedImage getImage() {
        return img.getSubimage(counter.getCounter() * 60, 0, 60, 60);
    }
}
