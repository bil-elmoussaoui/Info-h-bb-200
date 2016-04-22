package Model;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;
import View.MainWindow;

public class Game{
    public Player player;
    public static int[][] freePositions;
    public ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<WoodBox> woodBoxes = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private Inventory inventory;
    public MainWindow window;
    private int[][] map;
    public static int sizeY = 100;
    public static int sizeX = 100;
    public static int shownSizeX;
    public static int shownSizeY;

    public Game(MainWindow window) throws Exception {
        this.window = window;
        player = new Player(1, 1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        shownSizeX = (int)(Math.floor(screenSize.getWidth()/32)) + 1;
        shownSizeY = (int)(Math.floor(screenSize.getHeight()/32));
        freePositions = new int[this.sizeX][this.sizeY];
        map = new int[this.sizeX][this.sizeY];
        this.generateMap();
        this.refreshMap();
    }

    public int[][] getFreePositions(){
        return this.freePositions;
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

    public void generateMap(){
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++ ){
                walls.add(new Wall(0,j));
                walls.add(new Wall(sizeX - 1, j));
            }
            walls.add(new Wall(i,0));
            walls.add(new Wall(i, sizeY -1));
        }

        woodBoxes.add(new WoodBox(10,12));
        woodBoxes.add(new WoodBox(30,10));
        woodBoxes.add(new WoodBox(3, 8));
        woodBoxes.add(new WoodBox(2,2));
        woodBoxes.add(new WoodBox(9, 10));
        monsters.add(new Monster(5, 5, this));
        monsters.add(new Monster(20, 3, this));
        monsters.add(new Monster(10, 13, this));
        Coin coin = new Coin(20, 20);
        coin.setGame(this);
        items.add(coin);

    }

    public int[][] getMap(){

        for(int i = 0; i < this.sizeX; i++){
            for(int j = 0; j < this.sizeY; j++){
                map[i][j] = 0;
                freePositions[i][j] = 0;
            }
        }
        for(Wall wall: walls){
            int x = wall.getPositionX();
            int y = wall.getPositionY();
            map[x][y] = 1;
            freePositions[x][y] = 1;
        }

        for(WoodBox woodbox: woodBoxes){
            int x = woodbox.getPositionX();
            int y = woodbox.getPositionY();
            map[x][y] = 3;
            freePositions[x][y] = 1;
        }

        for(Item item: items){
            int x = item.getPositionX();
            int y = item.getPositionY();
            if(item instanceof Coin){
                map[x][y] = 6;
            } else if (item instanceof Heart){
                map[x][y] = 7;
            } else if (item instanceof Key) {
                map[x][y] = 8;
            }
            freePositions[x][y] = 0;
        }

        for(Monster monster: monsters){
            int x = monster.getPositionX();
            int y = monster.getPositionY();
            map[x][y] = 5;
            freePositions[x][y] = 1;
        }
        map[player.getPositionX()][player.getPositionY()] = 2;
        freePositions[player.getPositionX()][player.getPositionY()] = 1;
        return map;
    }

    public int[][] getVisibleMap(){
        int[][] map = this.getMap();
        int startPositionX = player.getPositionX() - (int)Math.floor(Game.shownSizeX/2) - 1;
        int endPositionX = player.getPositionX() + (int)Math.floor(Game.shownSizeX/2);
        int startPositionY = player.getPositionY() - (int)Math.floor(Game.shownSizeY/2);
        int endPositionY = player.getPositionY() + (int)Math.floor(Game.shownSizeY/2);
        if(endPositionX > Game.sizeX || endPositionX - startPositionX < Game.shownSizeX){
            startPositionX = Game.sizeX - Game.shownSizeX;
            endPositionX = Game.sizeX;
        }
        if(startPositionX < 0){
            startPositionX = 0;
            endPositionX = Game.sizeX;
        }
        if(endPositionY > Game.sizeY  || endPositionY - startPositionY < Game.shownSizeY) {
            startPositionY = Game.sizeY - Game.shownSizeY;
            endPositionY = Game.sizeY;
        }
        if(startPositionY < 0){
            startPositionY = 0;
            endPositionY = Game.sizeY;
        }
        int[][] visibleMap = new int[endPositionX - startPositionX][endPositionY - startPositionY];
        for (int i = startPositionX; i < endPositionX; i++) {
            for(int j = startPositionY; j < endPositionY; j ++){
                visibleMap[i - startPositionX][j - startPositionY] = map[i][j];
            }
        }
        return visibleMap;
    }

    public void refreshMap(){
        if(MainWindow.newGame){
           /* not sure about my code?
            monsters = new ArrayList<>();
            woodBoxes = new ArrayList<>();
            walls = new ArrayList<>();
            items = new ArrayList<>();
            MainWindow.newGame = false;
            MainWindow.gamePaused = false;
            this.generateMap();
            try {
                Thread.sleep(300);
            }catch (Exception e){

            }*/
        }
        window.draw(this.getVisibleMap());
    }

}