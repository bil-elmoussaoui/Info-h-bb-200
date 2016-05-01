package Model;

import java.util.ArrayList;
import java.util.Random;

/*
    TODO:
    - implement two kind of field of views (line - square)
 */

public class FieldOfView {
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
        this.view = rand.nextInt(2) + 1;
        this.createFov();
    }

    public ArrayList<int[]> getFOV(){
        return FOV;
    }

    public void updateFOV(int positionX, int positionY, int direction){
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        FOV.clear();
        createFov();
    }

    public void addToFov(int positionX, int positionY){
        if(positionX >= 0 && positionX < Game.sizeX && positionY >= 0 && positionY < Game.sizeY) {
            if (Game.freePositions[positionX][positionY] == 0) {
                FOV.add(new int[]{positionX, positionY});
            }
        }
    }

    public void createFov() {
        int t;
        switch (direction) {
            case (4):// 1 bas, 2 gauche 3 haut 4 droite
                for (int i = 1; i <= view; i++) {
                    addToFov(positionX + i, positionY);
                }
                t = ((int) Math.floor(view / 2));
                for (int i = 1; i <= t; i++) {
                    addToFov(positionX, positionY - i);
                    addToFov(positionX, positionY + i);
                }
            break;
            case (2):
                for (int i = 1; i <= view; i++) {
                    addToFov(positionX - i, positionY);
                }
                t = ((int) Math.floor((view) / 2));
                for (int i = 1; i <= t; i++) {
                    addToFov(positionX, positionY - i);
                    addToFov(positionX, positionY + i);
                }
            break;
            case (3):
                for (int i = 1; i <= view; i++) {
                    addToFov(positionX, positionY - i);
                }
                t = ((int) Math.floor((view) / 2));
                for (int i = 1; i <= t; i++) {
                    addToFov(positionX - i, positionY);
                    addToFov(positionX + i, positionY);
                }
            break;
            case (1):
                for (int i = 1; i <= view; i++) {
                    addToFov(positionX, positionY + i);
                }
                t = ((int) Math.floor((view) / 2));
                for (int i = 1; i <= t; i++) {
                    addToFov(positionX - i, positionY);
                    addToFov(positionX + i, positionY);
                }
            break;
        }
    }
}