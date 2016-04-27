package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 TODO :
 - implement sword draw function and animation
 */
public class Bow extends Weapon {
    public String imgPath = "Images/weapon-bow.png";
    public BufferedImage img = null;
    public BufferedImage staticImg = null;
    public String staticImgPath = "Images/sword_iron.png";

    public ArrayList<Arrow> arrows = new ArrayList<>();

    public Bow(Integer positionX, Integer positionY, int damage){
        super(positionX, positionY, damage, 12);
        try {
            img = ImageIO.read(new File(imgPath));
            staticImg = ImageIO.read(new File(staticImgPath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getSaticImg(){
        return staticImg;
    }

    public BufferedImage getImage(){
        BufferedImage buffer = img.getSubimage(this.counter.getCounter() *64, (this.getDirection() - 1)*64, 64, 64);
        Arrow arrow = new Arrow(0, 0, 1);
        arrows.add(arrow);
        BufferedImage bufferArrow = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2Arrow = bufferArrow.createGraphics();
        g2Arrow.drawImage(buffer, null, null);
        Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
        g2Arrow.setComposite(newComposite);
        g2Arrow.drawImage(arrow.getImage(), null, null);
        g2Arrow.dispose();
        return bufferArrow;
    }
}
