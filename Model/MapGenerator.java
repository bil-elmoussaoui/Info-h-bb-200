package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
    private int[][] generatedMap;
    private Game game;
    private int maxMonster = 5;
    private int minMonsters = 3;
    private int minItems = 3;
    private int maxItems = 5;
    public ArrayList<int[]> borderPositions = new ArrayList<>();

    public MapGenerator(Game game){
        this.game = game;
        // generate empty maps
        generatedMap = new int[Game.sizeX][Game.sizeX];
        // create random walls!
        initWalls();
        initPlayer();
        intiMonsters();
        intiItems();
        initDoor();
        int[] inPosition = this.getRandomPosition();
        //int[] outPosition = this.getRandomPosition();
        game.tiles.add(new Trap(inPosition[0], inPosition[1]));
    }

    public void createWall(int x, int y){
        game.walls.add(new Wall(x, y));
        generatedMap[x][y] = 1;
        Game.freePositions[x][y] = 1;
    }


    public void initWalls() {
        devide(new Rectangle(1, 1, Game.sizeX - 1, Game.sizeY - 1), false);
        // draw outline walls
        for (int i = 0; i < Game.sizeX; i++) {
            createWall(i, 0);
            createWall(i, Game.sizeY -1);
            if(i != 0) borderPositions.add(new int[]{i, 0});
            if(i != 0) borderPositions.add(new int[]{i, Game.sizeY - 1});
            for (int j = 0; j < Game.sizeY; j++) {
                createWall(0,j);
                createWall(Game.sizeX - 1, j);
                if(j != 0) borderPositions.add(new int[]{0, j});
                if(j != 0) borderPositions.add(new int[]{Game.sizeX - 1, j});
                if (Game.freePositions[i][j] == 0) {
                    game.tiles.add(new Tile(i, j));
                }
            }
        }
    }

    public void devide(Rectangle square, boolean wasHorizental){
        Random rand = new Random();
        int width = square.width - square.x;
        int height = square.height - square.y;
        boolean isHorizental = !wasHorizental;
        if((width > 3   && !isHorizental) || (height > 3 && isHorizental)) {
           boolean found = false;
            int wall = 0;
            while(!found){
                if(isHorizental){
                    wall = square.y +  rand.nextInt(square.height - 2);
                    found = (Math.abs(square.x - wall) >= 1 && Math.abs(wall - square.width) >= 1);
                } else {
                    wall = square.x + rand.nextInt(square.width - 2);
                    found = (Math.abs(square.y - wall) >= 1 && Math.abs(wall - square.height) >= 1);
                }
            }
            if(isHorizental){
                int passage = square.x + rand.nextInt(square.width);
                ArrayList<Integer> passageList = new ArrayList<>();
                for(int i = -1; i < 1; i ++){ passageList.add(passage + i);}
                int i = 0;
                while(i < square.width){
                    if(!passageList.contains(square.x + i)){
                        createWall(square.x + i, wall);
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
                        createWall(wall, square.y + i);
                    }
                    i++;
                }
                devide(new Rectangle(square.x, square.y, wall - square.x + 1, square.height), isHorizental);
                devide(new Rectangle(wall + 1, square.y, square.x + square.width - wall - 1, square.height),isHorizental);

            }
        }
    }

    public void initPlayer(){
        int[] randomPosition = getRandomPosition();
        if(game.player != null) {
            game.player.setPositionX(randomPosition[0]);
            game.player.setPositionY(randomPosition[1]);
        } else {
            game.player = new Player(randomPosition[0], randomPosition[1]);
        }
    }

    public void intiMonsters(){
        Random rand = new Random();
        int nbMonsters = rand.nextInt(maxMonster - minMonsters) + minMonsters;
        for(int i = 0 ; i < nbMonsters; i ++ ){
            int[] randomPosition = getRandomPosition();
            game.monsters.add(new Monster(randomPosition[0], randomPosition[1]));
        }
    }

    public void intiItems(){
        int[] keyPosition = getRandomPosition();
        game.items.add(new Key(keyPosition[0], keyPosition[1]));
        Random rand = new Random();
        int nbItems = rand.nextInt(maxItems - minItems) + minItems;
        for(int i = 0 ; i < nbItems; i ++ ){
            int[] randomPosition = getRandomPosition();
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

    public void initDoor(){
        Random rand = new Random();
        boolean found = false;
        int[] position = new int[2];
        while(!found){
            position = borderPositions.get(rand.nextInt(borderPositions.size()));
            if(position[0] == 0){
                found = Game.freePositions[1][position[1]] == 0;
            } else if(position[0] == Game.shownSizeX - 1){
                found = Game.freePositions[Game.shownSizeX - 2][position[1]] == 0;
            }
            if(position[1] == 0){
                found = Game.freePositions[position[0]][1] == 0;
            } else if(position[1] == Game.shownSizeY){
                found = Game.freePositions[position[0]][Game.shownSizeY - 2] == 0;
            }
        }
        game.door = new Door(position[0], position[1]);
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
        return generatedMap;
    }
}
