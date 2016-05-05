package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Arrow extends Bow {
    public String imgPath = "Images/weapon-arrow.png";
    public transient BufferedImage img = null;
    public Counter counter;
    public boolean beenThrown = false;

    public Arrow() {
        super(null, null);
        counter = new Counter(12);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attack(Person person) {
        double armorDiff = person.getArmor() - this.getDamage();
        if (armorDiff > 0) {
            person.setArmor(armorDiff);
        } else {
            person.setArmor(0);
            double healthDiff = person.getHealth() - Math.abs(armorDiff);
            if (healthDiff > 0) {
                person.setHealth(healthDiff);
            } else {
                person.setHealth(0);
            }
        }

    }

    public BufferedImage getImage() {
        return img.getSubimage(counter.getCounter() * 64, (this.getDirection() - 1) * 64, 64, 64);
    }
}
