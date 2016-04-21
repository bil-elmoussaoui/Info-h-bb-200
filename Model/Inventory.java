package Model;

import java.util.ArrayList;

/*
TODO :
- implement inventoryWindow and a keylistenner! (Controller)
- handle exception
- implement useWeapon
- implement useItem
 */

public class Inventory {
    private int sizeMaxWeapon = 3;
    private int sizeMaxItem = 5;
    private ArrayList<Weapon> weaponsInventory = new ArrayList<>();
    private ArrayList<Item> itemsInventory = new ArrayList<>();

	public void addWeapon(Weapon weapon){
		if (weaponsInventory.size() < sizeMaxWeapon){
            weaponsInventory.add(weapon);
		}else{

		}
	}
	
    public void addItem(Item item){
		if (itemsInventory.size() < sizeMaxItem){
            itemsInventory.add(item);
		}else{

		}
	}

	public void removeWeapon(Weapon weapon){
        weaponsInventory.remove(weapon);
	}
	
	public void removeItem(Item item){
        itemsInventory.remove(item);
	}

    public void useItem(){

    }

    public void useWeapon(){

    }
}