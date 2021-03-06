package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class Tile implements Serializable{
    private int positionX;
    private int positionY;
    private transient BufferedImage img;
    private String imgPath = "Images/tile.png";
    private boolean isWalkable;
    private boolean isBreakable;
    private boolean isDangerous;

    public Tile(int positionX, int positionY) {
        setPositionX(positionX);
        setPositionY(positionY);
        setIsBreakable(false);
        setIsWalkable(true);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createImage(){
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIsWalkable(boolean isWalkable) {
        this.isWalkable = isWalkable;
        Game.freePositions[this.getPositionX()][this.getPositionY()] = isWalkable ? 0 : 1;
    }

    public void setIsDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    public void setIsBreakable(boolean isBreakable) {
        this.isBreakable = isBreakable;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        try {
            if (positionX < 0) {
                throw new Exception("Position X can't be less than 0");
            } else {
                this.positionX = positionX;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        try {
            if (positionY < 0) {
                throw new Exception("Position Y can't be less than 0");
            } else {
                this.positionY = positionY;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return img;
    }

}
