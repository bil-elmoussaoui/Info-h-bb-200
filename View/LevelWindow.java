
import javax.swing.*;
import java.awt.*;


class LevelWindow{
    private Map levelMap = new Map();

    public void draw(int[][] mapMatrix){
        levelMap.setMapMatrix(mapMatrix);
    }


    public Component getJPanel(){
        JPanel LevelWindow = new JPanel();
        //levelMap = new Map(this.getLevel());
        //JPanel level = levelMap.getMap();
        //LevelWindow.add(level);
        //levelMap.showMap();
        return LevelWindow;
    }


}
