package Model;

/*
TODO:
- draw image as for other classes!
- implement the salesman!
 */
public class Salesman extends Item {

    public int selectorY = 0;
    public int selectorX = 0;
    public int[][][] carteAchat = new int[5][5][2];


    public Salesman(int positionX, int positionY)
    {
        super(positionX, positionY);
        setIsBreakable(false);
        setIsCollectable(false);
        setIsWalkable(false);

        carteAchat[0][0] = new int[]{5, 2 }; // Potion vie
        carteAchat[0][1] = new int[]{1, 100}; // Bouclier
        carteAchat[0][2] = new int[]{2, 25};; // Potion sorts 1
        carteAchat[0][3] = new int[]{3, 500};; // Armes
        carteAchat[0][4] = new int[]{4, 25};; // Potion sorts 2
    }

    public int getSelectorY(){
        return selectorY;
    }

    public void setSelectorY(int selectorY){
        if(selectorY < 0) {
            this.selectorY =  (4 + selectorY) % 4;
        } else {
            this.selectorY = selectorY % 4;
        }
    }

    public int getSelectorX(){
        return selectorX;
    }

    public void setSelectorX(int selectorX){
        if(selectorX < 0) {
            this.selectorX =  (4 + selectorX) % 4;
        } else {
            this.selectorX = selectorX % 4;
        }
    }

}