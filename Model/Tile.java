package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tile {
    private int positionX;
    private int positionY;
    private BufferedImage img;
    private String imgPath;

    public Tile(int positionX, int positionY) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        imgPath = "Images/leaves.png";try {
        img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public BufferedImage getImage(){

        return this.img;
    }

}
