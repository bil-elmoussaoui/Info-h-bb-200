package Controller;

import Model.*;
import View.MainWindow;

import java.util.ArrayList;

public class Animation {
    private int monsterMovesTime = 1500;
    private int animationRefresh = 100;
    private Game game;
    private Thread monstersThread;
    private Thread animationThread;
    private Thread playerAttackThread;


    public Animation(Game game){
        this.game = game;
        this.monstersThread = new Thread(){
            public void run(){
                try {
                    while (true) {
                        Thread.sleep(monsterMovesTime);
                        if (game.getMonsters().size() > 0) {
                            for (Monster monster : game.monsters) {
                                if (!MainWindow.gamePaused && monster.getCanMove()) {
                                    int[] playerPosition = new int[]{game.player.getPositionX(), game.player.getPositionY()};
                                    if (monster.FOV.getFOV().contains(playerPosition)) {
                                        monster.isMoving = true;
                                        if (playerPosition[0] == monster.getPositionX()) {
                                            if ((playerPosition[0] - monster.getPositionX()) > 0) {
                                                monster.setPositionX(monster.getPositionX() + 1);
                                            } else {
                                                monster.setPositionX(monster.getPositionX() - 1);
                                            }
                                        } else {
                                            if ((playerPosition[1] - monster.getPositionY()) > 0) {
                                                monster.setPositionY(monster.getPositionY() + 1);
                                            } else {
                                                monster.setPositionY(monster.getPositionY() - 1);
                                            }
                                        }
                                    }
                                    if(!monster.isMoving) {
                                        int[] position = monster.getRandomPosition();
                                        if (position != null) {
                                            monster.isMoving = true;
                                            monster.move(position[0], position[1]);
                                            monster.FOV.updateFOV(position[0], position[1], monster.direction);
                                        }
                                    }
                                    monster.isMoving = false;
                                }
                            }
                        }
                    }
                }catch(Exception e){}
            }
        };
        this.monstersThread.start();

        Thread trapThread = new Thread(){
            public void run(){
                try{
                    while (true) {
                        Thread.sleep(500);
                        if (game.getTiles().size() > 0) {
                            for (Tile tile : game.tiles) {
                                if (tile instanceof Trap) {
                                    game.trapDamage(tile);
                                }
                            }
                        }
                    }
                }catch (Exception e){

                }
            }
        };
        trapThread.start();

       playerAttackThread = new Thread(){
            public void run(){
                try {
                    while(true) {
                        Thread.sleep(animationRefresh);
                        if(game.player.isAttacking && !game.player.isMoving) {
                            game.player.setCanMove(false);
                            while (game.player.weapon.counter.getCounter() < game.player.weapon.counter.getCounterMax()) {
                                game.player.weapon.counter.up();
                                if(game.player.weapon instanceof Bow){
                                    ArrayList<Arrow> arrows = ((Bow)game.player.weapon).arrows;
                                    for(int i = 0; i < arrows.size(); i++){
                                        arrows.get(i).counter.up();
                                    }
                                }
                                game.player.counter.up();
                                game.refreshMap();
                                Thread.sleep(50);
                            }
                            game.player.setCanMove(true);
                            game.player.setAttackMode(false);
                            game.player.weapon.counter.init();
                            game.player.counter.init();
                            game.refreshMap();
                        }
                    }

                }catch (Exception e){}
            }
        };
        playerAttackThread.start();

        Thread trapAnimationThread = new Thread(){
            public void run(){
                try {
                    while(true) {
                        Thread.sleep(animationRefresh);
                        if (game.getTiles().size() > 0) {
                            for (Tile tile : game.tiles) {
                                if( tile instanceof Trap) {
                                    Trap trap = (Trap)tile;
                                    if(trap.counter.getCounter() == trap.counter.getCounterMax()) {
                                        int[] position = game.mapGenerator.getRandomPosition();
                                        trap.setPositionX(position[0]);
                                        trap.setPositionY(position[1]);
                                        trap.animationStopped = true;
                                        Thread.sleep(4000);
                                    }
                                    trap.animationStopped = false;
                                    ((Trap) tile).counter.up();
                                }
                            }
                        }
                    }
                }catch (Exception e){}
            }
        };
        trapAnimationThread.start();

        animationThread = new Thread(){
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(animationRefresh);
                        if(game.player.isMoving) {
                            game.player.counter.up();
                        }

                        if (game.getMonsters().size() > 0) {
                            for (Monster monster : game.monsters) {
                                if(monster.isMoving) {
                                    monster.counter.up();
                                }
                            }
                        }

                        if (game.getItems().size() > 0) {
                            for (Item item : game.items) {
                                if (item instanceof Coin) {
                                    ((Coin) item).counter.up();
                                }
                            }
                        }
                        game.refreshMap();
                    }
                }catch(Exception e){}
            }
        };
        animationThread.start();
    }

}
