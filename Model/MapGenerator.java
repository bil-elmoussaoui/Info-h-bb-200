package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
    // used to get a random door position
    public ArrayList<int[]> borderPositions = new ArrayList<>();
    public ArrayList<int[]> wallsToBreak = new ArrayList<>();
    private int[][] generatedMap;
    private Game game;
    private int maxMonster = 5;
    private int minMonsters = 3;
    private int minItems = 3;
    private int maxItems = 5;
    private int minTraps = 4;
    private int maxTraps = 8;

    public MapGenerator(Game game) {
        this.game = game;
        // generate empty maps
        generatedMap = new int[Game.sizeX][Game.sizeX];
        // create random walls!
        initWalls();
        initPlayer();
        intiMonsters();
        intiItems();
        initTraps();
        initDoor();
        initSalesman();
    }

    public void createWall(int x, int y) {
        if (x >= 0 && x < Game.sizeX && y >= 0 && y < Game.sizeY) {
            if (Game.freePositions[x][y] == 0) {
                game.walls.add(new Wall(x, y));
                generatedMap[x][y] = 1;
                //Game.freePositions[x][y] = 1;
            }
        }
    }

    public void initTraps() {
        Random rand = new Random();
        int nbTraps = rand.nextInt(maxTraps - minTraps) + minTraps;
        for (int i = 0; i < nbTraps; i++) {
            int[] randomPosition = getRandomPosition();
            switch (rand.nextInt(2)) {
                case 0:
                    game.tiles.add(new Tornado(randomPosition[0], randomPosition[1]));
                    break;
                case 1:
                    game.tiles.add(new Spikes(randomPosition[0], randomPosition[1]));
                    break;
            }
        }
    }

    public void initWalls() {
        divide(new Rectangle(1, 1, Game.sizeX - 1, Game.sizeY - 1), false);
        breakUnneededWalls();
        // draw outline walls
        for (int i = 0; i < Game.sizeX; i++) {
            createWall(i, 0);
            createWall(i, Game.sizeY - 1);
            if (i != 0) borderPositions.add(new int[]{i, 0});
            if (i != 0) borderPositions.add(new int[]{i, Game.sizeY - 1});
            for (int j = 0; j < Game.sizeY; j++) {
                createWall(0, j);
                createWall(Game.sizeX - 1, j);
                if (j != 0) borderPositions.add(new int[]{0, j});
                if (j != 0) borderPositions.add(new int[]{Game.sizeX - 1, j});
                if (Game.freePositions[i][j] == 0) {
                    game.tiles.add(new Tile(i, j));
                }
            }
        }
    }

    public void divide(Rectangle square, boolean wasHorizontal) {
        Random rand = new Random();
        int width = square.width - square.x;
        int height = square.height - square.y;
        boolean isHorizontal = !wasHorizontal;
        if ((width >= 3 && !isHorizontal) || (height >= 3 && isHorizontal)) {
            boolean found = false;
            int wall = 0;
            int itter = 0;
            while (!found && itter < 4) {
                if (isHorizontal) {
                    wall = square.y + rand.nextInt(square.height - 2);
                    found = (Math.abs(square.x - wall) >= 1 && Math.abs(wall - square.width) >= 1);
                } else {
                    wall = square.x + rand.nextInt(square.width - 2);
                    found = (Math.abs(square.y - wall) >= 1 && Math.abs(wall - square.height) >= 1);
                }
                itter += 1;
            }
            if (found) {
                if (isHorizontal) {
                    int passage = square.x + rand.nextInt(square.width);
                    ArrayList<Integer> passageList = new ArrayList<>();
                    for (int i = -1; i < 1; i++) {
                        passageList.add(passage + i);
                    }
                    int i = 0;
                    while (i < square.width) {
                        if (!passageList.contains(square.x + i)) {
                            createWall(square.x + i, wall);
                        } else {
                            wallsToBreak.add(new int[]{square.x + i, wall, 0});
                        }
                        i++;
                    }
                    divide(new Rectangle(square.x, square.y, square.width, wall - square.y + 1), isHorizontal);
                    divide(new Rectangle(square.x, wall + 1, square.width, square.y + square.height - wall - 1), isHorizontal);
                } else {
                    int passage = square.y + rand.nextInt(square.height);
                    ArrayList<Integer> passageList = new ArrayList<>();
                    for (int i = -1; i < 1; i++) {
                        passageList.add(passage + i);
                    }
                    int i = 0;
                    while (i < square.height) {
                        if (!passageList.contains(square.y + i)) {
                            createWall(wall, square.y + i);
                        } else {
                            wallsToBreak.add(new int[]{wall, square.y + i, 1});
                        }
                        i++;
                    }
                    divide(new Rectangle(square.x, square.y, wall - square.x + 1, square.height), isHorizontal);
                    divide(new Rectangle(wall + 1, square.y, square.x + square.width - wall - 1, square.height), isHorizontal);
                }
            }
        }
    }

    public void breakUnneededWalls() {
        for (int[] wallPosition : wallsToBreak) {
            breakWall(wallPosition[0], wallPosition[1]);
            for (int k = -1; k <= 1; k++) {
                if (k != 0) {
                    // horizental wall
                    if (wallPosition[2] == 0) {
                        breakWall(wallPosition[0], wallPosition[1] + k);
                    } else {
                        breakWall(wallPosition[0] + k, wallPosition[1]);
                    }
                }
            }
        }
    }

    public void initPlayer() {
        int[] randomPosition = getRandomPosition();
        if (game.player != null) {
            game.player.setPositionX(randomPosition[0]);
            game.player.setPositionY(randomPosition[1]);
        } else {
            game.player = new Player(randomPosition[0], randomPosition[1]);
        }
    }

    public void intiMonsters() {
        Random rand = new Random();
        int nbMonsters = rand.nextInt(maxMonster - minMonsters) + minMonsters;
        for (int i = 0; i < nbMonsters; i++) {
            int[] randomPosition = getRandomPosition();
            game.monsters.add(new Monster(randomPosition[0], randomPosition[1]));
        }
    }

    public void intiItems() {
        int[] keyPosition = getRandomPosition();
        game.items.add(new Key(keyPosition[0], keyPosition[1]));
        Random rand = new Random();
        int nbItems = rand.nextInt(maxItems - minItems) + minItems;
        for (int i = 0; i < nbItems; i++) {
            int[] randomPosition = getRandomPosition();
            switch (rand.nextInt(3)) {
                case 0:
                    game.items.add(new Coin(randomPosition[0], randomPosition[1]));
                    break;
                case 1:
                    game.items.add(new Potion(randomPosition[0], randomPosition[1]));
                    break;
                case 2:
                    game.items.add(new WoodBox(randomPosition[0], randomPosition[1]));
                    break;
                case 3: // Weapons
                    switch (0) { // rand.nextInt(4)
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

    public void initDoor() {
        Random rand = new Random();
        boolean found = false;
        int[] position = new int[2];
        while (!found) {
            position = borderPositions.get(rand.nextInt(borderPositions.size()));
            if (position[0] == 0) {
                found = Game.freePositions[1][position[1]] == 0;
            } else if (position[0] == Game.shownSizeX - 1) {
                found = Game.freePositions[Game.shownSizeX - 2][position[1]] == 0;
            }
            if (position[1] == 0) {
                found = Game.freePositions[position[0]][1] == 0;
            } else if (position[1] == Game.shownSizeY) {
                found = Game.freePositions[position[0]][Game.shownSizeY - 2] == 0;
            }
        }
        game.door = new Door(position[0], position[1]);
    }

    public void initSalesman() {
        int[] randomPosition = getRandomPosition();
        if (game.salesman != null) {
            game.salesman.setPositionX(randomPosition[0]);
            game.salesman.setPositionY(randomPosition[1]);
        } else {
            game.salesman = new Salesman(randomPosition[0], randomPosition[1]);
        }
    }

    public int[] getRandomPosition() {
        boolean foundPosition = false;
        int positionX = 0;
        int positionY = 0;
        Random rand = new Random();
        while (!foundPosition) {
            positionX = rand.nextInt(Game.sizeX);
            positionY = rand.nextInt(game.sizeY);
            if (Game.freePositions[positionX][positionY] == 0 && generatedMap[positionX][positionY] != 1) {
                foundPosition = true;
            }
        }
        return new int[]{positionX, positionY};
    }

    public void breakWall(int positionX, int positionY) {
        if (positionX >= 0 && positionX < Game.sizeX && positionY >= 0 && positionY < Game.sizeY) {
            if (Game.freePositions[positionX][positionY] == 1) {
                for (int i = 0; i < game.walls.size(); i++) {
                    Wall wall = game.walls.get(i);
                    if (positionX == wall.getPositionX() && wall.getPositionY() == positionY) {
                        game.walls.remove(wall);
                        game.tiles.add(new Tile(positionX, positionY));
                        Game.freePositions[positionX][positionY] = 0;
                        break;
                    }
                }
            }
        }
    }

    public int[][] getGeneratedMap() {
        return generatedMap;
    }
}
