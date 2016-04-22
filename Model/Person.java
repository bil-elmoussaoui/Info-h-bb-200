package Model;

import java.util.ArrayList;
import java.util.Random;

abstract class Person{
	static int health;
	private int armor;
	public Weapon weapon;
    public int direction;
    public int positionX;
    public int positionY;
    public ArrayList<Weapon> weapons = new ArrayList<>();

	public Person (int positionX, int positionY){
	    this.setHealth(3);
        this.setArmor(0);
        this.setPositionX(positionX);
        this.setPositionY(positionY);
    }

    public int getPositionX(){
        return this.positionX;
    }

    public int getPositionY(){
        return  this.positionY;
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
			if (health >= 0 & health <5){
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

	public void setWeapon(Weapon weapon)throws Exception{
		try{
			this.weapon = weapon;
		}catch(Exception e){
			e.printStackTrace();
		}
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
        int[] position = new int [2];
        int x = this.getPositionX();
        int y = this.getPositionY();
        if(Game.freePositions != null) {
            int i = -1;
            while (i <= 1) {
                if (i != 0) {
                    if(y + i >= 0) {
                        if (Game.freePositions[x][y + i] == 0) {
                            position[0] = x;
                            position[1] = y + i;
                            accessiblePositions.add(position);
                        }
                    }
                    if(x + i >= 0) {
                        if (Game.freePositions[x + i][y] == 0) {
                            position[0] = x + i;
                            position[1] = y;
                            accessiblePositions.add(position);
                        }
                    }
                    position = new int [2];
                }
                i += 1;
            }
        }
        return accessiblePositions;
    }

	public boolean isPossibleToMove(int positionX, int positionY){
		return Game.freePositions[positionX][positionY] == 0;
	}

	public void move(int positionX , int positionY){
		if(isPossibleToMove(positionX, positionY)){
            int oldPositionX = this.getPositionX();
            int oldPositionY = this.getPositionY();
            if(oldPositionY - positionY == 0) {
                if ((oldPositionX - positionX) > 0) {
                    direction = 1; // left
                } else {
                    direction = 2; // right
                }
            } else {
                if((oldPositionY - positionY) > 0) {
                    direction = 3; // bottom
                }  else {
                    direction = 4; // top
                }
            }
            Game.freePositions[positionX][positionY] = 1;
            this.setPositionX(positionX);
            this.setPositionY(positionY);
            Game.freePositions[oldPositionX][oldPositionY] = 0;
        }
    }
}