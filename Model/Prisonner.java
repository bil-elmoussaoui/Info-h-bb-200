
public class Prisonner extends Personn {
	
	public Prisonner(Weapon weapon, int health, int armor , int speedOfMoove) {
		super(weapon, health, armor, speedOfMoove);//mettre un super pk ca va changer les vies et autres sont spécifiques...
	}
	
	boolean isAlive;
	
	public void setIsAlive(){
		if (getHealth() <= 0){	//pk veux static???
			isAlive = false;
		}
		else{
			isAlive = true;
		}
	}
	
	//On le fait bouger ou pas????
	//est ce que les ennemis cherche à le tuer?
	
	public boolean getIsAlive(){
		return(isAlive);
	}

}
