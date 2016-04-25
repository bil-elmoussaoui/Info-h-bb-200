package Model;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;
import View.MainWindow;

public class Game{
    public Player player;
    public static int[][] freePositions;
    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<WoodBox> woodBoxes = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Tile> tiles = new ArrayList<>();
    private Inventory inventory;
    public MainWindow window;
    private int[][] map;
    // screen information
    public static int sizeY = 50;
    public static int sizeX = 50;
    public static int shownSizeX;
    public static int shownSizeY;
    public static int screenX;
    public static int screenY;

    public Game(MainWindow window) throws Exception {
        this.window = window;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenX = (int)screenSize.getWidth();
        screenY = (int)screenSize.getHeight();
        shownSizeX = (int)Math.floor(screenX/32) + 1;
        shownSizeY = (int)Math.floor(screenY/32);
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

    /*public int[][] getVisibleMap(){

        int[][] visibleMap = new int[endPositionX - startPositionX][endPositionY - startPositionY];
        for (int i = startPositionX; i < endPositionX; i++) {
            for(int j = startPositionY; j < endPositionY; j ++){
                visibleMap[i - startPositionX][j - startPositionY] = map[i][j];
            }
        }
        return visibleMap;
    }*/

    public void refreshMap(){
        window.draw(this.map, this);
    }

}