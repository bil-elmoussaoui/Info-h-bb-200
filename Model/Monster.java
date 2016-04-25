package Model;

import View.MainWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
TODO :
- Monster shouldn't have access to game class?
 */
public class Monster extends Person{
    private BufferedImage img;
    private boolean canMove;

    FieldOfView FOV;

	public Monster(int positionX, int positionY) {
        super(positionX, positionY);
        img = null;
        try {
            img = ImageIO.read(new File("Images/zombie.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    public boolean getCanMove(){
        return this.canMove;
    }

    public BufferedImage getImage() {
        return img;
    }

}
