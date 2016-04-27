package Controller;

import Model.Game;
import View.MainWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    private Game game;
    private int nextPositionX;
    private int nextPositionY;

    public Keyboard(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        nextPositionX = game.player.getPositionX();
        nextPositionY = game.player.getPositionY();
        switch(key){
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                nextPositionX += 1;
                game.player.isMoving = true;
            break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_Q:
                nextPositionX -= 1;
                game.player.isMoving = true;
            break;
            case KeyEvent.VK_UP: case KeyEvent.VK_Z:
                nextPositionY -= 1;
                game.player.isMoving = true;
            break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                nextPositionY += 1;
                game.player.isMoving = true;
            break;
            case KeyEvent.VK_SPACE:
                if(!MainWindow.gamePaused) {
                    game.player.setAttackMode(true);
                    game.playerAttack();
                }
            break;
            case KeyEvent.VK_E:
                if(!MainWindow.gamePaused) {
                    game.playerCollect();
                }
            break;
            case KeyEvent.VK_F:
                game.player.throwWeapon(game.player.weapon);
            break;
        }
        if(game.player.isMoving){
            if(nextPositionX != game.player.getPositionX() || nextPositionY != game.player.getPositionY()) {
                if(!MainWindow.gamePaused) {
                    game.player.setAttackMode(false);
                    game.player.move(nextPositionX, nextPositionY);
                    game.refreshMap();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        game.player.isMoving = false;
        game.player.counter.init();
    }
}
