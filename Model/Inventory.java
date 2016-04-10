import java.util.ArrayList;

public class Inventory {
	
	//interface graphique??

	
	int sizeMaxArme = 3;
	int sizeMaxItem = 5;
	ArrayList<Weapon> weaponsOfInventory = new ArrayList<Weapon>();
	ArrayList<Item> itemsOfInventory = new ArrayList<Item>();
	
	//uneArme provient de la fction collect du player
	
	public void ajoutWeapon(Weapon uneArme){
		if (weaponsOfInventory.size() < sizeMaxArme){
			weaponsOfInventory.add(uneArme);
		}else{
			//message d'erruer : full biatch
		}	
	}
	
	//unObjet provient de la fction collect du player
	
	public void ajoutItem(Item unObjet){
		if (itemsOfInventory.size() < sizeMaxItem){
			itemsOfInventory.add(unObjet);
		}else{
			//message d'erreur : full bitch
		}	
	}
	

	public void deleteArme(Weapon pointeA){
		//activé et selectionné par un keyListener ==> de nouveau un pointeur
		//Position dans l'inventaire
		//n peu comme la carte mais en plus petit
		weaponsOfInventory.remove(pointeA);
	}
	
	public void deleteItem(Item pointeI){
		//activé et selectionné par un keyListener ==> de nouveau un pointeur
		//Position dans l'inventaire
		//n peu comme la carte mais en plus petit
		itemsOfInventory.remove(pointeI);		
	}
	
	public void use(){
		//selection par keyListenner flèche et utilisation une touche
		//potion
		//vie
		//player.setHealth(health + 1);
		//Armor
		//player.setArmor(armor + 1);
		//Bonus de vitesse
		//player.setSpeedOfMoove(player.getSpeedOfMoove() + 1 );
	}
	
}
