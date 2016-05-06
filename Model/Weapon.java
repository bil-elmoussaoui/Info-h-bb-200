package Model;

import java.awt.image.BufferedImage;


public class Weapon extends Item {
    public transient BufferedImage img = null;
    public transient BufferedImage staticImg = null;
    public Counter counter;
    public boolean isDistanceWeapon;
    private double damage;
    private int direction = 1;

    public Weapon(Integer positionX, Integer positionY, double damage, int counterMax) {
        super(positionX, positionY);
        this.damage = damage;
        setIsCollectable(true);
        setIsBreakable(false);
        setIsWalkable(true);
        // counter is used for weapon animation
        counter = new Counter(counterMax);
    }

    public double getDamage() {
        return damage;
    }

    public int getDirection() {
        return this.direction;
    }
    
    // weapon direction used to get the right animation image
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getIsDistanceWeapon() {
        return this.isDistanceWeapon;
    }

    public void setIsDistanceWeapon(boolean isDistanceWeapon) {
        this.isDistanceWeapon = isDistanceWeapon;
    }

    public BufferedImage getImage() {
        return img;
    }

    public BufferedImage getStaticImg() {
        return staticImg;
    }

}
