package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Monster extends Person {
    public FieldOfView FOV;

    public Monster(int positionX, int positionY) {
        super(positionX, positionY, 8);
        FOV = new FieldOfView(positionX, positionY, direction);
        imgPath = "Images/monster.png";
        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                setWeapon(new Dagger(null, null));
                break;
            case 1:
                setWeapon(new Spear(null, null));
                break;
            case 2:
                setWeapon(new Staff(null, null));
                break;
        }
        try {
            img = ImageIO.read(new File(imgPath));
            shieldImg = ImageIO.read(new File(shieldImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createImage(){
        try {
            img = ImageIO.read(new File(imgPath));
            shieldImg = ImageIO.read(new File(shieldImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWeapon(Weapon weapon) {
        if (weapon != null) {
            if (weapon instanceof Dagger) {
                attackingImgPath = "Images/monster-attack-dagger.png";
            } else if (weapon instanceof Spear || weapon instanceof Staff) {
                attackingImgPath = "Images/monster-attack-spear.png";
            }

            this.weapon = weapon;
            this.weapon.setDirection(direction);
            setImage(attackingImgPath);
            counter.setCounterMax(this.weapon.counter.getCounterMax());
            this.weapon.counter.init();
            counter.init();
        } else {
            this.weapon = null;
        }
    }

}
