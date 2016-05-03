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
        switch (rand.nextInt(4)){
            case 0:
                setWeapon(new Dagger(null, null, 1));
            break;
            case 1:
                setWeapon(new Spear(null, null , 1));
            break;
            case 2:
                setWeapon(new Staff(null, null, 1));
            break;
            case 3:
                setWeapon(new Bow(null, null, 1));
            break;
        }
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
                attackingImgPath = "Images/player-attack-dagger.png";
            } else if (weapon instanceof Spear || weapon instanceof Staff) {
                attackingImgPath = "Images/player-attack-spear.png";
            } else if (weapon instanceof Bow) {
                attackingImgPath = "Images/player-attack-bow.png";
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
