package View;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Map extends JPanel{
    private boolean hasStarted = false;
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
        if(hasStarted) {
            int startPositionX = game.player.getPositionX() - (int) Math.floor(Game.shownSizeX / 2) - 1;
            int endPositionX = game.player.getPositionX() + (int) Math.floor(Game.shownSizeX / 2);
            int startPositionY = game.player.getPositionY() - (int) Math.floor(Game.shownSizeY / 2);
            int endPositionY = game.player.getPositionY() + (int) Math.floor(Game.shownSizeY / 2);
            if (endPositionX > Game.sizeX || endPositionX - startPositionX < Game.shownSizeX) {
                startPositionX = Game.sizeX - Game.shownSizeX;
                endPositionX = Game.sizeX;
            }
            if (startPositionX < 0) {
                startPositionX = 0;
                endPositionX = Game.sizeX;
            }
            if (endPositionY > Game.sizeY || endPositionY - startPositionY < Game.shownSizeY) {
                startPositionY = Game.sizeY - Game.shownSizeY;
                endPositionY = Game.sizeY;
            }
            if (startPositionY < 0) {
                startPositionY = 0;
                endPositionY = Game.sizeY;
            }

            ArrayList<Tile> tiles = game.getTiles();
            if (tiles.size() > 0) {
                for (Tile tile : tiles) {
                    if (startPositionX <= tile.getPositionX() && tile.getPositionX() <= endPositionX) {
                        if (startPositionY <= tile.getPositionY() && tile.getPositionY() <= endPositionY) {
                            if (tile instanceof Portal) {
                                g.drawImage(((Portal) tile).getInImage(), (tile.getPositionX() - startPositionX) * Game.pixelX, (tile.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                                g.drawImage(((Portal) tile).getOutImage(), (tile.getPositionX() - startPositionX) * Game.pixelX, (((Portal) tile).getOutPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                            } else {
                                g.drawImage(tile.getImage(), (tile.getPositionX() - startPositionX) * Game.pixelX, (tile.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                            }
                        }
                    }
                }
            }
            ArrayList<Monster> monsters = game.getMonsters();
            if (monsters.size() > 0) {
                for (Monster monster : monsters) {
                    if (startPositionX <= monster.getPositionX() && monster.getPositionX() <= endPositionX) {
                        if (startPositionY <= monster.getPositionY() && monster.getPositionY() <= endPositionY) {
                            g.drawImage(monster.getImage(), (monster.getPositionX() - startPositionX) * Game.pixelX, (monster.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                        }
                    }
                }
            }
            ArrayList<Wall> walls = game.getWalls();
            if (walls.size() > 0) {
                for (Wall wall : walls) {
                    if (startPositionX <= wall.getPositionX() && wall.getPositionX() <= endPositionX) {
                        if (startPositionY <= wall.getPositionY() && wall.getPositionY() <= endPositionY) {
                            g.drawImage(wall.getImage(), (wall.getPositionX() - startPositionX) * Game.pixelX, (wall.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                        }
                    }
                }
            }
            ArrayList<Item> items = game.getItems();
            if (items.size() > 0) {
                for (Item item : items) {
                    if (startPositionX <= item.getPositionX() && item.getPositionX() <= endPositionX) {
                        if (startPositionY <= item.getPositionY() && item.getPositionY() <= endPositionY) {
                            if (item instanceof Coin) {
                                g.drawImage(((Coin) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX + 16, (item.getPositionY() - startPositionY) * Game.pixelY + 16, 32 , 32, null);
                            } else if (item instanceof Key) {
                                g.drawImage(((Key) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX + 16, (item.getPositionY() - startPositionY) * Game.pixelY + 16, 32, 32, null);
                            } else if (item instanceof WoodBox) {
                                g.drawImage(((WoodBox) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX, (item.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                            } else if (item instanceof Heart) {
                                g.drawImage(((Heart) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX + 16, (item.getPositionY() - startPositionY) * Game.pixelY + 16 , 32 , 32, null);
                            }
                        }
                    }
                }
            }
            g.drawImage(game.player.getImage(), (game.player.getPositionX() - startPositionX) * Game.pixelX, (game.player.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 22));

            int startX = Game.screenX - 200;
            int startY = 80;
            try {
                //Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/ARCADE.TTF")).deriveFont(22f);
                //g.setFont(new Font(customFont, Font.BOLD, 22));
                BufferedImage heart = ImageIO.read(new File("Images/heart.png"));
                BufferedImage coin = ImageIO.read(new File("Images/coin.png"));
                for (int i = 0; i < game.player.getHealth(); i++) {
                    g.drawImage(heart, startX + 32 * i + 5, startY, 32, 32, null);
                }
                g.drawImage(coin, startX + 12, startY + 40, 24, 24, null);
                g.drawString(("x" + game.player.getCoins()), startX + 42, startY + 57);
            } catch (Exception e) {
            }
            g.setColor(Color.black);
            g.fillRect(0, (Game.shownSizeY)*Game.pixelY, Game.shownSizeX*Game.pixelX , Game.shownSizeY*Game.pixelX);
            try {
                BufferedImage inventoryBg = ImageIO.read(new File("Images/inventory.png"));
                BufferedImage keyImg = ImageIO.read(new File("Images/key.png"));
                for (int i = 0; i < game.player.inventory.sizeMaxWeapon; i++){
                    g.drawImage(inventoryBg, (Game.shownSizeX - 1 -  (game.player.inventory.sizeMaxWeapon + game.player.inventory.sizeMaxItem + 3 - i))*Game.pixelX, (Game.shownSizeY)*Game.pixelY, Game.pixelX, Game.pixelY, null);
                }
                BufferedImage dagger = ImageIO.read(new File("Images/sword_iron.png"));
                for (int i = 0; i < game.player.inventory.countWeapons(); i++){
                    if(game.player.inventory.getWeapon(i) instanceof Dagger) {
                        g.drawImage(dagger, (Game.shownSizeX - 1 -  (game.player.inventory.sizeMaxWeapon + game.player.inventory.sizeMaxItem + 3 - i))*Game.pixelX + 16, (Game.shownSizeY)*Game.pixelY + 16, 32, 32, null);
                    }
                }
                for (int i = 0; i < game.player.inventory.sizeMaxItem; i++){
                    g.drawImage(inventoryBg, (Game.shownSizeX - 1 -  (game.player.inventory.sizeMaxItem  + 2 -i))*Game.pixelX, (Game.shownSizeY)*Game.pixelY, Game.pixelX, Game.pixelY, null);
                }
                for (int i = 0; i < game.player.inventory.countItems(); i++){
                    if(game.player.inventory.getItem(i) instanceof Heart) {
                        g.drawImage(((Heart)game.player.inventory.getItem(i)).getImage() , (Game.shownSizeX - 1 -  (game.player.inventory.sizeMaxItem  + 2 -i))*Game.pixelX + 16, (Game.shownSizeY) * Game.pixelY  + 16, 32, 32, null);
                    }
                }

                g.drawImage(inventoryBg, (Game.shownSizeX - 2)*Game.pixelX, (Game.shownSizeY)*Game.pixelY, Game.pixelX, Game.pixelY, null);
                if(game.player.hasKey){
                    g.drawImage(keyImg, (Game.shownSizeX - 2)*Game.pixelX + 16, (Game.shownSizeY)*Game.pixelY + 16, 32, 32, null);
                }

            }catch (Exception e){

            }
        } else{
            hasStarted = true;
        }
    }


    public void setMapMatrix(int[][] map){
        this.map = map;
        this.repaint();
    }


}
