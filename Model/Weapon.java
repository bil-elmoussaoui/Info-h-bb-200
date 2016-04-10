import java.util.ArrayList;

public class Weapon {
	
	private int damage;
	private int level;
	public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	//weapons.add(weapon1,weapon2,weapon3,weapon4,weapon5);
	
	
	
	public Weapon (int damage){
		this.damage = damage;
	}
	
	
	ArrayList<Integer> enemiDamage = new ArrayList<>();
	//enemiDamage.add(1,2,3,4,5,6);
	
	
	/*
	public void setDamage(){
		if (instanceof xox.class == Ennemi){//xox cestle perso pointé
			damage = enemiDamage[level];
		}
		else if (instanceof xox.class == Player){
			//dpdt du niveau t du type de larme==> arme 1 à damage 1
		}
		else if (instanceof xox.class = Prisonner){
			damage = 2;
		}
	}
	*/
	public int getDamage(){
		return damage;
	}

}
