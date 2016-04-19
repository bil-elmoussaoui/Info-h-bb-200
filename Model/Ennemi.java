import java.util.Random;

public class Ennemi extends Person {
	
	public Ennemi(Weapon weapon, int health, int armor, int speedOfMoove) {
		super(weapon, health, armor, speedOfMoove);
	}


	FieldOfView FOV;	//ou rajoute t on le FOV moi je voudrais mettre dans le constructeur??
	
	
	
	public FieldOfView getFieldOfView(){
		return(FOV);
	}
	
	public void setFieldOfView(){
		//def la valeur du FOV (le meme sur cot�+devant)
		//peut diminuer avec des sorts ou autresS
	}
	
	public void move(){
		//se ferra par Threads
		//syst�me al�atoire soit immobile soit pr�d�fini
	}
	
	public void attck(){
		/*mthsimilaire � celle pr�d�finie: d�s quil est � cot� du player il attaque
		 * IsPlayerThere()
		 * oui attaque
		 * non avance selon FOV
		 */
		
	}
	
	public void disapear(){
	/*if(vivant == false){
	 * 	envoie de msg pour sp�cifier que la casse change de nature
	 * 	chgment de nature selon les probabilit�es suivantes		
		}*/
		Random rand = new Random();
		int nombre = rand.nextInt(1);
		if(nombre < 0.2){
			//case == rien donc nv emplacement libre
		}
		if(nombre <= 0.2 & nombre < 0.4){
			//vie � prendre donc case d'int�raction
		}
		if(nombre <= 0.4 & nombre < 0.6){
			//sous � prendre donc case d'int�raction 
		}
		if(nombre <= 0.6 & nombre < 0.8){
			//armure � prendre donc case d'int�raction 
		}
		else{
			//??
		}

	}

}
