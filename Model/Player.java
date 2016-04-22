package Model;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
TODO :
- how to implement attack & collect if the player does not have access to both lists and map?
- use a real weapon
- add animation
- draw a different image for each direction
*/

public class Player extends Person implements Runnable {
	public int coins;
    public int exp;
    private int counter = 0;
    private static BufferedImage img;

    public Player(int positionX, int positionY){
        super(positionX, positionY);
        try {
            img = ImageIO.read(new File("Images/Player.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void draw(Graphics g, int x, int y) {
        g.drawImage(img, x * 32, y * 32, 32, 32, null);

    }

    @Override
    public void run(){
        while(true){
            switch (counter){
                case 0:
                break;
                case 1:
                break;
                case 2:
                break;
                case 3:
                break;
            }
            counter += 1;
            if(counter > 4){
                counter = 0;
            }
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

    public void addCoins(int coins){
        this.coins += coins;
        if(this.coins  < 0){
            this.coins = 0;
        }
    }

    public void collect(){
        /*if(items.size() > 0){
            int j = 0;
            int itemsSize = items.size();
            while (j < itemsSize && itemsSize != 0) {
                Item item = items.get(j);
                int x = item.getPositionX();
                int y = item.getPositionY();
                if (this.getPositionX() == x && this.getPositionY() == y) {
                    if(item instanceof Coin){
                        //player.addCoins((Coin)item.getValue());
                    } else if (item instanceof Heart){
                        if(this.getHealth() + 1 < 5) { // Health limit
                            this.setHealth(this.getHealth() + 1);
                        } else {
                            // Should be added to the inventory later!
                        }
                    }
                    //LevelWindow.refreshGamePanel();
                    items.remove(item);
                    itemsSize -=1;
                    freePositions[x][y] = 0;
                }
                j+=1;
            }
        }*/
    }

    public int[] getAttackedPosition(){
        int attackedX = this.getPositionX();
        int attackedY = this.getPositionY();
        switch(direction){
            case 1: // left
                attackedX += 1;
            break;
            case 2: // right;
                attackedX -= 1;
            break;
            case 3: //bottom
                attackedY += 1;
            break;
            case 4: // top
                attackedY -= 1;
            break;
        }
        int[] position = new int[2];
        position[0] = attackedX;
        position[1] = attackedY;
        return position;
    }

    public void attack(){
     /*   int[] position = this.getAttackedPosition();
        if(monsters.size() > 0) {
            int i = 0;
            int monstersSize = monsters.size();
            while (i < monstersSize && monstersSize != 0) {
                Monster monster = monsters.get(i);
                int x = monster.getPositionX();
                int y = monster.getPositionY();
                if (position[0] == x - 1 && position[1] == y - 1) {
                    if (monster.getHealth() - 1 == 0) {
                        monsters.remove(monster);
                        monstersSize -= 1;
                        map[x][y] = 0;
                        Game.freePositions[x][y] = 0;
                    } else {
                        monster.setHealth(monster.getHealth() - 1);
                    }
                }
                i += 1;
            }
        }
        if(woodBoxs.size() > 0) {
            int j = 0;
            int woodBoxesSize = woodBoxs.size();
            while (j < woodBoxesSize && woodBoxesSize != 0) {
                WoodBox box = woodBoxs.get(j);
                int x = box.getPositionX();
                int y = box.getPositionY();
                if (position[0] == x - 1 && position[1] == y - 1) {
                    woodBoxs.remove(box);
                    woodBoxesSize -=1;
                    Random randomItem = new Random();
                    switch(randomItem.nextInt(3)){
                        case 1: items.add(new Coin(x, y)); break;
                        case 2: items.add(new Heart(x, y)); break;
                        case 3: items.add(new Key(x, y)); break;
                    }
                    Game.freePositions[x][y] = 0;
                }
                j+=1;
            }
        }*/
    }
}
