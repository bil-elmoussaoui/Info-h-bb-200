package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    private int positionX;
    private int positionY;
    private static BufferedImage img;

    public Tile(int positionX, int positionY) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        try {
            img = ImageIO.read(new File("Images/StoneWallBackground.png"));
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
    public static void draw(Graphics g, int x, int y) {
        g.drawImage(Tile.img, x * 24, y * 24, 24, 24, null);
    }
}
