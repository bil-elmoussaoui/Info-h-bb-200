package Model;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/*
TODO :
- implement weapon damage & half heart damage
*/

public class Player extends Person {
	public int coins;
    public int exp;
    public boolean hasKey = false;
    public Inventory inventory;
    public int speed = 10;
    public int speedMax = 10;

    public Player(int positionX, int positionY){
        super(positionX, positionY, 7);
        inventory = new Inventory();
        imgPath = "Images/player.png";
        setWeapon(new Spear(null, null, 1));
        try {
            img = ImageIO.read(new File(imgPath));
            shieldImg = ImageIO.read(new File(shieldImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeKey(){
        hasKey = false;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        if(speed < 0){
            this.speed = 0;
        } else if(speed > speedMax){
            this.speed = speedMax;
        }else {
            this.speed = speed;
        }
    }

    public void lowerSpeed(){
        setSpeed(getSpeed() - 1);
    }

    public void higherSpeed(){
        setSpeed(getSpeed() + 1);
    }

    public void setWeapon(Weapon weapon){
        if(weapon != null) {
            if (weapon instanceof Dagger) {
                attackingImgPath = "Images/player-attack-dagger.png";
            } else if (weapon instanceof Spear || weapon instanceof Staff) {
                attackingImgPath = "Images/player-attack-spear.png";
            } else if (weapon instanceof Bow) {
                attackingImgPath = "Images/player-attack-bow.png";
            }
            if (!inventory.containsWeapon(weapon)) {
                inventory.addWeapon(weapon);
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

    public void setWeaponByIndex(int index){
        if(inventory.countWeapons() >= index){
            setWeapon(inventory.getWeapon(index - 1));
        }
    }

    public void throwWeapon(Weapon weapon){
        if(inventory.containsWeapon(weapon)){
            inventory.removeWeapon(weapon);
            if (inventory.countWeapons() > 0) {
                setWeapon(inventory.randomWeapon());
            } else {
                setWeapon(null);
                setAttackMode(false);
            }
        }
    }

    public int getCoins(){
        return coins;
    }

    public int getExp(){
        return exp;
    }

    public void addExp(int exp){
        this.exp += exp;
        if(this.exp  < 0){
            this.exp = 0;
        }
    }

    public void addCoins(Coin coin){
        this.coins += coin.getValue();
        if(this.coins  < 0){
            this.coins = 0;
        }
    }

    public void addKey(){
        hasKey = true;
    }

    public void collect(Item item){
        if(item instanceof Coin){
            addCoins(((Coin)item));
        } else if (item instanceof Heart){
            if(getHealth() < 5) {
                setHealth(getHealth() + 1);
            } else {
                inventory.addItem(item);
            }
        } else if (item instanceof Key){
            addKey();
        } else if(item instanceof Weapon){
            setWeapon((Weapon)item);
        }
    }

}
