package Model;

public class Item {
    private int positionX;
    private int positionY;
    private boolean isBreakable = false;
    private boolean isCollectable = false;
    private boolean isWalkable = true;
    private boolean isUsed = false;


    public Item(Integer positionX, Integer positionY){
        if(positionX == null || positionY == null){
            this.isUsed = true;
        } else {
            this.setPositionX(positionX);
            this.setPositionY(positionY);
        }
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }

    public boolean getIsUsed(){
        return this.isUsed;
    }

    public void setIsWalkable(boolean isWalkable){
        this.isWalkable = isWalkable;
        Game.freePositions[this.getPositionX()][this.getPositionY()] = isWalkable ? 0 : 1;
    }

    public boolean getIsWalkable(){
        return this.isWalkable;
    }

    public void setIsBreakable(boolean isBreakable){
        this.isBreakable = isBreakable;
    }

    public void setIsCollectable(boolean isCollectable){
        this.isCollectable = isCollectable;
    }

    public boolean getIsBreakable(){
        return this.isBreakable;
    }

    public boolean getIsCollectable(){
        return this.isCollectable;
    }

    public int getPositionX(){
        return this.positionX;
    }

    public int getPositionY(){
        return  this.positionY;
    }

    public void setPositionX(int positionX){
        try{
            if(positionX < 0){
                throw new Exception("Position X can't be less than 0");
            } else {
                this.positionX = positionX;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPositionY(int positionY){
        try{
            if(positionY < 0){
                throw new Exception("Position Y can't be less than 0");
            } else {
                this.positionY = positionY;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
