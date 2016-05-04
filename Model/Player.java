package Model;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/*
TODO :
- implement weapon damage & half heart damage
*/

public class Player extends Person {
	public int coins = 100;
    public int exp;
    public boolean hasKey = false;
    public Inventory inventory;
    public boolean isSpelling = false;
    public Spell spell = null;

    public Player(int positionX, int positionY){
        super(positionX, positionY, 7);
        inventory = new Inventory();
        imgPath = "Images/player.png";
        setWeapon(new Bow(null, null, 1));
        setWeapon(new Dagger(null, null, 1));
        setWeapon(new Spear(null, null, 1));
        setWeapon(new Staff(null, null, 1));

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
            counter.setCounterMax(weapon.counter.getCounterMax());
            setAttackMode(false);
            isAttacking = false;
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

    public void setCoins(int coin){
        this.coins = coin;
    }

    public void addCoins(Coin coin){
        this.coins += coin.getValue();
        if(this.coins  < 0){
            this.coins = 0;
        }
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

    public void addKey(){
        hasKey = true;
    }

    public void collect(Item item){
        if(item instanceof Coin){
            addCoins(((Coin)item));
        } else if (item instanceof Heart){
            if(getHealth() < 5) {
                setHealth(getHealth() + ((Heart) item).getHealth());
            } else {
                inventory.addItem(item);
            }
        } else if (item instanceof Key){
            addKey();
        } else if(item instanceof Weapon){
            setWeapon((Weapon)item);
        }
    }

    public void doSpell(){
        setCanMove(false);
        setCanAttack(false);
        isSpelling = true;
        setImage("Images/player-spell.png");
        counter.setCounterMax(6);
        counter.init();
        System.out.println(isSpelling);

    }

    public void stopSpelling(){
        setCanMove(true);
        setCanAttack(true);
        isSpelling = false;
        setImage("Images/player.png");
        counter.setCounterMax(7);
        counter.init();
    }

    public void usePotion(int index){
        if(inventory.countItems() > 0) {
            Item item = inventory.getItem(index - 1);
            if (item instanceof Heart) {
                setHealth(getHealth() + ((Heart) item).getHealth());
                inventory.removeItem(item);
                //}else if(item instanceof Spell){
                //   setSpell(getSpell() + ((Spell) item).getSpell());
            }
        }
    }
}
