package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
TODO :
- exp maybe??
*/

public class Player extends Person implements PlayerObserver {
    public int coins = 100;
    public boolean hasKey = false;
    public Inventory inventory;
    public boolean isSpelling = false;
    public Spell spell = null;
    private String imgPath = "Images/player.png";
    // lists of obervers used to notify the mainwindow that the user is dead or not yet!
    private transient ArrayList<Observer> observers = new ArrayList<>();

    public Player(int positionX, int positionY) {
        super(positionX, positionY, 7);
        inventory = new Inventory();
        setWeapon(new Bow(null, null));
        setWeapon(new Staff(null, null));
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

    public void removeKey() {
        hasKey = false;
    }

    public void setWeapon(Weapon weapon) {
        if (weapon != null) {
            // change attacking animation!
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
        setAttackMode(false);
    }

    public void setWeaponByIndex(int index) {
        if (inventory.countWeapons() >= index) {
            setWeapon(inventory.getWeapon(index - 1));
        }
    }

    public void throwWeapon(Weapon weapon) {
        // remove a weapon from the inventory, can be collected again after that
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
        // start the spell animation
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
        // stop player animation before starting the spell
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
                        if (getHealth() < healthMax) {
                            setHealth(getHealth() + ((Potion) item).getHealth());
                            inventory.removeItem(item);
                        }
                        break;
                    case 1:
                        doSpell(0); //FireLion
                        inventory.removeItem(item);
                        break;
                    case 2:
                        doSpell(1); //IceTacle
                        inventory.removeItem(item);
                        break;
                }
            }
        }
    }

    public void setHealth(double health) {
        this.health = health;
        if (this.health < 0) this.health = 0;
        if (this.health > healthMax) this.health = healthMax;
        if (this.health == 0) {
            notifyObservers();
        }
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
