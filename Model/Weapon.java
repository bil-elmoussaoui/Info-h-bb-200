package Model;

import java.awt.image.BufferedImage;


public class Weapon {
	private int damage;
	private int level;
	public String imgPath = "Images/sword.png";
	public BufferedImage img = null;
	private int direction = 1;
    public Counter counter;

	public Weapon (int damage, int counterMax){
		this.damage = damage;
        counter = new Counter(counterMax);
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

	public void setDirection(int direction){
		this.direction = direction;
	}


	public int getDirection(){
		return  this.direction;
	}


	public BufferedImage getImage(){
		return img;
	}
}
