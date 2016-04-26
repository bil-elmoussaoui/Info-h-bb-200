package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Portal extends Tile{
    private String inImgPath = "Images/portal-in.png";
    private BufferedImage inImg;
    private String outImgPath = "Images/portal-out.png";
    private BufferedImage outImg;
    private int outPositionX;
    private int outPositionY;

    public Portal(int positionX, int positionY, int outPositionX, int outPositionY){
        super(positionX, positionY);
        this.setIsBreakable(false);
        this.setIsWalkable(true);
        this.outPositionX = outPositionX;
        this.outPositionY = outPositionY;
        try {
            inImg = ImageIO.read(new File(inImgPath));
            outImg = ImageIO.read(new File(outImgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getOutPositionX(){
        return this.outPositionX;
    }

    public int getOutPositionY(){
        return this.outPositionY;
    }

    public BufferedImage getInImage() {
        return inImg;
    }

    public BufferedImage getOutImage() {
        return outImg;
    }
}