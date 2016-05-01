package Model;

import View.MainWindow;

import java.awt.*;
import java.util.ArrayList;

public class Game{
    public Player player = null;
    public static int[][] freePositions;
    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Tile> tiles = new ArrayList<>();
    public MainWindow window;
    public MapGenerator mapGenerator;
    public Door door;
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
        return monsters;
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
        mapGenerator = new MapGenerator(this);
        map = mapGenerator.getGeneratedMap();
    }

    public void refreshMap(){
        if(MainWindow.newGame){
            MainWindow.newGame = false;
            newGame();
        }
        window.draw(map, this);
    }

    public void playerAttack(){
        if(player.weapon != null && !player.weapon.getIsDistanceWeapon()) {
            int[] position = player.getAttackedPosition();
            for (int i = 0; i < monsters.size(); i++) {
                Monster monster = monsters.get(i);
                if (position[0] == monster.getPositionX() && position[1] == monster.getPositionY()) {
                    player.attack(monster);
                    if (!monster.isAlive()) {
                        monsters.remove(monster);
                        Game.freePositions[position[0]][position[1]] = 0;
                        refreshMap();
                        break;
                    }
                }
            }

            for (int j = 0; j < items.size(); j++) {
                Item item = items.get(j);
                if (item.getIsBreakable()) {
                    if (position[0] == item.getPositionX() && position[1] == item.getPositionY()) {
                        if (((WoodBox) item).content != null) {
                            items.add(((WoodBox) item).content);
                        }
                        items.remove(item);
                        freePositions[position[0]][position[1]] = 0;
                        refreshMap();
                        break;
                    }
                }
            }
        }
    }

    public void playerThrowWeapon(){
        if(player.weapon != null) {
            player.weapon.setPositionX(player.getPositionX());
            player.weapon.setPositionY(player.getPositionY());
            items.add(player.weapon);
            player.throwWeapon(player.weapon);
            refreshMap();
        }
    }

    public void trapDamage(Tile tile){
        int[] position = new int[]{tile.getPositionX(),tile.getPositionY()};
        for(int i = 0; i < monsters.size(); i ++) {
            Monster monster = monsters.get(i);
            if (position[0] == monster.getPositionX() && position[1] == monster.getPositionY()) {
                ((Trap)tile).attack(monster);
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
            ((Trap)tile).attack(player);
            if (!player.isAlive()) {
                // kill player?
            }
        }
    }

    public void playerCollect(){
        for(int j = 0; j < items.size(); j ++){
            Item item = items.get(j);
            if (player.getPositionX() == item.getPositionX() && player.getPositionY() == item.getPositionY()) {
                if (item.getIsCollectable()) {
                    if(item instanceof Heart){
                        if(player.inventory.countItems() < player.inventory.sizeMaxItem){
                            player.collect(item);
                            items.remove(item);
                            break;
                        } else if(player.getHealth() < player.healthMax){
                            player.setHealth(player.getHealth() + ((Heart)item).getHealth());
                        }
                    } else {
                        player.collect(item);
                        items.remove(item);
                        break;
                    }
                }
            }

        }
    }

    public void openDoor(){
        int[] position = player.getAttackedPosition();
        if(player.hasKey && door != null){
            if(position[0] == door.getPositionX() && position[1] == door.getPositionY()){
                player.removeKey();
                door.setIsOpen(true);
                mapGenerator.breakWall(position[0], position[1]);
            }
            try {Thread.sleep(100);}catch (Exception e){}
            newGame();
        }
    }

    public void newGame() {
        monsters.clear();
        tiles.clear();
        walls.clear();
        items.clear();
        player.removeKey();
        freePositions = new int[this.sizeX][this.sizeY];
        map = new int[this.sizeX][this.sizeY];
        generateMap();
        refreshMap();

    }
}