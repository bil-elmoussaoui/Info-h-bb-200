package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
    private int[][] generatedMap;
    private Game game;
    private int maxMonster = 8;
    private int minMonsters = 4;
    private int minItems = 3;
    private int maxItems = 5;


    public MapGenerator(Game game){
        this.game = game;
        // generate empty maps
        generatedMap = new int[Game.sizeX][Game.sizeX];
        // create random walls!
        this.initWalls();
        this.initPlayer();
        this.intiMonsters();
        this.intiItems();
        int[] inPosition = this.getRandomPosition();
        //int[] outPosition = this.getRandomPosition();
        game.tiles.add(new Trap(inPosition[0], inPosition[1]));
    }

    public void initWalls(){
        // draw outline walls
        for(int i = 0; i < Game.sizeX; i++){
            generatedMap[i][0] = 1;
            game.walls.add(new Wall(i, 0));
            generatedMap[i][Game.sizeY - 1] = 1;
            game.walls.add(new Wall(i, Game.sizeY - 1));
            for(int j = 0; j < Game.sizeY; j++){
                game.tiles.add(new Tile(i,j));
                generatedMap[0][j] = 1;
                game.walls.add(new Wall(0, j));
                generatedMap[Game.sizeX - 1][j] = 1;
                game.walls.add(new Wall(Game.sizeX - 1, j));
            }
        }

        devide(new Rectangle(1, 1, Game.sizeX - 1, Game.sizeY - 1), false);
        int j = 0;
        int tilesSize = game.tiles.size();
        while (j < tilesSize && tilesSize != 0) {
            Tile tile = game.tiles.get(j);
            int x = tile.getPositionX();
            int y = tile.getPositionY();
            if(generatedMap[x][y] != 0) {
                game.tiles.remove(tile);
                tilesSize -= 1;
            }
            j+=1;
        }
    }
    /*
    public int[] randomWall(Rectangle square, boolean isHorizental){
        Random rand = new Random();
        boolean found = false;
        int wallPosition = 0;
        int wallRandom = 0;
        int wallX, wallY;
        boolean condition = false;
        while(!found){
            wallRandom = (isHorizental ? rand.nextInt(square.height - 2) : rand.nextInt(square.width - 2));
            wallPosition = (isHorizental ? square.y +  wallRandom : square.x +  wallRandom);
            condition = (isHorizental? Math.abs(square.height - wallPosition) > 2 && Math.abs(square.y - wallPosition) > 2 :
                    Math.abs(square.x - wallPosition) > 2 && Math.abs(square.width - wallPosition) > 2);
            if(condition){
                found = true;
            }
        }
        wallX = square.x + (isHorizental ? 0 : wallRandom);
        wallY = square.y + (isHorizental ? wallRandom: 0);
        return new int[]{wallX, wallY};
    }
    */
    public void devide(Rectangle square, boolean wasHorizental){
        if(canBeDevided(square)) {
            Random rand = new Random();
            boolean isHorizental = this.getOrientation(square.width, square.height, wasHorizental);
            //int[] randWall = randomWall(square, isHorizental);
            int wallX = square.x + (isHorizental? 0 :rand.nextInt(square.width - 2));
            int wallY = square.y + (isHorizental? rand.nextInt(square.height - 2) : 0);
            int passageX = wallX + (isHorizental ? rand.nextInt(square.width) : 0);
            ArrayList<Integer> passageXList = new ArrayList<>();
            for(int i = -1; i < 1; i ++){ passageXList.add(passageX + i);}
            int passageY = wallY + (isHorizental ? 0 : rand.nextInt(square.height));
            ArrayList<Integer> passageYList = new ArrayList<>();
            for(int i = -1; i < 1; i ++){ passageYList.add(passageY + i);}
            int i = 0;
            while(i < (isHorizental ? square.width : square.height)){
                if(!passageXList.contains(wallX) || !passageYList.contains(wallY)) {
                    generatedMap[wallX][wallY] = 1;
                    game.walls.add(new Wall(wallX ,wallY));
                }
                wallX += isHorizental ? 1 : 0;
                wallY += isHorizental ? 0 : 1;
                i++;
            }
            devide(new Rectangle(square.x, square.y, isHorizental ? square.width : wallX - square.x + 1, isHorizental ? wallY - square.y + 1 : square.height), isHorizental);
            devide(new Rectangle(isHorizental ? square.x : wallX + 1, isHorizental ? wallY + 1 : square.y, isHorizental ? square.width : square.x + square.width - wallX - 1, isHorizental ? square.y + square.height - wallY - 1 : square.height),isHorizental);

        }
    }

    public boolean getOrientation(int width, int height, boolean wasHorizental){
        boolean isHorizental;
        Random rand = new Random();
        if(width < height){
            isHorizental = true;
        } else if(width > height){
            isHorizental = false;
        } else {
            isHorizental = !wasHorizental;
        }
        return isHorizental;
    }

    public boolean canBeDevided(Rectangle square){
        int width = square.width - square.x;
        int height = square.height - square.y;
        return (width > 2 && height > 2);
    }

    public void initPlayer(){
        int[] randomPosition = this.getRandomPosition();
        game.player = new Player(randomPosition[0], randomPosition[1]);
    }

    public void intiMonsters(){
        Random rand = new Random();
        int nbMonsters = rand.nextInt(maxMonster - minMonsters) + minMonsters;
        for(int i = 0 ; i < nbMonsters; i ++ ){
            int[] randomPosition = this.getRandomPosition();
            game.monsters.add(new Monster(randomPosition[0], randomPosition[1]));
        }
    }

    public void intiItems(){
        int[] keyPosition = this.getRandomPosition();
        game.items.add(new Key(keyPosition[0], keyPosition[1]));
        Random rand = new Random();
        int nbItems = rand.nextInt(maxItems - minItems) + minItems;
        for(int i = 0 ; i < nbItems; i ++ ){
            int[] randomPosition = this.getRandomPosition();
            switch (rand.nextInt(3)){
                case 0:
                    game.items.add(new Coin(randomPosition[0], randomPosition[1]));
                break;
                case 1:
                    game.items.add(new Heart(randomPosition[0], randomPosition[1]));
                break;
                case 2:
                    game.items.add(new WoodBox(randomPosition[0], randomPosition[1]));
                break;
            }
        }
    }

    public int[] getRandomPosition(){
        boolean foundPosition = false;
        int positionX = 0;
        int positionY = 0;
        Random rand = new Random();
        while(!foundPosition){
            positionX = rand.nextInt(Game.sizeX);
            positionY = rand.nextInt(game.sizeY);
            if(Game.freePositions[positionX][positionY] == 0){
                foundPosition = true;
            }
        }
        return new int[]{positionX, positionY};
    }

    public int[][] getGeneratedMap(){
        return this.generatedMap;
    }
}
