import java.util.ArrayList;
import java.util.Random;

public class Personn {
	
	static int health;	//change quoi
	private int armor;	
	public Weapon weapon;
	private int speedOfMoove;	//on lutilise ou??
	public ArrayList<Weapon> weapons;
	public Position position;
	public ArrayList<Position> freePositions;
	public ArrayList<Position> ennemiPositions;
	
	
	
	public Personn (Weapon weapon, int health , int armor , int speedOfMoove){
		this.weapon = weapon;
		this.armor = armor;
		this.health = health;
		this.speedOfMoove = speedOfMoove;
	}
	
	public static int getHealth(){
		return health;		
	}
	
	public void setHealth(int health)throws Exception{
		try{
		if (health > 0 & health <5){
			//bugg à gérer (try catch??)
			this.health = health;
		}
		else {
			throw new Exception ("Pb sur les vies"); 	
		}
		}catch (Exception pbVies){
			this.health = 5;
		}	
	}
	
	
	public int getArmor(){
		return armor;		
	}
	
	public void setArmor(int armor)throws Exception{
		try{
		if (armor > 0 & armor <3){
			//bugg à gérer (try catch??)
			this.armor = armor;
		}
		else {
			throw new Exception ("Pb sur l'armure");
		}
		}catch (Exception pbArmure){
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
		}catch(Exception pbArme){
			//chais po
		}
	}
	
	public int getSpeedOfMoove(){
		return speedOfMoove;		
	}
	
	public void setSpeedOfMoove(int speedOfMoove)throws Exception{
		try{
		if (speedOfMoove > 0 & speedOfMoove <4){
			//bugg à gérer (try catch??)
			this.speedOfMoove = speedOfMoove;
		}
		else {
			throw new Exception ("Pb sur la vitesse d'avance"); 	
		}
		}catch (Exception pbVSOMoove){
			this.speedOfMoove = 2;
		}	
	}
	
	
	
	//proposition: la position est un couple de nombre vecteur x et y
	
	public Position getPosition(){
		return(this.position);
	}
	

	
	//A la génération de la carte ==> liste de position avec toute les positions libres (bloc ou il y a ni
	//trésor ni ennemi, mur,... se met à jour à chaque déplacement de n'importe qui
	
	public void isPossibleToMove(Position position, Position nextPosition){
		boolean possibleM = false;
		for (Position elem : freePositions){
			if(elem == nextPosition){
				possibleM = true;
			}
		}		
	}
	
	public void move(){
		//spécifique??
		//players==> keylistener
		//ennemi
		// en tout cas ca donne lieu à des directives: pas par pas
		//g d arrière avant et ca pourrait être aussi la prochaine position donc
		//sous forme de (x',y') a chaque coup ca crée le couple nextmoove et est entré en ispossible
		//utilisation de isPossible to move
		//if(possible){
		//	position = nextPosition;
		}
		
	public void isPossibleToAttack(Position position, Position mire){
		boolean possibleA = false;
		for (Position elem : ennemiPositions){
			if(elem == mire){
				possibleA = true;
			}
		}		
	}
	
	public void attack(){
		//if (PossibleA){
		//ennemi.setHealth(ennemi.getHealth() - damage)		health du voisin = - damage;??vas t il cherché direct damage
		//de l'arme et comment il sait que c'est
		//celui de l'attaquant 
		
	}
	
	public void die(){
		boolean vivant = true;
		if (health <= 0){
			vivant = false;
			//personn.disparait(); comment appliquer la mth disparait à la personne
		}
	}
	
	public void disparait(){
		//peut etre spécifique à chacun si player msg gamme over
		//si ennemi aéa apparition de trésor
		//je lai donc fait spécifiquement
		//envoie de msg au jeu??
				//si prisonnier game over

	}
	
}
