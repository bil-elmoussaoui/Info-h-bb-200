import java.util.ArrayList;
import javax.swing.JPanel;

class Game {
    //private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    private JPanel window;
    private int size = 50;

    public Game(JPanel window){
        this.window = window;
        for(int i = 0; i < size; i++){
            blocks.add(new Block(i,0));
            blocks.add(new Block(0,i));
            blocks.add(new Block(i, size-1));
            blocks.add(new Block(size-1, i));
        }
        //window.draw();
    }

    public int[][] getMap(){
        int[][] map = new int[this.size][this.size];
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                map[i][j] = 0;
            }
        }
        for(Block block: blocks){
            int x = block.getPositionX();
            int y = block.getPositionY();
            map[x][y] = 1;
        }
        return map;
    }

}
