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
    private int itteration = 0;

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
        Random rand = new Random();
        boolean isHorizental = !wasHorizental;
        if(canBeDevided(square, isHorizental)) {
           boolean found = false;
            int wall = 0;
            while(!found){
                if(isHorizental){
                    wall = square.y +  rand.nextInt(square.height - 2);
                    found = (Math.abs(square.x - wall) > 1 && Math.abs(wall - square.width) > 1);
                } else {
                    wall = square.x + rand.nextInt(square.width - 2);
                    found = (Math.abs(square.y - wall) > 1 && Math.abs(wall - square.height) > 1);
                }
            }
            /*
            int passageX = wallX + (isHorizental ? rand.nextInt(square.width) : 0);
            int passageY = wallY + (isHorizental ? 0 : rand.nextInt(square.height));
             */
            if(isHorizental){
                int passage = square.x + rand.nextInt(square.width);
                ArrayList<Integer> passageList = new ArrayList<>();
                for(int i = -1; i < 1; i ++){ passageList.add(passage + i);}
                int i = 0;
                while(i < square.width){
                    if(!passageList.contains(square.x + i)){
                        generatedMap[square.x + i][wall] = 1;
                        game.walls.add(new Wall(square.x + i, wall));
                    }
                    i++;
                }
                devide(new Rectangle(square.x, square.y, square.width, wall - square.y + 1), isHorizental);
                devide(new Rectangle(square.x, wall + 1, square.width, square.y + square.height - wall - 1), isHorizental);
            } else {
                int passage = square.y + rand.nextInt(square.height);
                ArrayList<Integer> passageList = new ArrayList<>();
                for(int i = -1; i < 1; i ++){ passageList.add(passage + i);}
                int i = 0;
                while(i < square.height){
                    if(!passageList.contains(square.y + i)){
                        generatedMap[wall][square.y + i] = 1;
                        game.walls.add(new Wall(wall, square.y + i));
                    }
                    i++;
                }
                devide(new Rectangle(square.x, square.y, wall - square.x + 1, square.height), isHorizental);
                devide(new Rectangle(wall + 1, square.y, square.x + square.width - wall - 1, square.height),isHorizental);

            }
        }
    }

    public boolean canBeDevided(Rectangle square, boolean isHorizental){
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
                case 3: // Weapons
                    switch (0){ // rand.nextInt(4)
                        case 0:
                            game.items.add(new Dagger(randomPosition[0], randomPosition[1], 1));
                        break;
                        case 1:
                            game.items.add(new Staff(randomPosition[0], randomPosition[1], 1));
                        break;
                        case 2:
                            game.items.add(new Spear(randomPosition[0], randomPosition[1], 1));
                        break;
                        case 3:
                            game.items.add(new Bow(randomPosition[0], randomPosition[1], 1));
                        break;
                    }
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
