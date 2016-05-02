package Model;

import View.MainWindow;

import java.util.ArrayList;
import java.util.Random;

abstract class Person{
    private double health;
	private double armor;
	public Weapon weapon;
    public int direction  = 1;
    public int positionX;
    public int positionY;
    public boolean isMoving = false;
    public boolean isAttacking = false;
    private boolean canMove = true;
    public Counter counter;
    public static double healthMax = 5;


    public Person (int positionX, int positionY, int counterMax){
        setPositionX(positionX);
        setPositionY(positionY);
        counter = new Counter(counterMax);
        Game.freePositions[positionX][positionY] = 1;
        setHealth(5);
        setArmor(3);
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

    public double getHealth(){
		return health;
	}

	public void setHealth(double health){
		try{
			if (health >= 0 & health <= healthMax){
				this.health = health;
			} else {
				throw new Exception ("Pb sur les vies");
			}
            if(this instanceof Player && this.health == 0){
                Game.enVie = false;
            }
		}catch (Exception pbVies){
			this.health = 0;
		}
	}

    public void setArmor(double armor){
        this.armor = armor;
    }

    public boolean getHasArmor(){
        return  armor > 0;
    }

    public double getArmor(){return armor;}

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
        return canMove;
    }

    public int[] getRandomPosition(){
        int[] position = null;
        Random randomGenerator = new Random();
        ArrayList<int[]> accessiblePositions = getAccessiblePositions();
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
        return ((positionX >= 0 && positionY >= 0) && (positionX < Game.sizeX && positionY < Game.sizeY)
                && Game.freePositions[positionX][positionY] == 0);
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

	public void move(int positionX , int positionY){
        int oldPositionX = getPositionX();
        int oldPositionY = getPositionY();
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
            weapon.setDirection(direction);
        }
		if(isPossibleToMove(positionX, positionY)){
            Game.freePositions[positionX][positionY] = 1;
            setPositionX(positionX);
            setPositionY(positionY);
            Game.freePositions[oldPositionX][oldPositionY] = 0;
        }
    }

    public boolean isAlive(){
        return !(getHealth() == 0);
    }
}