package Model;

import java.util.ArrayList;
import java.util.Random;

abstract class Person{
    private int health;
	private int armor;
	public Weapon weapon;
    public int direction  = 1;
    public int positionX;
    public int positionY;
    public ArrayList<Weapon> weapons = new ArrayList<>();
    public boolean isMoving = false;
    public boolean isAttacking = false;
    private boolean canMove = true;
    public Counter counter;


    public Person (int positionX, int positionY, int counterMax){
        setPositionX(positionX);
        setPositionY(positionY);
        counter = new Counter(counterMax);
        Game.freePositions[positionX][positionY] = 1;
        setHealth(5);
        setArmor(0);
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public void setPositionX(int positionX){
        try{
            if(positionX < 0){
                throw new Exception("Position X can't be less than 0");
            } else {
                this.positionX = positionX;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPositionY(int positionY){
        try{
            if(positionY < 0){
                throw new Exception("Position Y can't be less than 0");
            } else {
                this.positionY = positionY;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getHealth(){
		return health;
	}

	public void setHealth(int health){
		try{
			if (health >= 0 & health < 5){
				this.health = health;
			} else {
				throw new Exception ("Pb sur les vies");
			}
		}catch (Exception pbVies){
			this.health = 5;
		}
	}

	public int getArmor(){
		return armor;
	}

	public void setArmor(int armor) {
		try{
		if (armor > 0 & armor < 3){
			this.armor = armor;
		}
		else {
			throw new Exception ("Pb sur l'armure");
		}
		}catch (Exception e){
			this.armor = 3;
		}
	}

	public Weapon getWeapon(){
		return weapon;
	}

	public void setWeapon(Weapon weapon){
		try{
			this.weapon = weapon;
		}catch(Exception e){
			e.printStackTrace();
		}
	}


    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    public boolean getCanMove(){
        return this.canMove;
    }

    public int[] getRandomPosition(){
        int[] position = null;
        Random randomGenerator = new Random();
        ArrayList<int[]> accessiblePositions = this.getAccessiblePositions();
        if(accessiblePositions.size() > 0) {
            int index = randomGenerator.nextInt(accessiblePositions.size());
            position = accessiblePositions.get(index);
        }
        return position;
    }

    public ArrayList<int[]> getAccessiblePositions(){
        ArrayList<int[]> accessiblePositions = new ArrayList<>();
        int x = this.getPositionX();
        int y = this.getPositionY();
        if(Game.freePositions != null) {
            int i = -1;
            while (i <= 1) {
                if (i != 0) {
                    if(y + i >= 0) {
                        if (Game.freePositions[x][y + i] == 0) {
                            accessiblePositions.add(new int[]{x ,y + i});
                        }
                    }
                    if(x + i >= 0) {
                        if (Game.freePositions[x + i][y] == 0) {
                            accessiblePositions.add(new int[]{x +i ,y});
                        }
                    }
                }
                i += 1;
            }
        }
        return accessiblePositions;
    }

	public boolean isPossibleToMove(int positionX, int positionY){
        return (positionX >= 0 && positionY >= 0) && Game.freePositions[positionX][positionY] == 0;
    }

	public void move(int positionX , int positionY){
        int oldPositionX = this.getPositionX();
        int oldPositionY = this.getPositionY();
        if(oldPositionY - positionY == 0) {
            if ((oldPositionX - positionX) > 0) {
                direction = 2; // left
            } else {
                direction = 4; // right
            }
        } else {
            if((oldPositionY - positionY) > 0) {
                direction = 1; // bottom
            }  else {
                direction = 3; // top
            }
        }
        if(this.weapon != null) {
            this.weapon.setDirection(direction);
        }
		if(isPossibleToMove(positionX, positionY)){
            Game.freePositions[positionX][positionY] = 1;
            this.setPositionX(positionX);
            this.setPositionY(positionY);
            Game.freePositions[oldPositionX][oldPositionY] = 0;
        }
    }

    public boolean isAlive(){
        return (getHealth() > 0);
    }
}