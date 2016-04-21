package View;
import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map extends JPanel{
    private int[][] mapMatrix;

    public Map(){
        this.requestFocusInWindow();
        this.setFocusable(true);
        this.requestFocus();
    }

    public void paint(Graphics g) {
        BufferedImage stoneBackground = null;
        try {
            stoneBackground = ImageIO.read(new File("Images/StoneWallBackground.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        for(int i = 0; i< mapMatrix.length; i++){
            for(int j = 0; j< mapMatrix[i].length; j++){
                int type = mapMatrix[i][j];
                Tile.draw(g, i ,j);
                switch(type) {
                    case 1: Wall.draw(g, i, j); break;
                    case 2: Player.draw(g, i, j); break;
                    case 3: WoodBox.draw(g, i ,j); break;
                    case 4: g.drawImage(Door.draw(), i * 24, j * 24, 24, 24, null); break;
                    case 5: Monster.draw(g, i, j); break;
                    case 6: Coin.draw(g, i, j); break;
                    case 7: Heart.draw(g, i ,j); break;
                    case 8: Key.draw(g, i, j); break;
                    case 10: g.drawImage(Salesman.draw(), i*24, j*24, 24, 24, null); break;
                }
            }
        }
    }

    public void setMapMatrix(int[][] mapMatrix){
        this.mapMatrix = mapMatrix;
        this.repaint();
    }
}
