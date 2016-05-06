package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/*
TODO :
- handle exceptions
 */

public class Inventory implements Serializable {
    public int sizeMaxWeapon = 5; // nombre de weapons maximale que peut le joueur avoir
    public int sizeMaxItem = 3; // nombre d'items(potions) maximale que le joueur peut avoir
    private ArrayList<Weapon> weaponsInventory = new ArrayList<>();
    private ArrayList<Item> itemsInventory = new ArrayList<>();

    public void addWeapon(Weapon weapon) {
        if (weaponsInventory.size() < sizeMaxWeapon) {
            weaponsInventory.add(weapon);
        }
    }

    public boolean containsWeapon(Weapon weapon) {
        return weaponsInventory.contains(weapon);
    }

    public void addItem(Item item) {
        if (itemsInventory.size() < sizeMaxItem) {
            itemsInventory.add(item);
        }
    }

    public Item getItem(int index) {
        if (countItems() - 1 >= index) {
            return itemsInventory.get(index);
        } else {
            return null;
        }
    }

    public Weapon getWeapon(int index) {
        return weaponsInventory.get(index);
    }

    public int countWeapons() {
        return weaponsInventory.size();
    }

    public int countItems() {
        return itemsInventory.size();
    }

    public Weapon randomWeapon() {
        Random rand = new Random();
        return weaponsInventory.get(rand.nextInt(countWeapons()));
    }

    public void removeWeapon(Weapon weapon) {
        weaponsInventory.remove(weapon);
    }

    public void removeItem(Item item) {
        itemsInventory.remove(item);
    }
}