package Model;
import java.util.ArrayList;
import View.MainWindow;

public class Game{
    public Player player = new Player(1, 1);
    public static int[][] freePositions;
    public ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private Inventory inventory;
    private MainWindow window;
    private int sizeY = 32;
    private int sizeX = 48;

    public Game(MainWindow window){
        this.window = window;
        freePositions = new int[this.sizeX][this.sizeY];
        this.generateMap();
        this.inventory = new Inventory();
        window.draw(this.getMap());
        //Thread thread = new Thread();
        //thread.start();
    }
    /*
    public void run(){
        try{
            while(true){
                Thread.sleep(1000);
                this.window.draw(this.getMap());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */

    public void generateMap(){
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++ ){
                walls.add(new Wall(0,j));
                walls.add(new Wall(sizeX - 1, j));
            }
            walls.add(new Wall(i,0));
            walls.add(new Wall(i, sizeY -1));
        }
        monsters.add(new Monster(5, 5, this));
        monsters.add(new Monster(10, 11, this));
        monsters.add(new Monster(30, 20, this));

    }

    public int[][] getMap(){
        int[][] map = new int[this.sizeX][this.sizeY];
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

        for(Monster monster: monsters){
            int x = monster.getPositionX();
            int y = monster.getPositionY();
            map[x][y] = 5;
            freePositions[x][y] = 1;
        }
        map[player.getPositionX()][player.getPositionY()] = 2;
        return map;
    }

    public void refreshMap(){
        window.draw(this.getMap());
    }

    public void movePlayer(int positionX, int positionY){
        player.move(positionX, positionY);
        window.draw(this.getMap());
    }

}
