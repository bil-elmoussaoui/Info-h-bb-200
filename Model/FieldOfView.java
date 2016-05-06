package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class FieldOfView implements Serializable{
    private int view;
    private int direction; // 1 bas, 2 gauche 3 haut 4 droite
    private int positionX;
    private int positionY;
    private ArrayList<int[]> FOV = new ArrayList<>();

    public FieldOfView(int positionX, int positionY, int direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        Random rand = new Random();
        this.view = rand.nextInt(3) + 1;
        this.createFov();
    }

    public ArrayList<int[]> getFOV() {
        return FOV;
    }

    public void updateFOV(int positionX, int positionY, int direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        FOV.clear();
        createFov();
    }

    public void addToFov(int positionX, int positionY) {
        if (positionX >= 0 && positionX < Game.sizeX && positionY >= 0 && positionY < Game.sizeY) {
            if (Game.freePositions[positionX][positionY] == 0) {
                FOV.add(new int[]{positionX, positionY});
            }
        }
    }

    public void createFov() {
        int t = ((int) Math.floor(view / 2));
        switch (direction) {
            case 4:
            case 2: // 2 gauche, 4 droite
                for (int i = 1; i <= view; i++) {
                    if (direction == 4) {
                        addToFov(positionX + i, positionY);
                    } else {
                        addToFov(positionX - i, positionY);
                    }
                }
                if(direction == 4) addToFov(positionX - 1, positionY);
                if(direction == 2) addToFov(positionX + 1, positionY);
                for (int i = 1; i <= t; i++) {
                    addToFov(positionX, positionY - i);
                    addToFov(positionX, positionY + i);
                }
                break;
            case 3:
            case 1: // 1 bas, 3 haut
                for (int i = 1; i <= view; i++) {
                    if (direction == 3) {
                        addToFov(positionX, positionY - i);
                    } else {
                        addToFov(positionX, positionY + i);
                    }
                }
                if(direction == 3) addToFov(positionX, positionY + 1);
                if(direction == 1) addToFov(positionX, positionY - 1);
                for (int i = 1; i <= t; i++) {
                    addToFov(positionX - i, positionY);
                    addToFov(positionX + i, positionY);
                }
                break;
        }
    }
}