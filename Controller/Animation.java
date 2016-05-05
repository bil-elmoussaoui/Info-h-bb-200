package Controller;

import Model.*;
import View.MainWindow;

import java.util.ArrayList;

public class Animation implements IObservateur {
    private int monsterMovesTime = 500;
    private int animationRefresh = 100;
    private int monsterAttackTime = 500;
    private ArrayList<Thread> threadList = new ArrayList<>();
    private Game game;

    public Animation(Game game){
        this.game = game;

        Thread monstersThread = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(monsterMovesTime);
                        for (int i = 0; i < game.monsters.size(); i++) {
                            Monster monster = game.monsters.get(i);
                            if (!MainWindow.gamePaused && monster.getCanMove()) {
                                int[] playerPosition = new int[]{game.player.getPositionX(), game.player.getPositionY()};
                                if (monster.getCanMove()) {
                                    for (int k = 0; k < monster.FOV.getFOV().size(); k++) {
                                        if (monster.FOV.getFOV().get(k)[0] == playerPosition[0] && monster.FOV.getFOV().get(k)[1] == playerPosition[1]) {
                                            monster.isMoving = true;
                                            if (playerPosition[0] == monster.getPositionX()) {
                                                if ((playerPosition[1] - monster.getPositionY()) > 0) {
                                                    monster.move(monster.getPositionX(), monster.getPositionY() + 1);
                                                    System.out.println("fuck");
                                                } else {
                                                    monster.move(monster.getPositionX(), monster.getPositionY() - 1);
                                                    System.out.println("you");

                                                }
                                            } else if (playerPosition[1] == monster.getPositionY()) {
                                                if ((playerPosition[0] - monster.getPositionX()) > 0) {
                                                    monster.move(monster.getPositionX() + 1, monster.getPositionY());
                                                    System.out.println("bastard");

                                                } else {
                                                    monster.move(monster.getPositionX() - 1, monster.getPositionY());
                                                    System.out.println("dammit");

                                                }
                                            }
                                        }
                                    }
                                    if (!monster.isMoving) {
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
                } catch (Exception e) {
                }
            }

        };
        threadList.add(monstersThread);

        Thread trapThread = new Thread(){
            public void run(){
                try{
                    while (true) {
                        Thread.sleep(500);
                        for(int i = 0; i < game.getTiles().size(); i++){
                            Tile tile = game.getTiles().get(i);
                                if (tile instanceof Tornado || tile instanceof Spikes) {
                                    game.trapDamage(tile);
                                }
                        }
                    }
                }catch (Exception e){

                }
            }
        };
        threadList.add(trapThread);

        Thread arrow = new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(animationRefresh);
                        for(int i = 0; i < game.thrownWeapons.size(); i++){
                            Weapon weapon = game.thrownWeapons.get(i);
                            if(weapon instanceof Arrow){
                                Arrow arrow = (Arrow)weapon;
                                int positionX = arrow.getPositionX();
                                int positionY = arrow.getPositionY();
                                switch (arrow.getDirection()){
                                    // 1 bas, 2 gauche 3 haut 4 droite
                                    case 1:
                                        positionY -= 1;
                                    break;
                                    case 2:
                                        positionX -= 1;
                                    break;
                                    case 3:
                                        positionY += 1;
                                    break;
                                    case 4:
                                        positionX += 1;
                                    break;
                                }
                                if(Game.freePositions[positionX][positionY] == 1){
                                    Monster monster = game.getMonster(positionX, positionY);
                                    Item item = game.getItem(positionX, positionY);
                                    Wall wall = game.getWall(positionX, positionY);
                                    if(monster != null){
                                        arrow.attack(monster);
                                        game.thrownWeapons.remove(arrow);
                                        if (!monster.isAlive()) {
                                            game.monsters.remove(monster);
                                            Game.freePositions[positionX][positionY] = 0;
                                        }
                                    } else if(item != null){
                                        if(item.getIsBreakable()){
                                            ((WoodBox)item).setIsBeingBroken(true);
                                        }
                                        game.thrownWeapons.remove(arrow);
                                    } else if(wall != null){
                                        game.thrownWeapons.remove(arrow);
                                    }
                                } else {
                                    arrow.setPositionX(positionX);
                                    arrow.setPositionY(positionY);
                                }
                                game.refreshMap();
                            }
                        }
                    }catch (Exception e){}
                }
            }
        };
        threadList.add(arrow);

        Thread spell = new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(100);
                        for(int i = 0; i < game.thrownSpells.size(); i++){
                            Spell spell = game.thrownSpells.get(i);
                            int positionX = spell.getPositionX();
                            int positionY = spell.getPositionY();
                            if(spell.getIsMovingSpell()) {
                                switch (spell.getDirection()) {
                                    case 1: positionY -= 1; break;
                                    case 2: positionX -= 1; break;
                                    case 3: positionY += 1; break;
                                    case 4: positionX += 1; break;
                                }
                            }
                            if(Game.freePositions[positionX][positionY] == 1){
                                Monster monster = game.getMonster(positionX, positionY);
                                Item item = game.getItem(positionX, positionY);
                                Wall wall = game.getWall(positionX, positionY);
                                if(monster != null){
                                    if(spell instanceof FireLion){
                                        if(spell.getIsMovingSpell()) {
                                            spell.setPositionX(monster.getPositionX());
                                            spell.setPositionY(monster.getPositionY());
                                        }
                                        monster.setCanMove(false);
                                    } else {
                                        monster.setCanMove(false);
                                        monster.setCanAttack(false);
                                    }
                                    spell.attack(monster);
                                    Thread.sleep(1000);
                                    game.thrownSpells.remove(spell);
                                    if (!monster.isAlive()) {
                                        game.monsters.remove(monster);
                                        Game.freePositions[positionX][positionY] = 0;
                                    }
                                } else if(item != null){
                                    if(item.getIsBreakable()){
                                        ((WoodBox)item).setIsBeingBroken(true);
                                    }
                                    game.thrownSpells.remove(spell);
                                } else if(wall != null){
                                    game.thrownSpells.remove(spell);
                                }
                            } else {
                                if(spell.getIsMovingSpell()) {
                                    spell.setPositionX(positionX);
                                    spell.setPositionY(positionY);
                                }
                            }
                            game.refreshMap();
                        }
                    } catch (Exception e){}
                }
            }
        };
        threadList.add(spell);

        Thread monsterAttackThread = new Thread(){
            public void run(){
                try {
                    while(true) {
                        Thread.sleep(monsterAttackTime);
                        for (int i = 0; i < game.monsters.size(); i++) {
                            Monster monster = game.monsters.get(i);
                            int[] playerPosition = new int[]{game.player.getPositionX(), game.player.getPositionY()};
                            int[] monsterAttackPosition = monster.getAttackedPosition();
                            if (playerPosition[0] == monsterAttackPosition[0] && playerPosition[1] == monsterAttackPosition[1]) {
                                if(monster.getCanAttack()) {
                                    monster.attack(game.player);
                                    monster.isAttacking = true;
                                }
                            }
                            if (monster.isAttacking && !monster.isMoving) {
                                monster.setCanMove(false);
                                while (monster.weapon.counter.getCounter() < monster.weapon.counter.getCounterMax()) {
                                    monster.weapon.counter.up();
                                    if(game.player.weapon instanceof Bow){
                                        Arrow arrow = ((Bow)game.player.weapon).arrow;
                                        if(!arrow.beenThrown) {
                                            ((Bow) game.player.weapon).arrow.counter.up();
                                            if(arrow.counter.getCounterMax() == arrow.counter.getCounter()){
                                                arrow.beenThrown = true;
                                                arrow.counter.init();
                                                arrow.setPositionX(game.player.getPositionX());
                                                arrow.setPositionY(game.player.getPositionY());
                                                game.thrownWeapons.add(arrow);
                                                ((Bow)game.player.weapon).arrow = null;
                                            }
                                        }
                                    }
                                    monster.counter.up();
                                    game.refreshMap();
                                    Thread.sleep(50);
                                }
                                monster.setCanMove(true);
                                monster.setAttackMode(false);
                                monster.weapon.counter.init();
                                monster.counter.init();
                                game.refreshMap();
                            }
                        }
                    }

                }catch (Exception e){}
            }
        };
        threadList.add(monsterAttackThread);

        Thread playerAttackThread = new Thread(){
            public void run(){
                try {
                    while(true) {
                        Thread.sleep(animationRefresh);
                        if(game.player.isAttacking && !game.player.isMoving) {
                            game.player.setCanMove(false);
                            while (game.player.weapon.counter.getCounter() < game.player.weapon.counter.getCounterMax()) {

                                if(game.player.weapon instanceof Bow){
                                    Arrow arrow = ((Bow)game.player.weapon).arrow;
                                    if(!arrow.beenThrown) {
                                        game.player.weapon.counter.up();
                                        game.player.counter.up();
                                        ((Bow) game.player.weapon).arrow.counter.up();
                                        if(arrow.counter.isMax()){
                                            arrow.beenThrown = true;
                                            arrow.counter.init();
                                            arrow.setPositionX(game.player.getPositionX());
                                            arrow.setPositionY(game.player.getPositionY());
                                            game.thrownWeapons.add(arrow);
                                            ((Bow)game.player.weapon).arrow = null;
                                        }
                                    }
                                } else {
                                    game.player.weapon.counter.up();
                                    game.player.counter.up();
                                }
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
    threadList.add(playerAttackThread);


    Thread trapTornadoAnimation = new Thread(){
            public void run(){
                try {
                    while(true) {
                        Thread.sleep(animationRefresh);
                        if (game.getTiles().size() > 0) {
                            for (Tile tile : game.tiles) {
                                if(tile instanceof Trap) {
                                    Trap trap = (Trap)tile;
                                    if(trap instanceof Tornado) {
                                        if (((Tornado)trap).counter.isMax()) {
                                            ((Tornado)trap).animationStopped = true;
                                            ((Tornado)trap).counter.init();
                                            int[] position = game.mapGenerator.getRandomPosition();
                                            trap.setPositionX(position[0]);
                                            trap.setPositionY(position[1]);
                                            Thread.sleep(2000);
                                        } else {
                                            ((Tornado) trap).animationStopped = false;
                                            ((Tornado) trap).counter.up();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }catch (Exception e){}
            }
        };
        threadList.add(trapTornadoAnimation);

        Thread spellAnimation = new Thread(){
            public void run(){
                try {
                    while(true) {
                        Thread.sleep(animationRefresh);
                        for(int i = 0; i < game.thrownSpells.size(); i++){
                            Spell spell = game.thrownSpells.get(i);
                            if(spell.counter.isMax()){
                                game.thrownSpells.remove(spell);
                            } else {
                                spell.counter.up();
                            }
                        }
                    }

                }catch (Exception e){}
            }
        };
        threadList.add(spellAnimation);

        Thread animationThread = new Thread(){
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(animationRefresh);
                        if(game.player.isSpelling){
                            if(game.player.counter.isMax()){
                                game.thrownSpells.add(game.player.spell);
                                game.player.spell = null;
                                game.player.stopSpelling();
                            } else {
                                game.player.counter.up();
                            }
                        }
                        if(game.player.isMoving) {
                            game.player.counter.up();
                        }

                        for(int i = 0; i < game.getMonsters().size(); i++){
                            Monster monster = game.getMonsters().get(i);
                            if(monster.isMoving) {
                                monster.counter.up();
                            }
                        }
                        for(int i = 0; i < game.getTiles().size(); i++){
                            Tile tile = game.getTiles().get(i);
                            if(tile instanceof Spikes) {
                                ((Spikes)tile).counter.up();
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
        threadList.add(animationThread);
    }

    public void update(){
        if(MainWindow.gamePaused){
            for(Thread thread : threadList){
                thread.interrupt();
            }
        } else {
             for(Thread thread : threadList){
                thread.start();
            }
        }
    }

}
