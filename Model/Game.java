package Model;

import View.MainWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game{
    public Player player = null;
    public static int[][] freePositions;
    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Tile> tiles = new ArrayList<>();
    private Inventory inventory;
    public MainWindow window;
    private int[][] map;


    // screen information
    public static int sizeY = 100;
    public static int sizeX = 100 ;
    public static int shownSizeX;
    public static int shownSizeY;
    public static int screenX;
    public static int screenY;
    public static int pixelX = 64;
    public static int pixelY = 64;

    public Game(MainWindow window) throws Exception {
        this.window = window;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenX = (int)screenSize.getWidth();
        screenY = (int)screenSize.getHeight();
        shownSizeX = (int)Math.floor(screenX/pixelX) + 1;
        shownSizeY = (int)Math.floor(screenY/pixelY) - 1;
        sizeX = shownSizeX;
        sizeY = shownSizeY;
        freePositions = new int[this.sizeX][this.sizeY];
        map = new int[this.sizeX][this.sizeY];
        this.generateMap();
        this.refreshMap();
    }

    public ArrayList<Monster> getMonsters(){
        return this.monsters;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Wall> getWalls(){
        return walls;
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public void generateMap(){
        MapGenerator mapGenerator = new MapGenerator(this);
        this.map = mapGenerator.getGeneratedMap();
    }

    public void refreshMap(){
        if(MainWindow.newGame){
            tiles = new ArrayList<>();
            monsters = new ArrayList<>();
            items = new ArrayList<>();
            walls = new ArrayList<>();
            freePositions = new int[this.sizeX][this.sizeY];
            map = new int[this.sizeX][this.sizeY];
            this.generateMap();
        }
        window.draw(this.map, this);
    }

    public void playerAttack(){
        int[] position = player.getAttackedPosition();
        int monstersSize = monsters.size();
        int i = 0;
        while (i < monstersSize && monstersSize != 0) {
            Monster monster = monsters.get(i);
            int x = monster.getPositionX();
            int y = monster.getPositionY();
            if (position[0] == x && position[1] == y) {
                player.attack(monster);
                if (!monster.isAlive()) {
                    monsters.remove(monster);
                    monstersSize -= 1;
                    map[x][y] = 0;
                    Game.freePositions[x][y] = 0;
                    this.refreshMap();
                }
            }
            i += 1;
        }
        int itemsSize = items.size();

        if(itemsSize > 0) {
            int j = 0;
            while (j < itemsSize && itemsSize != 0) {
                Item item  = items.get(j);
                if(item.getIsBreakable()) {
                    int x = item.getPositionX();
                    int y = item.getPositionY();
                    if (position[0] == x && position[1] == y) {
                        items.remove(item);
                        itemsSize -= 1;
                        Random randomItem = new Random();
                        switch (randomItem.nextInt(3)) {
                            case 1:
                                items.add(new Coin(x, y));
                            break;
                            case 2:
                                items.add(new Heart(x, y));
                            break;
                            case 3:
                                items.add(new Key(x, y));
                            break;
                        }
                        Game.freePositions[x][y] = 0;
                    }
                }
                j+=1;
            }
        }
    }

    public void trapDammage(Tile tile){
        int[] position = new int[]{tile.getPositionX(),tile.getPositionY()};
        int monstersSize = monsters.size();
        int i = 0;
        while (i < monstersSize && monstersSize != 0) {
            Monster monster = monsters.get(i);
            int x = monster.getPositionX();
            int y = monster.getPositionY();
            if (position[0] == x && position[1] == y) {
                ((Trap)tile).attack(monster);
                if (!monster.isAlive()) {
                    monsters.remove(monster);
                    monstersSize -= 1;
                    map[x][y] = 0;
                    Game.freePositions[x][y] = 0;
                    this.refreshMap();
                }
            }
            i += 1;
        }
        if (position[0] == player.getPositionX() && position[1] == player.getPositionY()) {
            ((Trap)tile).attack(player);
            if (!player.isAlive()) {
                /*monsters.remove(monster);
                monstersSize -= 1;
                map[x][y] = 0;
                Game.freePositions[x][y] = 0;
                this.refreshMap();*/
            }
        }
    }

    public void playerCollect(){
        int itemsSize = items.size();
        if(itemsSize > 0){
            int j = 0;
            while (j < itemsSize && itemsSize != 0) {
                Item item = items.get(j);
                if(item.getIsCollectable()) {
                    int x = item.getPositionX();
                    int y = item.getPositionY();
                    if (player.getPositionX() == x && player.getPositionY() == y) {
                        if(player.inventory.countItems() < player.inventory.sizeMaxItem) {
                            player.collect(item);
                            items.remove(item);
                            itemsSize -= 1;
                            freePositions[x][y] = 0;
                        }
                    }
                }
                j+=1;
            }
        }
    }

}