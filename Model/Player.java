package Model;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private BufferedImage img;
    private BufferedImage shieldImg;
    public String imgPath = "Images/player.png";
    public String attackingImgPath;
    public String shieldImgPath = "Images/shield-walking.png";
    public Inventory inventory;
    public String attackingShieldImgPath = "Images/shield-attacking.png";

    public Player(int positionX, int positionY){
        super(positionX, positionY, 7);
        inventory = new Inventory();
        setWeapon(new Bow(null, null, 1));
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

    public void setImage(String imgPath){
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e){}
    }

    public void setAttackMode(boolean attackMode){
        this.isAttacking = attackMode;
        try {
            if(attackMode && weapon != null) {
                if(weapon instanceof Spear || weapon instanceof Staff) {
                    shieldImg = ImageIO.read(new File(attackingShieldImgPath));
                }
                setImage(attackingImgPath);
                counter.setCounterMax(weapon.counter.getCounterMax());
            } else {
                setImage(imgPath);
                shieldImg = ImageIO.read(new File(shieldImgPath));
                counter.setCounterMax(7);
            }
        } catch (Exception e){}
    }

    public BufferedImage getImage(){
        BufferedImage playerImage = img.getSubimage(counter.getCounter()*64, (direction - 1)*64, 64, 64);
        if(weapon != null) {
            BufferedImage buffer = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.drawImage(playerImage, null, null);
            Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            g2.setComposite(newComposite);
            g2.drawImage(weapon.getImage(), null, null);
            g2.dispose();
            playerImage = buffer;
        }
        if(getHasArmor()){
            BufferedImage shieldSubImage;
            if(weapon != null && !weapon.getIsDistanceWeapon()) {
                shieldSubImage = shieldImg.getSubimage(counter.getCounter() * 64, (direction - 1) * 64, 64, 64);
            } else {
                shieldSubImage = shieldImg.getSubimage(0, (direction - 1) * 64, 64, 64);
            }
            BufferedImage buffer = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.drawImage(playerImage, null, null);
            Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            g2.setComposite(newComposite);
            g2.drawImage(shieldSubImage, null, null);
            g2.dispose();
            playerImage = buffer;
        }
        return playerImage;
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

    public int[] getAttackedPosition(){
        int attackedX = this.getPositionX();
        int attackedY = this.getPositionY();
        switch(direction){
            case 2: attackedX -= 1; break; // left
            case 4: attackedX += 1; break; // right
            case 1: attackedY -= 1; break; // bottom
            case 3: attackedY += 1; break;  // top
        }
        return new int[]{attackedX, attackedY};
    }

    public void attack(Monster monster){
        // fix shit here!
        if(weapon != null) {
            if(monster.getHasArmor()){
                monster.setArmor(monster.getArmor() - weapon.getDamage());
            } else {
                monster.setHealth(monster.getHealth() - weapon.getDamage());
            }
        }
    }
}
