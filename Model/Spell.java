package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Spell implements Serializable{
    private static final long serialVersionUID = 1L;
    public transient BufferedImage img;
    public String imgPath;
    public Counter counter;
    public boolean movingSpell;
    private int positionX;
    private int positionY;
    private int direction;
    private int damage = 10; // so the monster will die just after that nigga!

    public Spell(int positionX, int positionY, int direction) {
        setPositionX(positionX);
        setPositionY(positionY);
        counter = new Counter(15);
        this.direction = direction;
    }

    public boolean getIsMovingSpell() {
        return movingSpell;
    }

    public void setIsMovingSpell(boolean movingSpell) {
        this.movingSpell = movingSpell;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        try {
            if (positionX < 0) {
                throw new Exception("Position X can't be less than 0");
            } else {
                this.positionX = positionX;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        try {
            if (positionY < 0) {
                throw new Exception("Position Y can't be less than 0");
            } else {
                this.positionY = positionY;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDirection() {
        return direction;
    }

    public double getDamage() {
        return damage;
    }

    public void attack(Person person) {
        double armorDiff = person.getArmor() - getDamage();
        if (armorDiff > 0) {
            person.setArmor(armorDiff);
        } else {
            person.setArmor(0);
            double healthDiff = person.getHealth() - Math.abs(armorDiff);
            if (healthDiff > 0) {
                person.setHealth(healthDiff);
            } else {
                person.setHealth(0);
            }
        }
    }

    public BufferedImage getImage() {
        return img;
    }


}
