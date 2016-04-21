package Model;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;
import View.MainWindow;
import sun.applet.Main;

public class Game{
    public Player player;
    public static int[][] freePositions;
    public ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<WoodBox> woodBoxs = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private Inventory inventory;
    private MainWindow window;

    private int[][] map;
    private int sizeY = 28;
    private int sizeX = 57;

    public Game(MainWindow window) throws Exception {
        this.window = window;
        player = new Player(1, 1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.sizeX = (int)(Math.floor(screenSize.getWidth()/24)) + 1;
        this.sizeY = (int)(Math.floor(screenSize.getHeight()/24)) - 3;
        freePositions = new int[this.sizeX][this.sizeY];
        map = new int[this.sizeX][this.sizeY];
        this.generateMap();
        window.draw(this.getMap());
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


        woodBoxs.add(new WoodBox(10,12));
        woodBoxs.add(new WoodBox(30,10));
        woodBoxs.add(new WoodBox(3, 8));
        woodBoxs.add(new WoodBox(2,2));
        woodBoxs.add(new WoodBox(9, 10));
        monsters.add(new Monster(5, 5, this));
        monsters.add(new Monster(20, 3, this));
        monsters.add(new Monster(10, 13, this));

    }

    public int[][] getMap(){
        if(MainWindow.newGame){
            this.generateMap();
        }
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

        for(WoodBox woodbox: woodBoxs){
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

    public void refreshMap(){
        window.draw(this.getMap());
    }

}