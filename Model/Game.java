package Model;

import View.MainWindow;

import java.awt.*;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

public class Game implements Serializable, Observer {
    public static int[][] freePositions;
    public static boolean playerIsShopping = false;
    // screen information
    public static int sizeY = 25;
    public static int sizeX = 75;
    public static int shownSizeX;
    public static int shownSizeY;
    public static int screenX;
    public static int screenY;
    public static int pixelX = 64;
    public static int pixelY = 64;
    public Player player = null;
    public Salesman salesman = null;
    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Tile> tiles = new ArrayList<>();
    public ArrayList<Weapon> thrownWeapons = new ArrayList<>();
    public ArrayList<Spell> thrownSpells = new ArrayList<>();
    transient public MainWindow window;
    public transient MapGenerator mapGenerator;
    public Door door;
    private int[][] map;

    public Game(MainWindow window) {
        this.window = window;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenX = (int) screenSize.getWidth();
        screenY = (int) screenSize.getHeight();
        shownSizeX = (int) Math.floor(screenX / pixelX) + 1;
        shownSizeY = (int) Math.floor(screenY / pixelY) - 1;
        try {
            // read the settings file
            Properties p = new Properties();
            p.load(new FileInputStream("settings.ini"));
            int mapX = Integer.valueOf(p.getProperty("sizeX"));
            int mapY = Integer.valueOf(p.getProperty("sizeY"));
            sizeX = (mapX >= shownSizeX && mapX <= 100) ? sizeX : shownSizeX;
            sizeY = (mapY >= shownSizeY && mapY <= 100) ? sizeY : shownSizeY;
        } catch (Exception e) {
            e.printStackTrace();
        }
        freePositions = new int[this.sizeX][this.sizeY];
        map = new int[this.sizeX][this.sizeY];
        this.generateMap();
        this.refreshMap();
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void generateMap() {
        mapGenerator = new MapGenerator(this);
        map = mapGenerator.getGeneratedMap();
    }

    public void setWindow(MainWindow window) {
        this.window = window;
    }

    // refresh map
    public void refreshMap() {
        if (MainWindow.newGame) {
            MainWindow.newGame = false;
            newGame();
        }
        window.draw(map, this);
    }

    // refresh image objects
    public void refreshImages() {
        player.createImage();
        salesman.createImage();
        door.createImage();
        for (Tile tile : getTiles()) {
            tile.createImage();
        }
        for (Item item : getItems()) {
            if (item instanceof WoodBox) {
                ((WoodBox) item).createImage();
            } else if (item instanceof Key) {
                ((Key) item).createImage();
            } else if (item instanceof Coin) {
                ((Coin) item).createImage();
            } else if (item instanceof Potion) {
                ((Potion) item).createImage();
            }
        }
        for (Monster monster : getMonsters()) {
            monster.createImage();
        }

        for (Wall wall : getWalls()) {
            wall.createImage();
        }
    }

    // draw a shop
    public void drawShop() {
        int[] position = player.getAttackedPosition();
        if (salesman != null) {
            if (position[0] == salesman.getPositionX() && position[1] == salesman.getPositionY()) {
                MainWindow.gamePaused = true;
                Game.playerIsShopping = true;
                window.showBuyWindow(this);
            }
        }
    }

    // player attack
    public void playerAttack() {
        if (player.weapon != null) {
            // if the player is using a bow
            if (!player.weapon.getIsDistanceWeapon()) {
                player.setAttackMode(true);
                int[] position = player.getAttackedPosition();
                Monster monster = getMonster(position[0], position[1]);
                Item item = getItem(position[0], position[1]);

                if (monster != null) {
                    player.attack(monster);
                    if (!monster.isAlive()) {
                        monsters.remove(monster);
                        Game.freePositions[position[0]][position[1]] = 0;
                        refreshMap();
                    }
                }

                if (item != null) {
                    if (item.getIsBreakable()) {
                        ((WoodBox) item).setIsBeingBroken(true);
                    }
                }
            } else if (player.weapon.getIsDistanceWeapon()) {
                if (player.weapon instanceof Bow) {
                    if (((Bow) player.weapon).arrowsCount > 0) {
                        Arrow arrow = new Arrow();
                        arrow.setDirection(player.direction);
                        ((Bow) player.weapon).arrow = arrow;
                        ((Bow) player.weapon).addArrows(-1);
                        player.setAttackMode(true);
                    } else {
                        player.setAttackMode(false);
                    }
                }
            }
        }
    }

    // throw a weapon
    public void playerThrowWeapon() {
        if (player.weapon != null) {
            player.weapon.setPositionX(player.getPositionX());
            player.weapon.setPositionY(player.getPositionY());
            items.add(player.weapon);
            player.throwWeapon(player.weapon);
            refreshMap();
        }
    }

    // trap damage (trap attacks person)
    public void trapDamage(Tile tile) {
        int[] position = new int[]{tile.getPositionX(), tile.getPositionY()};
        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            if (position[0] == monster.getPositionX() && position[1] == monster.getPositionY()) {
                ((Trap) tile).attack(monster);
                if (!monster.isAlive()) {
                    monsters.remove(monster);
                    map[position[0]][position[1]] = 0;
                    freePositions[position[0]][position[1]] = 0;
                    this.refreshMap();
                    break;
                }
            }
        }
        if (position[0] == player.getPositionX() && position[1] == player.getPositionY()) {
            ((Trap) tile).attack(player);
        }
    }

