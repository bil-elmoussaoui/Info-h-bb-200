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
    FieldOfView FOV;

	public Monster(int positionX, int positionY) {
        super(positionX, positionY, 8);
        this.setWeapon(new Dagger(null, null, 1));
        try {
            img = ImageIO.read(new File(imgPath));
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
        BufferedImage monsterImage = img.getSubimage(counter.getCounter()*64, (direction - 1)*64, 64, 64);
        if(weapon != null) {
            BufferedImage buffer = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.drawImage(monsterImage, null, null);
            Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            g2.setComposite(newComposite);
            g2.drawImage(this.weapon.getImage(), null, null);
            g2.dispose();
            return buffer;
        } else {
            return monsterImage;
        }
    }

}
