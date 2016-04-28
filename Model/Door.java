package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class Door extends Tile {
    private boolean needKey;
    private int positionX;
    private int positionY;
    private String imgPath = "Images/door.png";
    private BufferedImage img = null;
    private boolean isOpen = false;

    public Door(int positionX, int positionY){
        super(positionX, positionY);
        try{
            img = ImageIO.read(new File(imgPath));
        }catch (Exception e){

        }
    }

    public void setNeedKey(Boolean needKey){
        this.needKey = needKey;
    }

    public boolean getNeedKey(){
        return this.needKey;
    }

    public boolean getIsOpen(){
        return this.isOpen;
    }

    public void setIsOpen(boolean isOpen){
        this.isOpen = isOpen;
        setIsWalkable(true);
    }

    public BufferedImage getImage(){
        return img;
    }
}
