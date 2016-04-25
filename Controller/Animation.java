package Controller;
import Model.Game;
import View.MainWindow;
import Model.Monster;
import Model.Item;
import Model.Coin;

public class Animation implements Runnable {
    private int counter = 0;
    private int monsterMovesTime = 250;
    private int animationRefresh = 250;
    private Game game;
    private Thread thread;


    public Animation(Game game){
        this.game = game;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){
        try{
            while(true) {
                Thread.sleep(this.monsterMovesTime);
                if(game.getMonsters().size() > 0) {
                    for (Monster monster : game.monsters) {
                        int[] position = monster.getRandomPosition();
                        if (position != null && !MainWindow.gamePaused) {
                            if(monster.getCanMove()) {
                                monster.move(position[0], position[1]);
                            }
                        }
                    }
                }
                if(game.getItems().size() > 0) {
                    for (Item item : game.items) {
                        if(item instanceof Coin){
                            ((Coin) item).upCounter();
                        }
                    }
                }
                game.refreshMap();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
