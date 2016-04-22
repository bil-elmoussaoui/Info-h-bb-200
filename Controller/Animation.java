package Controller;
import Model.Game;

public class Animation implements Runnable {
    private int counter = 0;
    private int limitCounter;
    private Game game;

    public Animation(Game game){
        this.game = game;
    }

    @Override
    public void run(){
        while(true){
            counter += 1;
            if(counter > limitCounter){
                counter = 0;
            }
            this.game.refreshMap();
        }
    }


}
