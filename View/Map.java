
import javax.swing.*;
import java.awt.*;

class Map extends JPanel{
    private int[][] mapMatrix;

    public Map(){
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void drawMap(Graphics g) {
        for(int i = 0; i< mapMatrix.length; i++){
            for(int j = 0; j< mapMatrix[i].length; j++){
                int color = mapMatrix[i][j];
                if(color == 0){
                    g.setColor(Color.GRAY);
                }else if(color == 1){
                    g.setColor(Color.DARK_GRAY);
                }else if(color == 2){
                    g.setColor(Color.RED);
                }

                g.fillRect(i*50, j*50, 48, 48);
                g.setColor(Color.BLACK);
                g.drawRect(i*50, j*50, 48, 48);
             }
        }
    }

    public void setMapMatrix(int[][] mapMatrix){
        this.mapMatrix = mapMatrix;
        this.repaint();
    }
}
