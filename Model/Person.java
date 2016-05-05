package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    public Weapon weapon;
    public int direction = 1;
    public int positionX;
    public int positionY;
    public boolean isMoving = false;
    public boolean isAttacking = false;
    public transient BufferedImage img;
    public transient BufferedImage shieldImg;
    public String imgPath = "Images/player.png";
    public String attackingImgPath;
    public String shieldImgPath = "Images/shield-walking.png";
    public String attackingShieldImgPath = "Images/shield-attacking.png";
    public Counter counter;
    public double healthMax = 5;
    public double armorMax = 3;
    public double health;
    private double armor;
    private boolean canMove = true;
    private boolean canAttack = true;


    public Person(int positionX, int positionY, int counterMax) {
        setPositionX(positionX);
        setPositionY(positionY);
        if (weapon instanceof Dagger) {
            attackingImgPath = "Images/player-attack-dagger.png";
        } else if (weapon instanceof Spear || weapon instanceof Staff) {
            attackingImgPath = "Images/player-attack-spear.png";
        } else if (weapon instanceof Bow) {
            attackingImgPath = "Images/player-attack-bow.png";
        }
        counter = new Counter(counterMax);
        Game.freePositions[positionX][positionY] = 1;
        setHealth(5);
        setArmor(3);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        try {
            if (positionX < 0) {
                throw new Exception("Position X can't be less than 0");
            } else {
                this.positionX = positionX;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        try {
            if (positionY < 0) {
                throw new Exception("Position Y can't be less than 0");
            } else {
                this.positionY = positionY;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        try {
            if (health >= 0 & health <= healthMax) {
                this.health = health;
            } else {
                throw new Exception("Pb sur les vies");
            }
        } catch (Exception pbVies) {
            this.health = 0;
        }
    }

    public boolean getHasArmor() {
        return armor > 0;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
        if (this.armor < 0) {
            this.armor = 0;
        } else if (this.armor > armorMax) {
            this.armor = armorMax;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean getCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
        if (!this.canAttack) setAttackMode(false);
    }

    public int[] getRandomPosition() {
        int[] position = null;
        Random randomGenerator = new Random();
        ArrayList<int[]> accessiblePositions = getAccessiblePositions();
        if (accessiblePositions.size() > 0) {
            int index = randomGenerator.nextInt(accessiblePositions.size());
            position = accessiblePositions.get(index);
        }
        return position;
    }

    public ArrayList<int[]> getAccessiblePositions() {
        ArrayList<int[]> accessiblePositions = new ArrayList<>();
        int x = this.getPositionX();
        int y = this.getPositionY();
        if (Game.freePositions != null) {
            int i = -1;
            while (i <= 1) {
                if (i != 0) {
                    if (y + i >= 0) {
                        if (Game.freePositions[x][y + i] == 0) {
                            accessiblePositions.add(new int[]{x, y + i});
                        }
                    }
                    if (x + i >= 0) {
                        if (Game.freePositions[x + i][y] == 0) {
                            accessiblePositions.add(new int[]{x + i, y});
                        }
                    }
                }
                i += 1;
            }
        }
        return accessiblePositions;
    }

    public boolean isPossibleToMove(int positionX, int positionY) {
        return ((positionX >= 0 && positionY >= 0) && (positionX < Game.sizeX && positionY < Game.sizeY)
                && Game.freePositions[positionX][positionY] == 0);
    }

    public int[] getAttackedPosition() {
        int attackedX = getPositionX();
        int attackedY = getPositionY();
        switch (direction) {
            case 2:
                attackedX -= 1;
                break; // left
            case 4:
                attackedX += 1;
                break; // right
            case 1:
                attackedY -= 1;
                break; // bottom
            case 3:
                attackedY += 1;
                break;  // top
        }
        return new int[]{attackedX, attackedY};
    }

    public void move(int positionX, int positionY) {
        int oldPositionX = getPositionX();
        int oldPositionY = getPositionY();
        if (oldPositionY - positionY == 0) {
            if ((oldPositionX - positionX) > 0) {
                direction = 2; // left
            } else {
                direction = 4; // right
            }
        } else {
            if ((oldPositionY - positionY) > 0) {
                direction = 1; // bottom
            } else {
                direction = 3; // top
            }
        }
        if (this.weapon != null) {
            weapon.setDirection(direction);
        }
        if (isPossibleToMove(positionX, positionY)) {
            Game.freePositions[positionX][positionY] = 1;
            setPositionX(positionX);
            setPositionY(positionY);
            Game.freePositions[oldPositionX][oldPositionY] = 0;
        }
    }

    public boolean isAlive() {
        return !(getHealth() == 0);
    }

    public void attack(Person person) {
        if (weapon != null && person != this) {
            double armorDiff = person.getArmor() - weapon.getDamage();
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
    }

    public void setAttackMode(boolean attackMode) {
        this.isAttacking = attackMode;
        try {
            if (attackMode && weapon != null) {
                if (weapon instanceof Spear || weapon instanceof Staff) {
                    shieldImg = ImageIO.read(new File(attackingShieldImgPath));
                }
                setImage(attackingImgPath);
                counter.setCounterMax(weapon.counter.getCounterMax());
            } else {
                setImage(imgPath);
                shieldImg = ImageIO.read(new File(shieldImgPath));
                counter.setCounterMax(7);
            }
        } catch (Exception e) {
        }
    }

    public BufferedImage getImage() {
        BufferedImage personImage = img.getSubimage(counter.getCounter() * 64, (direction - 1) * 64, 64, 64);
        if (weapon != null) {
            BufferedImage buffer = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.drawImage(personImage, null, null);
            Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            g2.setComposite(newComposite);
            g2.drawImage(weapon.getImage(), null, null);
            g2.dispose();
            personImage = buffer;
        }
        if (getHasArmor()) {
            BufferedImage shieldSubImage;
            if (weapon != null && !weapon.getIsDistanceWeapon()) {
                shieldSubImage = shieldImg.getSubimage(counter.getCounter() * 64, (direction - 1) * 64, 64, 64);
            } else {
                shieldSubImage = shieldImg.getSubimage(0, (direction - 1) * 64, 64, 64);
            }
            BufferedImage buffer = new BufferedImage(Game.pixelX, Game.pixelY, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.drawImage(personImage, null, null);
            Composite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
            g2.setComposite(newComposite);
            g2.drawImage(shieldSubImage, null, null);
            g2.dispose();
            personImage = buffer;
        }
        return personImage;
    }

    public void setImage(String imgPath) {
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (Exception e) {
        }
    }
}
