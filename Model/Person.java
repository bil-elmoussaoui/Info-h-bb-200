package Model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

abstract class Person {

	static int health;
	private int armor;
	public Weapon weapon;
	private int positionX;
	private int positionY;
	public ArrayList<Weapon> weapons = new ArrayList<>();

	public Person (int positionX, int positionY){
	    this.setHealth(5);
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
			if (health > 0 & health <5){
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
			boolean estLa = false;
			for (Weapon arme : weapons){
				if (weapon == arme){
					estLa = true;
				}
			}
			if (estLa){
				this.weapon = weapon;
			}else{
				throw new Exception ("pb arme inexistante");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    public int[] getRandomPosition(){
        int[] position = null;
        Random randomGenerator = new Random();
        ArrayList<int[]> acceciblePositions = this.getAcceciblePositions();
        if(acceciblePositions.size() > 0) {
            int index = randomGenerator.nextInt(acceciblePositions.size());
            position = acceciblePositions.get(index);
        }
        return position;
    }

    public ArrayList<int[]> getAcceciblePositions(){
        ArrayList<int[]> acceciblePositions = new ArrayList<>();
        int[] position = new int [2];
        int x = this.getPositionX();
        int y = this.getPositionY();
        if(Game.freePositions != null) {
            int i = -1;
            while (i <= 1) {
                if (i != 0) {
                    if (Game.freePositions[x][y + i] == 0) {
                        position[0] = x;
                        position[1] = y + i;
                        acceciblePositions.add(position);
                    }
                    if (Game.freePositions[x + i][y] == 0) {
                        position[0] = x + i;
                        position[1] = y;
                        acceciblePositions.add(position);
                    }
                }
                i += 1;
            }
        }
        return acceciblePositions;
    }

	public boolean isPossibleToMove(int positionX, int positionY){
		return Game.freePositions[positionX][positionY] == 0;
	}

	public void move(int positionX , int positionY){
		if(isPossibleToMove(positionX, positionY)){
            Game.freePositions[this.getPositionX()][this.getPositionY()] = 0;
            this.setPositionX(positionX);
            this.setPositionY(positionY);
            Game.freePositions[positionX][positionY] = 1;
        }
    }
}