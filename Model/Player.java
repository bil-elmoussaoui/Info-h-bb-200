import java.util.ArrayList;

public class Player extends Personn {
	
	public Player(Weapon weapon, int health, int armor , int speedOfMoove) {
		super(weapon, health, armor, speedOfMoove);//mettre un super pk ca va changer les vies et autres sont spécifiques...
	}

	public ArrayList<Position> ennemiPositions;
	public ArrayList<Position> itemsPositions;
	public ArrayList<Position> doorLevels;
	public ArrayList<Position> freePositionsN;	//avance normale
	public ArrayList<Position> freePositionsH;	//avance hard//pk va pas chercher sa dans la classe personne
	
	
	//céer des array list qui se mettent à jour à chaque instant
	//cases libre de déplacement == freePositions
	//cases avec des choses à ramasser == items
	//cases aves portes doorsToNextLevel
	//cases avec les ennemis
	
	
	
	public void collect(){
		//par un keyListener
		//pointe vers la case droit devant lui
		//retourne un double de nombre (x,y) = Position pointeur
		Position pointeur = null;//défini plus loin par le keylinstener
		for (Position elem : this.itemsPositions){
			if (pointeur == elem);
			//(ce qu'il y a en elem)
			//si c'eest vie
			//player.setHealth(health + 1) ??comment dire que ca ajoute à sa vie
			//jai géré si il y a plus de 5 vies
			//si c'est armure 
			//player.setArmor(armor + 1)
			//autre
			//add.inventory
		}
	}
	
	public void use(){
		//interaction avee l'inventaire
		//fais da,s la section invetaire
		//avec les getteur et setteur de player
	
		
	}
	
	public void move(){
		/* par les keyListeners 
		 * génère un Position nextPosition
		 * et isPossible toMove déja fais dans la superclasse Personn
		 * Position playerPosition = player.getPosition
		 * Gros IFFF ou FOR avec dans quelle type de case on est déplacement normal ou ralentit
		 * for (Position elem : freePositionN)
		 * 	if (elem == player.getPosition()){
		 * 		speedOfMoove = 4;
		 * }else{
		 * 	speedOfMoove = 2;
		 * }
		 * if (possibleM){
		 * 	playerPosition = nextPosition
		 * }
		 */
	}
	
	public void attack(Position mire){
		//se fait différet la par keyListener
		
	}
}
