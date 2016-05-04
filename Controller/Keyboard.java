package Controller;

import Model.Game;
import View.MainWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    private Game game;
    private int nextPositionX;
    private int nextPositionY;
    private int selectorY = 0;
    private int selectorX = 0;

    private boolean speedMoves = false;
    public Keyboard(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if(Game.playerIsShopping){
            switch(key){

                case KeyEvent.VK_UP: case KeyEvent.VK_Z:
                    selectorY = -1;
                    break;
                case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                    selectorY = 1;
                    break;
                case KeyEvent.VK_LEFT: case KeyEvent.VK_Q:
                    selectorX = -1;
                    break;
                case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                    selectorX = 1;
                    break;
                case KeyEvent.VK_ENTER:
                    game.playerBuy();
                    break;
                case KeyEvent.VK_ESCAPE:
                    if(!MainWindow.gamePaused) {
                        MainWindow.gamePaused = true;
                        game.window.showMenuWindow();
                    } else {
                        MainWindow.gamePaused = false;
                        game.window.showLevelWindow();
                    }
                    break;
            }
        } else {
            nextPositionX = game.player.getPositionX();
            nextPositionY = game.player.getPositionY();
            switch (key) {
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    nextPositionX += 1;
                    game.player.isMoving = true;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_Q:
                    nextPositionX -= 1;
                    game.player.isMoving = true;
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_Z:
                    nextPositionY -= 1;
                    game.player.isMoving = true;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    nextPositionY += 1;
                    game.player.isMoving = true;
                    break;
                case KeyEvent.VK_O:
                    game.openDoor();
                    break;
                case KeyEvent.VK_1:
                case 150:
                    game.player.setWeaponByIndex(1);
                    break;
                case KeyEvent.VK_2:
                case 0:
                    game.player.setWeaponByIndex(2);
                    break;
                case KeyEvent.VK_3:
                case 152:
                    game.player.setWeaponByIndex(3);
                    break;
                case KeyEvent.VK_4:
                case 222:
                    game.player.setWeaponByIndex(4);
                    break;
                case KeyEvent.VK_5:
                case 519:
                    game.player.setWeaponByIndex(5);
                    break;
                case KeyEvent.VK_Y:
                    game.player.usePotion(1);
                    break;
                case KeyEvent.VK_U:
                    game.player.usePotion(2);
                    break;
                case KeyEvent.VK_I:
                    game.player.usePotion(3);
                    break;
                case KeyEvent.VK_SPACE:
                    if (!MainWindow.gamePaused) {
                        game.playerAttack();
                        game.player.setAttackMode(true);
                    }
                    break;
                case KeyEvent.VK_E:
                    if (!MainWindow.gamePaused) {
                        game.playerCollect();
                    }
                break;
                case KeyEvent.VK_B:
                    if (!MainWindow.gamePaused) {
                        MainWindow.gamePaused = true;
                        Game.playerIsShopping = true;
                        game.drawShop();
                    }
                break;
                case KeyEvent.VK_F:
                    game.playerThrowWeapon();
                    break;
                case KeyEvent.VK_SHIFT:
                    speedMoves = true;
                    break;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if(!Game.playerIsShopping) {
            if (nextPositionX != game.player.getPositionX() || nextPositionY != game.player.getPositionY()) {
                if (!MainWindow.gamePaused) {
                    game.player.setAttackMode(false);
                    game.player.move(nextPositionX, nextPositionY);
                    game.refreshMap();
                    game.player.isMoving = false;
                }
            }
            game.player.counter.init();
        } else {
            if(selectorY + game.salesman.getSelectorY() != game.salesman.getSelectorY()) {
                game.salesman.setSelectorY(game.salesman.getSelectorY() + selectorY);
            }
            if (selectorX + game.salesman.getSelectorX() != game.salesman.getSelectorX()) {
                game.salesman.setSelectorX(game.salesman.getSelectorX() + selectorX);
            }
            selectorX = 0;
            selectorY = 0;
            game.drawShop();
        }
    }
}
