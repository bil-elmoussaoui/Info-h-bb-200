package Model;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
TODO :
- use a real weapon
*/

public class Player extends Person {
	public int coins;
    public int exp;
    public ArrayList<Key> keys = new ArrayList<>();
    private BufferedImage img;
    public String imgPath = "Images/player.png";
    public String attackingImgPath;
    private Inventory inventory;

    public Player(int positionX, int positionY){
        super(positionX, positionY, 7);
        this.inventory = new Inventory();
        this.setWeapon(new Staff(1));
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(weapon instanceof Dagger){
            attackingImgPath = "Images/player-attack-dagger.png";
        } else if (weapon instanceof Spear || weapon instanceof Staff){
            attackingImgPath = "Images/player-attack-spear.png";
        } else if (weapon instanceof Bow){
            attackingImgPath = "Images/player-attack-bow.png";
        }
    }

    public void setWeapon(Weapon weapon){
        if(!this.inventory.containsWeapon(weapon)){
            this.inventory.addWeapon(weapon);
        }
        this.weapon = weapon;
    }

    public void setImage(String imgPath){
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e){}
    }


    public void setAttackMode(boolean attackMode){
        this.isAttacking = attackMode;
        if(attackMode) {
            this.setImage(attackingImgPath);
            counter.setCounterMax(weapon.counter.getCounterMax());
        } else {
            this.setImage(imgPath);
            counter.setCounterMax(7);
        }
    }

    public BufferedImage getImage(){
        BufferedImage playerImage = img.getSubimage(counter.getCounter()*64, (direction - 1)*64, 64, 64);
        if(weapon != null) {
            BufferedImage buffer = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.drawImage(playerImage, null, null);
            Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            g2.setComposite(newComposite);
            g2.drawImage(this.weapon.getImage(), null, null);
            g2.dispose();
            return buffer;
        } else {
            return playerImage;
        }
    }

    public int getCoins(){
        return this.coins;
    }

    public int getExp(){
        return this.exp;
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

    public void addKey(Key key){
        // nombre limité de clefs?
        this.keys.add(key);
    }

    public void collect(Item item){
        if(item instanceof Coin){
            this.addCoins(((Coin)item));
        } else if (item instanceof Heart){
            this.setHealth(this.getHealth() + 1);
        } else if (item instanceof Key){
            this.addKey(((Key)item));
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
        monster.setHealth(monster.getHealth() - 1);
    }
}
