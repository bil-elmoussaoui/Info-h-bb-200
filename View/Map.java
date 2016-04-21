package View;

import Model.*;
import javax.swing.*;
import java.awt.*;

public class Map extends JPanel{
    private int[][] mapMatrix;

    public Map(){
        this.requestFocusInWindow();
        this.setFocusable(true);
        this.requestFocus();
    }

    public void paint(Graphics g) {
        for(int i = 0; i< mapMatrix.length; i++){
            for(int j = 0; j< mapMatrix[i].length; j++){
                Tile.draw(g, i ,j);
                switch(mapMatrix[i][j]) {
                    case 1: Wall.draw(g, i, j); break;
                    case 2: Player.draw(g, i, j); break;
                    case 3: WoodBox.draw(g, i ,j); break;
                    //case 4: g.drawImage(Door.draw(), i * 24, j * 24, 24, 24, null); break;
                    case 5: Monster.draw(g, i, j); break;
                    case 6: Coin.draw(g, i, j); break;
                    case 7: Heart.draw(g, i ,j); break;
                    case 8: Key.draw(g, i, j); break;
                    //case 10: g.drawImage(Salesman.draw(), i*24, j*24, 24, 24, null); break;
                }
            }
        }
    }

    public void setMapMatrix(int[][] mapMatrix){
        this.mapMatrix = mapMatrix;
        this.repaint();
    }
}
