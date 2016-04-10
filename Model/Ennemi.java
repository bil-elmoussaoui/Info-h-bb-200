import java.util.Random;

public class Ennemi extends Personn {
	
	public Ennemi(Weapon weapon, int health, int armor, int speedOfMoove) {
		super(weapon, health, armor, speedOfMoove);
	}


	FieldOfView FOV;	//ou rajoute t on le FOV moi je voudrais mettre dans le constructeur??
	
	
	
	public FieldOfView getFieldOfView(){
		return(FOV);
	}
	
	public void setFieldOfView(){
		//def la valeur du FOV (le meme sur coté+devant)
		//peut diminuer avec des sorts ou autresS
	}
	
	public void move(){
		//se ferra par Threads
		//système aléatoire soit immobile soit prédéfini
	}
	
	public void attck(){
		/*mthsimilaire à celle prédéfinie: dès quil est à coté du player il attaque
		 * IsPlayerThere()
		 * oui attaque
		 * non avance selon FOV
		 */
		
	}
	
	public void disapear(){
	/*if(vivant == false){
	 * 	envoie de msg pour spécifier que la casse change de nature
	 * 	chgment de nature selon les probabilitées suivantes		
		}*/
		Random rand = new Random();
		int nombre = rand.nextInt(1);
		if(nombre < 0.2){
			//case == rien donc nv emplacement libre
		}
		if(nombre <= 0.2 & nombre < 0.4){
			//vie à prendre donc case d'intéraction
		}
		if(nombre <= 0.4 & nombre < 0.6){
			//sous à prendre donc case d'intéraction 
		}
		if(nombre <= 0.6 & nombre < 0.8){
			//armure à prendre donc case d'intéraction 
		}
		else{
			//??
		}

	}

}
