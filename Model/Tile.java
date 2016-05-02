package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tile {
    private int positionX;
    private int positionY;
    private BufferedImage img;
    private String imgPath;
    private boolean isWalkable;
    private boolean isBreakable;
    private boolean isDangerous;

    public Tile(int positionX, int positionY) {
        setPositionX(positionX);
        setPositionY(positionY);
        setIsBreakable(false);
        setIsWalkable(true);
        imgPath = "Images/tile.png";try {
        img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIsWalkable(boolean isWalkable){
        this.isWalkable = isWalkable;
        Game.freePositions[this.getPositionX()][this.getPositionY()] = isWalkable ? 0 : 1;
    }

    public boolean getIsWalkable(){
        return isWalkable;
    }

    public void setIsBreakable(boolean isBreakable){
        this.isBreakable = isBreakable;
    }

    public void setIsDangerous(boolean isDangerous){
        this.isDangerous = isDangerous;
    }

    public boolean getIsDangerous(){
        return isDangerous;
    }

    public boolean getIsBreakable(){
        return isBreakable;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return  positionY;
    }

    public void setPositionX(int positionX){
        try{
            if(positionX < 0){
                throw new Exception("Position X can't be less than 0");
            } else {
                this.positionX = positionX;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPositionY(int positionY){
        try{
            if(positionY < 0){
                throw new Exception("Position Y can't be less than 0");
            } else {
                this.positionY = positionY;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return img;
    }

}
