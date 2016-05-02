package Controller;

import Model.*;
import View.MainWindow;

import java.util.ArrayList;

public class Animation {
    private int monsterMovesTime = 1200;
    private int animationRefresh = 100;
    private Game game;

    public Animation(Game game){
        this.game = game;
        Thread monstersThread = new Thread(){
            public void run(){
                try {
                    while (true) {
                        Thread.sleep(monsterMovesTime);
                        for(int i = 0; i < game.monsters.size(); i++){
                            Monster monster = game.monsters.get(i);
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
                }catch(Exception e){}
            }
        };
        monstersThread.start();

        Thread trapThread = new Thread(){
            public void run(){
                try{
                    while (true) {
                        Thread.sleep(500);
                        for(int i = 0; i < game.getTiles().size(); i++){
                            Tile tile = game.getTiles().get(i);
                                if (tile instanceof Trap) {
                                    game.trapDamage(tile);
                                }
                        }
                    }
                }catch (Exception e){

                }
            }
        };
        trapThread.start();

        Thread playerAttackThread = new Thread(){
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
                                        Arrow arrow = arrows.get(i);
                                        if(!arrow.beenThrown) {
                                            if (arrow.counter.isMax()) {
                                                ((Bow) game.player.weapon).arrows.get(i).beenThrown = true;
                                            } else {
                                                ((Bow) game.player.weapon).arrows.get(i).counter.up();
                                            }
                                        }
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
                                    if(trap.counter.isMax()) {
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

        Thread animationThread = new Thread(){
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(animationRefresh);
                        if(game.player.isMoving) {
                            game.player.counter.up();
                        }

                        for(int i = 0; i < game.getMonsters().size(); i++){
                            Monster monster = game.getMonsters().get(i);
                            if(monster.isMoving) {
                                monster.counter.up();
                            }
                        }

                        for(int i = 0; i < game.getItems().size(); i++) {
                            Item item = game.getItems().get(i);
                            if (item instanceof Coin) {
                                ((Coin) item).counter.up();
                            } else if (item instanceof WoodBox){
                                if(((WoodBox) item).getIsBeingBroken()) {
                                    if (((WoodBox) item).counter.isMax()) {
                                        int x = item.getPositionX();
                                        int y = item.getPositionY();
                                        if (((WoodBox) item).content != null) {
                                            game.items.add(((WoodBox) item).content);
                                        }
                                        game.items.remove(item);
                                        Game.freePositions[x][y] = 0;
                                    } else {
                                        ((WoodBox) item).counter.up();
                                    }
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
