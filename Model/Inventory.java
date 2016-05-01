package Model;

import java.util.ArrayList;
import java.util.Random;

/*
TODO :
- implement inventoryWindow and a keylistenner! (Controller)
- handle exception
- implement useWeapon
- implement useItem
 */

public class Inventory {
    public int sizeMaxWeapon = 5;
    public int sizeMaxItem = 3;
    private ArrayList<Weapon> weaponsInventory = new ArrayList<>();
    private ArrayList<Item> itemsInventory = new ArrayList<>();

	public void addWeapon(Weapon weapon){
		if (weaponsInventory.size() < sizeMaxWeapon){
            weaponsInventory.add(weapon);
		}
	}

    public boolean containsWeapon(Weapon weapon){
        return weaponsInventory.contains(weapon);
    }

    public void addItem(Item item){
		if (itemsInventory.size() < sizeMaxItem){
            itemsInventory.add(item);
		}
	}

    public Item getItem(int index){
        return itemsInventory.get(index);
    }

    public Weapon getWeapon(int index){
        return weaponsInventory.get(index);
    }

    public int countWeapons(){
        return weaponsInventory.size();
    }

    public int countItems(){
        return itemsInventory.size();
    }

    public Weapon randomWeapon(){
        Random rand = new Random();
        return weaponsInventory.get(rand.nextInt(countWeapons()));
    }

	public void removeWeapon(Weapon weapon){
        weaponsInventory.remove(weapon);
	}
	
	public void removeItem(Item item){
        itemsInventory.remove(item);
	}

    public void useItem(Item item){

    }

    public void useWeapon(Weapon weapon){

    }
}