package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Salesman extends Item {
    public int selectorY = 0;
    public int selectorX = 0;
    public int[][][] carteAchat = new int[4][4][2];
    private String imgPath = "Images/market.png";
    private transient BufferedImage img = null;

    public Salesman(int positionX, int positionY) {
        super(positionX, positionY);
        setIsBreakable(false);
        setIsCollectable(false);
        setIsWalkable(false);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {

        }
        carteAchat[0][0] = new int[]{1, 10}; // Potion vie
        carteAchat[1][0] = new int[]{2, 50}; // Potion firelion
        carteAchat[2][0] = new int[]{3, 50}; // Potion icetacle
        carteAchat[3][0] = new int[]{4, 100}; // Bouclier
        carteAchat[0][1] = new int[]{5, 10}; // arrows
        carteAchat[1][1] = new int[]{6, 200}; // bow
        carteAchat[2][1] = new int[]{7, 150}; // Dagger
        carteAchat[3][1] = new int[]{8, 100};  //Spear
        carteAchat[3][3] = new int[]{16, 1000}; // key
    }

    public int getSelectorY() {
        return selectorY;
    }

    public void setSelectorY(int selectorY) {
        if (selectorY < 0) {
            this.selectorY = (4 + selectorY) % 4;
        } else {
            this.selectorY = selectorY % 4;
        }
    }

    public int getSelectorX() {
        return selectorX;
    }

    public void setSelectorX(int selectorX) {
        if (selectorX < 0) {
            this.selectorX = (4 + selectorX) % 4;
        } else {
            this.selectorX = selectorX % 4;
        }
    }

    public BufferedImage getImage() {
        return img;
    }

}