    // collect function, verify if the invenotry is not full
    public void playerCollect() {
        Item item = getItem(player.getPositionX(), player.getPositionY());
        if (item != null) {
            if (item.getIsCollectable()) {
                if (item instanceof Potion) {
                    if (player.inventory.countItems() < player.inventory.sizeMaxItem) {
                        player.collect(item);
                        items.remove(item);
                    }
                } else if(item instanceof Weapon) {
                    if (player.inventory.countWeapons() < player.inventory.sizeMaxWeapon) {
                        player.collect(item);
                        items.remove(item);
                    }
                } else {
                    player.collect(item);
                    items.remove(item);
                }
            }
        }
    }

    // open the door only if the player has the key, and start a new game
    public void openDoor() {
        int[] position = player.getAttackedPosition();
        if (player.hasKey && door != null) {
            if (position[0] == door.getPositionX() && position[1] == door.getPositionY()) {
                player.removeKey();
                door.setIsOpen(true);
                mapGenerator.breakWall(position[0], position[1]);
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            newGame();
        }
    }

    // generate new map, and remove old walls, monsters..
    public void newGame() {
        monsters.clear();
        tiles.clear();
        walls.clear();
        items.clear();
        thrownWeapons.clear();
        player.removeKey();
        if (player.isAlive()) {
            player.removeKey();
        } else {
            player = null;
            window.menuAlive = true;
        }
        freePositions = new int[Game.sizeX][Game.sizeY];
        map = new int[Game.sizeX][Game.sizeY];
        generateMap();
        refreshMap();

    }

    // get a wall if it exists
    public Wall getWall(int positionX, int positionY) {
        Wall wall = null;
        for (int i = 0; i < walls.size(); i++) {
            if (positionX == walls.get(i).getPositionX() && walls.get(i).getPositionY() == positionY) {
                wall = walls.get(i);
            }
        }
        return wall;
    }

    // get a monster if it exists
    public Monster getMonster(int positionX, int positionY) {
        Monster monster = null;
        for (int i = 0; i < monsters.size(); i++) {
            if (positionX == monsters.get(i).getPositionX() && monsters.get(i).getPositionY() == positionY) {
                monster = monsters.get(i);
            }
        }
        return monster;
    }

    //get an item if it exists
    public Item getItem(int positionX, int positionY) {
        Item item = null;
        for (int i = 0; i < items.size(); i++) {
            if (positionX == items.get(i).getPositionX() && items.get(i).getPositionY() == positionY) {
                item = items.get(i);
            }
        }
        return item;
    }

    // verify if the user has enough money to buy and add elements bought to his inventory
    public void playerBuy() {
        int price = salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][1];
        if (player.getCoins() >= price) {
            boolean wasBought = false;
            int selector = salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][0];
            switch (selector) {
                case 1:
                case 2:
                case 3:
                    if (player.inventory.countItems() + 1 <= player.inventory.sizeMaxItem) {
                        Potion spell = new Potion(null, null);
                        if (selector == 2) spell.setType(1);
                        if (selector == 3) spell.setType(2);
                        player.inventory.addItem(spell);
                        wasBought = true;
                    }
                    break;
                case 4:
                    if (player.getArmor() < player.armorMax) {
                        player.setArmor(player.armorMax);
                        wasBought = true;
                    }
                    break;
                case 5: // arrows
                    for (int i = 0; i < player.inventory.countWeapons(); i++) {
                        Weapon weapon = player.inventory.getWeapon(i);
                        if (weapon instanceof Bow) {
                            ((Bow) weapon).arrowsCount += 10;
                            wasBought = true;
                        }
                    }
                    break;
                case 6:
                    if (player.inventory.countWeapons() + 1 <= player.inventory.sizeMaxWeapon) {
                        player.inventory.addWeapon(new Bow(null, null));
                        wasBought = true;
                    }
                    break;
                case 7:
                    if (player.inventory.countWeapons() + 1 <= player.inventory.sizeMaxWeapon) {
                        player.inventory.addWeapon(new Dagger(null, null));
                        wasBought = true;
                    }
                    break;
                case 8:
                    if (player.inventory.countWeapons() + 1 <= player.inventory.sizeMaxWeapon) {
                        player.inventory.addWeapon(new Spear(null, null));
                        wasBought = true;
                    }
                    break;
                case 16:
                    if (!player.hasKey) {
                        player.addKey();
                        wasBought = true;
                    }
                    break;
            }
            if (wasBought) player.setCoins(player.getCoins() - price);
        }
    }

    @Override
    public void update() {
        if (!player.isAlive()) {
            window.showMenuWindow();
        }
    }

}
