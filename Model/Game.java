package Model;

import View.MainWindow;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable{
    private static final long serialVersionUID = 1L;
    public Player player = null;
    public static int[][] freePositions;
    public Salesman salesman = null;
    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Tile> tiles = new ArrayList<>();
    public ArrayList<Weapon> thrownWeapons = new ArrayList<>();
    public ArrayList<Spell> thrownSpells = new ArrayList<>();
    public transient MainWindow window;
    public MapGenerator mapGenerator;
    public static boolean enVie = true;
    public static boolean playerIsShopping = false;
    public Door door;
    private int[][] map;
    // screen information
    public static int sizeY = 25;
    public static int sizeX = 75 ;
    public static int shownSizeX;
    public static int shownSizeY;
    public static int screenX;
    public static int screenY;
    public static int pixelX = 64;
    public static int pixelY = 64;

    public Game(MainWindow window) {
        this.window = window;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenX = (int)screenSize.getWidth();
        screenY = (int)screenSize.getHeight();
        shownSizeX = (int)Math.floor(screenX/pixelX) + 1;
        shownSizeY = (int)Math.floor(screenY/pixelY) - 1;
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

    public void drawShop(){
        window.showBuyWindow(this);
    }

    public void playerAttack(){
        if(player.weapon != null) {
            if (!player.weapon.getIsDistanceWeapon()) {
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
                        Arrow arrow = new Arrow(1);
                        arrow.setDirection(player.direction);
                        ((Bow) player.weapon).arrow = arrow;
                        ((Bow) player.weapon).addArrows(-1);
                    } else {
                        player.isAttacking = false;
                        player.isMoving = false;
                        player.isSpelling = false;
                        player.weapon.counter.init();
                        player.counter.init();
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
        }
    }

    public void playerCollect(){
        Item item = getItem(player.getPositionX(), player.getPositionY());
        if(item != null){
            if (item.getIsCollectable()) {
                if(item instanceof Heart){
                    if(player.inventory.countItems() < player.inventory.sizeMaxItem){
                        player.collect(item);
                        items.remove(item);
                    } else if(player.getHealth() < player.healthMax){
                        player.setHealth(player.getHealth() + ((Heart)item).getHealth());
                    }
                } else {
                    player.collect(item);
                    items.remove(item);
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
        thrownWeapons.clear();
        player.removeKey();
        if(Game.enVie) {
            player.removeKey();
        } else {
            player = null;
            Game.enVie = true;
        }
        freePositions = new int[this.sizeX][this.sizeY];
        map = new int[this.sizeX][this.sizeY];
        generateMap();
        refreshMap();

    }

    public Wall getWall(int positionX, int positionY){
        Wall wall = null;
        for(int i = 0; i < walls.size(); i ++){
            if(positionX == walls.get(i).getPositionX() && walls.get(i).getPositionY() == positionY){
                wall = walls.get(i);
            }
        }
        return wall;
    }

    public Monster getMonster(int positionX , int positionY){
        Monster monster = null;
        for(int i = 0; i < monsters.size(); i ++){
            if(positionX == monsters.get(i).getPositionX() && monsters.get(i).getPositionY() == positionY){
                monster = monsters.get(i);
            }
        }
        return monster;
    }

    public Item getItem(int positionX, int positionY){
        Item item = null;
        for(int i = 0; i < items.size(); i ++){
            if(positionX == items.get(i).getPositionX() && items.get(i).getPositionY() == positionY){
                item = items.get(i);
            }
        }
        return item;
    }

    public void doSpell(){
        int positionX = player.getPositionX();
        int positionY = player.getPositionY();
        switch(player.direction){
            case 1: positionY -= 1; break;
            case 2: positionX -= 1; break;
            case 3: positionY += 1; break;
            case 4: positionX += 1; break;
        }
        player.doSpell();
        player.spell = new Icetacle(positionX, positionY, player.direction);
        //player.removeSpell();
    }


    public void playerBuy(){
        switch(salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][0]){
            case(5):
                if(player.getCoins() >= salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][1]){
                    player.inventory.addItem(new Heart(null,null));
                    player.setCoins(player.getCoins() - salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][1]);
                }
            case(1):
                if(player.getCoins() >= salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][1]){
                    player.inventory.addItem(new Heart(null,null));
                }
            case(2):
                if(player.getCoins() >= salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][1]){
                    player.inventory.addItem(new Heart(null,null));
                }
            case(3):
                if(player.getCoins() >= salesman.carteAchat[salesman.getSelectorX()][salesman.getSelectorY()][1]){
                    player.inventory.addItem(new Heart(null,null));
                }

        }


    }


}
