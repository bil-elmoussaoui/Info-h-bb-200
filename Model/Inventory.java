package Model;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {
    private int sizeMaxArme = 3;
    private int sizeMaxItem = 5;
    private ArrayList<Weapon> weaponsOfInventory = new ArrayList<Weapon>();
    private ArrayList<Item> itemsOfInventory = new ArrayList<Item>();

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
		//activ� et selectionn� par un keyListener ==> de nouveau un pointeur
		//Position dans l'inventaire
		//n peu comme la carte mais en plus petit
		weaponsOfInventory.remove(pointeA);
	}
	
	public void deleteItem(Item pointeI){
		//activ� et selectionn� par un keyListener ==> de nouveau un pointeur
		//Position dans l'inventaire
		//n peu comme la carte mais en plus petit
		itemsOfInventory.remove(pointeI);		
	}
	
	public void use(){
		//selection par keyListenner fl�che et utilisation une touche
		//potion
		//vie
		//player.setHealth(health + 1);
		//Armor
		//player.setArmor(armor + 1);
		//Bonus de vitesse
		//player.setSpeedOfMoove(player.getSpeedOfMoove() + 1 );
	}
	
}
