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
                switch(type) {
                    case 0: // floor
                        g.drawImage(stoneBackground, i * 16, j * 16, 16, 16, null);
                        break;
                    case 1: // wall
                        g.drawImage(Wall.draw(), i * 16, j * 16, 16, 16, null);
                        break;
                    case 2: // player
                        g.drawImage(stoneBackground, i * 16, j * 16, 16, 16, null);
                        g.drawImage(Player.draw(), i * 16, j * 16, 16, 16, null);
                        break;
                    case 3: //Tile
                        g.drawImage(WoodBox.draw(), i * 16, j * 16, 16, 16, null);
                    break;
                    case 4: //Door
                        g.drawImage(stoneBackground, i * 16, j * 16, 16, 16, null);
                        g.drawImage(Door.draw(), i * 16, j * 16, 16, 16, null);
                    break;
                    case 5: // Monster
                        g.drawImage(stoneBackground, i*16, j*16, 16, 16, null);
                        g.drawImage(Monster.draw(), i*16, j*16, 16, 16, null);
                    break;
                    case 6:
                        g.drawImage(stoneBackground, i*16, j*16, 16, 16, null);
                        g.drawImage(Salesman.draw(), i*16, j*16, 16, 16, null);
                    break;
                }
            }
        }
    }

    public void setMapMatrix(int[][] mapMatrix){
        this.mapMatrix = mapMatrix;
        this.repaint();
    }
}
