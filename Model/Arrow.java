package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
 TODO :
 - implement arrow animation
 */
public class Arrow extends Bow {
    public String imgPath = "Images/weapon-arrow.png";
    public BufferedImage img = null;
    public Counter counter;
    public boolean beenThrown = false;
    private int damage;

    public Arrow (int damage){
        super(null, null, damage);
        this.damage = damage;
        counter = new Counter(12);
        try {
            img = ImageIO.read(new File(imgPath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void attack(Person person) {
        if (person.getHasArmor()) {
            person.setArmor(person.getArmor() - damage);
        } else {
            person.setHealth(person.getHealth() - damage);
        }
    }

    public BufferedImage getImage(){
        return img.getSubimage(counter.getCounter() *64, (this.getDirection() - 1)*64, 64, 64);
    }
}
