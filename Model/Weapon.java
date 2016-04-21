package Model;

import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Weapon {
	
	private int damage;
	private int level;

	public Weapon (int damage){
		this.damage = damage;
	}

	public void setLevel(int level){
		try {
			if(level > 0) {
				this.level = level;
			} else {
				throw new Exception();
			}
		}catch (Exception e){
			this.level = 0;
		}
	}

	public int getDamage(){
        return  this.damage;
    }

	public int getLevel(){
		return this.level;
	}

    public BufferedImage draw(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/zombie.gif"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }


}
