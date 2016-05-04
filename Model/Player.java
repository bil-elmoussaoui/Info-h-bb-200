package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/*
TODO :
- implement weapon damage
- exp maybe??
*/

public class Player extends Person {
    public static boolean isAlive = true;
    public int coins = 100;
    public boolean hasKey = false;
    public Inventory inventory;
    public boolean isSpelling = false;
    public Spell spell = null;

    public Player(int positionX, int positionY) {
        super(positionX, positionY, 7);
        inventory = new Inventory();
        imgPath = "Images/player.png";
        setWeapon(new Staff(null, null, 1));

        try {
            img = ImageIO.read(new File(imgPath));
            shieldImg = ImageIO.read(new File(shieldImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeKey() {
        hasKey = false;
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

    public void setWeaponByIndex(int index) {
        if (inventory.countWeapons() >= index) {
            setWeapon(inventory.getWeapon(index - 1));
        }
    }

    public void throwWeapon(Weapon weapon) {
        if (inventory.containsWeapon(weapon)) {
            inventory.removeWeapon(weapon);
            if (inventory.countWeapons() > 0) {
                setWeapon(inventory.randomWeapon());
            } else {
                setWeapon(null);
                setAttackMode(false);
            }
        }
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coin) {
        this.coins = coin;
    }

    public void addCoins(Coin coin) {
        this.coins += coin.getValue();
        if (this.coins < 0) {
            this.coins = 0;
        }
    }

    public void addKey() {
        hasKey = true;
    }

    public void collect(Item item) {
        if (item instanceof Coin) {
            addCoins(((Coin) item));
        } else if (item instanceof Potion) {
            inventory.addItem(item);
        } else if (item instanceof Key) {
            addKey();
        } else if (item instanceof Weapon) {
            setWeapon((Weapon) item);
        }
    }

    public void doSpell(int type) {
        int positionX = getPositionX();
        int positionY = getPositionY();
        switch (direction) {
            case 1:
                positionY -= 1;
                break;
            case 2:
                positionX -= 1;
                break;
            case 3:
                positionY += 1;
                break;
            case 4:
                positionX += 1;
                break;
        }
        setCanMove(false);
        setCanAttack(false);
        isSpelling = true;
        setImage("Images/player-spell.png");
        counter.setCounterMax(6);
        counter.init();
        if (type == 0) { // FireLion
            spell = new FireLion(positionX, positionY, direction);
        } else if (type == 1) { //IceTacle
            spell = new Icetacle(positionX, positionY, direction);
        }
    }

    public void stopSpelling() {
        setCanMove(true);
        setCanAttack(true);
        isSpelling = false;
        setImage("Images/player.png");
        counter.setCounterMax(7);
        counter.init();
    }

    public void usePotion(int index) {
        if (inventory.countItems() > 0) {
            Item item = inventory.getItem(index - 1);
            if (item instanceof Potion) {
                switch (((Potion) item).getType()) {
                    case 0:
                        setHealth(getHealth() + ((Potion) item).getHealth());
                        break;
                    case 1:
                        doSpell(0); //FireLion
                        break;
                    case 2:
                        doSpell(1); //IceTacle
                        break;
                }
                inventory.removeItem(item);
            }
        }
    }
}
