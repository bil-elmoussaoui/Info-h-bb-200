package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monster extends Person{
    private BufferedImage img;
    private String imgPath = "Images/monster.png";
    public String attackingImgPath;
    public FieldOfView FOV;
    private BufferedImage shieldImg;
    public String shieldImgPath = "Images/shield-walking.png";
    public String attackingShieldImgPath = "Images/shield-attacking.png";


    public Monster(int positionX, int positionY) {
        super(positionX, positionY, 8);
        FOV = new FieldOfView(positionX, positionY, direction);
        setWeapon(new Dagger(null, null, 1));
        try {
            img = ImageIO.read(new File(imgPath));
            shieldImg = ImageIO.read(new File(shieldImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            this.weapon = weapon;
            this.weapon.setDirection(direction);
            this.weapon.counter.init();
        }
    }



    public BufferedImage getImage() {
        BufferedImage playerImage = img.getSubimage(counter.getCounter()*64, (direction - 1)*64, 64, 64);
        BufferedImage shieldSubImage = shieldImg.getSubimage(counter.getCounter()*64, (direction - 1)*64, 64, 64);
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
        if(getHasArmor() && !isAttacking){
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

    public void attack(Player player){
        if(weapon != null) {
            if(player.getHasArmor()){
                player.setArmor(player.getArmor() - weapon.getDamage());
            } else {
                player.setHealth(player.getHealth() - weapon.getDamage());
            }
        }
    }


    // 1 bas, 2 gauche 3 haut 4 droite

    /*public int[] getMonsterAttackPosition() {
        int attackPositionX = this.getPositionX();
        int attackPositionY = this.getPositionY();
        switch (direction) {
            case (4):
                attackPositionX = attackPositionX + 1;
                break;
            case (2):
                attackPositionX = attackPositionX - 1;
                break;
            case (1):
                attackPositionY = attackPositionY + 1;
                break;
            case (3):
                attackPositionY = attackPositionY - 1;
                break;
        }
    }*/

}
