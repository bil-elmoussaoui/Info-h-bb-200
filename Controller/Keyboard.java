package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Game;
import View.MainWindow;

public class Keyboard implements KeyListener{
    private Game game;

    public Keyboard(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        int nextPositionX = game.player.getPositionX();
        int nextPositionY = game.player.getPositionY();
        switch(key){
            case KeyEvent.VK_RIGHT:
                nextPositionX += 1;
            break;
            case KeyEvent.VK_LEFT:
                nextPositionX -= 1;
            break;
            case KeyEvent.VK_UP:
                nextPositionY -= 1;
            break;
            case KeyEvent.VK_DOWN:
                nextPositionY += 1;
            break;
        }
        if(nextPositionX != game.player.getPositionX() || nextPositionY != game.player.getPositionY()) {
            if(!MainWindow.gamePaused) {
                game.player.move(nextPositionX, nextPositionY);
                game.refreshMap();
            }
        } else {
            switch (key){
                case KeyEvent.VK_SPACE:
                    if(!MainWindow.gamePaused) {
                        game.player.attack();
                        game.refreshMap();
                    }
                break;
                case KeyEvent.VK_E:
                    if(!MainWindow.gamePaused) {
                        game.player.collect();
                        game.refreshMap();
                    }
                break;

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
