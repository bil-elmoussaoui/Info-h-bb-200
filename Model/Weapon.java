

package Model;

import java.awt.image.BufferedImage;


public class Weapon extends Item {
	private int damage;
	private int level;
	public String imgPath = "Images/sword.png";
    public String staticImgPath = "Images/sword-static.png";
	public BufferedImage img = null;
    public BufferedImage staticImg = null;
	private int direction = 1;
    public Counter counter;
	public boolean isDistanceWeapon;

	public Weapon (Integer positionX, Integer positionY, int damage, int counterMax){
		super(positionX, positionY);
		this.damage = damage;
        setIsCollectable(true);
        setIsBreakable(false);
        setIsWalkable(true);
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
        return  damage;
    }

	public int getLevel(){
		return level;
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

	public int getDirection(){
		return  this.direction;
	}

	public void setIsDistanceWeapon(boolean isDistanceWeapon){
		this.isDistanceWeapon = isDistanceWeapon;
	}

	public boolean getIsDistanceWeapon(){
		return  this.isDistanceWeapon;
	}

	public BufferedImage getImage(){
		return img;
	}

    public BufferedImage getStaticImg(){
        return staticImg;
    }

}
