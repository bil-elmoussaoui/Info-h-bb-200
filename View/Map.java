package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map extends JPanel{
    private int[][] map;
    private Game game;

    public Map(){
        this.requestFocusInWindow();
        this.setFocusable(true);
        this.requestFocus();
    }

    public void setGame(Game game){
        this.game = game;
    }

    public void paint(Graphics g) {
        int startPositionX = game.player.getPositionX() - (int)Math.floor(Game.shownSizeX/2) - 1;
        int endPositionX = game.player.getPositionX() + (int)Math.floor(Game.shownSizeX/2);
        int startPositionY = game.player.getPositionY() - (int)Math.floor(Game.shownSizeY/2);
        int endPositionY = game.player.getPositionY() + (int)Math.floor(Game.shownSizeY/2);
        if(endPositionX > Game.sizeX || endPositionX - startPositionX < Game.shownSizeX){
            startPositionX = Game.sizeX - Game.shownSizeX;
            endPositionX = Game.sizeX;
        }
        if(startPositionX < 0){
            startPositionX = 0;
            endPositionX = Game.sizeX;
        }
        if(endPositionY > Game.sizeY  || endPositionY - startPositionY < Game.shownSizeY) {
            startPositionY = Game.sizeY - Game.shownSizeY;
            endPositionY = Game.sizeY;
        }
        if(startPositionY < 0){
            startPositionY = 0;
            endPositionY = Game.sizeY;
        }

        ArrayList<Tile> tiles = game.getTiles();
        if(tiles.size() > 0){
            for(Tile tile: tiles){
                if(startPositionX <= tile.getPositionX() && tile.getPositionX() <= endPositionX) {
                    if (startPositionY <= tile.getPositionY() && tile.getPositionY() <= endPositionY) {
                        g.drawImage(tile.getImage(), (tile.getPositionX() - startPositionX)* 32, (tile.getPositionY() - startPositionY) * 32, 32, 32, null);
                    }
                }
            }
        }
        ArrayList<Monster> monsters = game.getMonsters();
        if(monsters.size() > 0){
            for(Monster monster: monsters){
                if(startPositionX <= monster.getPositionX() && monster.getPositionX() <= endPositionX) {
                    if (startPositionY <= monster.getPositionY() && monster.getPositionY() <= endPositionY) {
                        g.drawImage(monster.getImage(), (monster.getPositionX() - startPositionX)* 32, (monster.getPositionY() - startPositionY) * 32, 32, 32, null);
                    }
                }
            }
        }
        ArrayList<Wall> walls = game.getWalls();
        if(walls.size() > 0){
            for(Wall wall: walls){
                if(startPositionX <= wall.getPositionX() && wall.getPositionX() <= endPositionX) {
                    if (startPositionY <= wall.getPositionY() && wall.getPositionY() <= endPositionY) {
                        g.drawImage(wall.getImage(), (wall.getPositionX() - startPositionX)* 32, (wall.getPositionY() - startPositionY) * 32, 32, 32, null);
                    }
                }
            }
        }
        ArrayList<Item> items = game.getItems();
        if(items.size() > 0){
            for(Item item: items){
                if(startPositionX <= item.getPositionX() && item.getPositionX() <= endPositionX) {
                    if (startPositionY <= item.getPositionY() && item.getPositionY() <= endPositionY) {
                        if(item instanceof Coin) {
                            g.drawImage(((Coin)item).getImage(), (item.getPositionX() - startPositionX) * 32, (item.getPositionY() - startPositionY) * 32, 32, 32, null);
                        }
                    }
                }
            }
        }
        g.drawImage(game.player.getImage(), (game.player.getPositionX() - startPositionX) * 32, (game.player.getPositionY() - startPositionY)* 32, 32, 32, null);
    }


    public void setMapMatrix(int[][] map){
        this.map = map;
        this.repaint();
    }


}
