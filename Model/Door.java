package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
TODO:
  - implement opening the door, only if the player have a key and remove the key from the player is key variable !
  - draw the door image directly
 */

public class Door {
    private Boolean needKey;
    private int positionX;
    private int positionY;

    public Door(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setNeedKey(Boolean needKey){
        this.needKey = needKey;
    }

    public Boolean getNeedKey(){
        return this.needKey;
    }

    public int getPositionX(){
        return this.positionX;
    }

    public int getPositionY(){
        return  this.positionY;
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

    public static BufferedImage draw(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/PlayerPortalEmpty_Complete.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

}
