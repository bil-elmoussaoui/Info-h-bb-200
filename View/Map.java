package View;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Map extends JPanel {
    private boolean hasStarted = false;
    private int[][] map;
    private Game game;

    public Map() {
        requestFocusInWindow();
        setFocusable(true);
        requestFocus();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void paint(Graphics g) {
        if (hasStarted) {
            int startPositionX = game.player.getPositionX() - (int) Math.floor(Game.shownSizeX / 2) - 1;
            int endPositionX = game.player.getPositionX() + (int) Math.floor(Game.shownSizeX / 2);
            int startPositionY = game.player.getPositionY() - (int) Math.floor((Game.shownSizeY) / 2) - 1;
            int endPositionY = game.player.getPositionY() + (int) Math.floor((Game.shownSizeY) / 2) - 1;
            if (endPositionX > Game.sizeX) {
                startPositionX = Game.sizeX - Game.shownSizeX;
                endPositionX = Game.sizeX;
            }
            if (startPositionX < 0) {
                startPositionX = 0;
                endPositionX = Game.sizeX;
            }
            if (endPositionY > Game.sizeY) {
                startPositionY = Game.sizeY - Game.shownSizeY;
                endPositionY = Game.sizeY;
            }
            if (startPositionY < 0) {
                startPositionY = 0;
                endPositionY = Game.sizeY;
            }
            ArrayList<Tile> tiles = game.getTiles();
            for (int i = 0; i < tiles.size(); i++) {
                Tile tile = tiles.get(i);
                if (startPositionX <= tile.getPositionX() && tile.getPositionX() <= endPositionX) {
                    if (startPositionY <= tile.getPositionY() && tile.getPositionY() <= endPositionY) {
                        if (!(tile instanceof Trap) || (tile instanceof Trap && !((Trap) tile).animationStopped)) {
                            g.drawImage(tile.getImage(), (tile.getPositionX() - startPositionX) * Game.pixelX, (tile.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                        }
                    }
                }
            }

            for (int i = 0; i < game.getWalls().size(); i++) {
                Wall wall = game.getWalls().get(i);
                if (startPositionX <= wall.getPositionX() && wall.getPositionX() <= endPositionX) {
                    if (startPositionY <= wall.getPositionY() && wall.getPositionY() <= endPositionY) {
                        g.drawImage(wall.getImage(), (wall.getPositionX() - startPositionX) * Game.pixelX, (wall.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                    }
                }
            }

            for (int i = 0; i < game.getItems().size(); i++) {
                Item item = game.getItems().get(i);
                if (startPositionX <= item.getPositionX() && item.getPositionX() <= endPositionX) {
                    if (startPositionY <= item.getPositionY() && item.getPositionY() <= endPositionY) {
                        if (item instanceof Coin) {
                            g.drawImage(((Coin) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX + 16, (item.getPositionY() - startPositionY) * Game.pixelY + 16, 32, 32, null);
                        } else if (item instanceof Key) {
                            g.drawImage(((Key) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX + 16, (item.getPositionY() - startPositionY) * Game.pixelY + 16, 32, 32, null);
                        } else if (item instanceof WoodBox) {
                            g.drawImage(((WoodBox) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX + 2, (item.getPositionY() - startPositionY) * Game.pixelY + 2, 60, 60, null);
                        } else if (item instanceof Potion) {
                            g.drawImage(((Potion) item).getImage(), (item.getPositionX() - startPositionX) * Game.pixelX + 16, (item.getPositionY() - startPositionY) * Game.pixelY + 16, 32, 32, null);
                        } else if (item instanceof Weapon) {
                            g.drawImage(((Weapon) item).getStaticImg(), (item.getPositionX() - startPositionX) * Game.pixelX + 16, (item.getPositionY() - startPositionY) * Game.pixelY + 16, 32, 32, null);
                        }
                    }
                }
            }
            for (int i = 0; i < game.thrownWeapons.size(); i++) {
                Weapon weapon = game.thrownWeapons.get(i);
                if (startPositionX <= weapon.getPositionX() && weapon.getPositionX() <= endPositionX) {
                    if (startPositionY <= weapon.getPositionY() && weapon.getPositionY() <= endPositionY) {
                        if (weapon instanceof Arrow) {
                            g.drawImage(weapon.getImage(), (weapon.getPositionX() - startPositionX) * Game.pixelX, (weapon.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                        }
                    }
                }
            }


            for (int i = 0; i < game.getMonsters().size(); i++) {
                Monster monster = game.getMonsters().get(i);
                if (startPositionX <= monster.getPositionX() && monster.getPositionX() <= endPositionX) {
                    if (startPositionY <= monster.getPositionY() && monster.getPositionY() <= endPositionY) {
                        g.drawImage(monster.getImage(), (monster.getPositionX() - startPositionX) * Game.pixelX, (monster.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                    }
                }
            }


            if (game.player != null) {
                g.drawImage(game.player.getImage(), (game.player.getPositionX() - startPositionX) * Game.pixelX, (game.player.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
            }
            for (int i = 0; i < game.thrownSpells.size(); i++) {
                Spell spell = game.thrownSpells.get(i);
                if (startPositionX <= spell.getPositionX() && spell.getPositionX() <= endPositionX) {
                    if (startPositionY <= spell.getPositionY() && spell.getPositionY() <= endPositionY) {
                        g.drawImage(spell.getImage(), (spell.getPositionX() - startPositionX) * Game.pixelX, (spell.getPositionY() - startPositionY) * Game.pixelY, 80, 80, null);
                    }
                }
            }
            if (game.door != null) {
                if (!game.door.getIsOpen()) {
                    g.drawImage(game.door.getImage(), (game.door.getPositionX() - startPositionX) * Game.pixelX, (game.door.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                }
            }
            if (game.salesman != null) {
                g.drawImage(game.salesman.getImage(), (game.salesman.getPositionX() - startPositionX) * Game.pixelX, (game.salesman.getPositionY() - startPositionY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
            }

            g.setColor(Color.black);
            g.fillRect(0, (Game.shownSizeY) * Game.pixelY, Game.shownSizeX * Game.pixelX, Game.shownSizeY * Game.pixelX);

            g.setFont(new Font("TimesRoman", Font.BOLD, 22));
            try {
                BufferedImage inventoryBg = ImageIO.read(new File("Images/inventory.png"));
                BufferedImage keyImg = ImageIO.read(new File("Images/key.png"));
                BufferedImage heart = ImageIO.read(new File("Images/heart.png"));
                BufferedImage coin = ImageIO.read(new File("Images/coin.png"));
                BufferedImage arrow = ImageIO.read(new File("Images/arrows.png"));
                // show heart life's!
                int j = 0;
                for (int i = 0; i < Math.floor(game.player.getHealth()); i++) {
                    g.drawImage(heart, 32 * i + 5, Game.shownSizeY * Game.pixelY, 32, 32, null);
                    j += 1;
                }
                if (game.player.getHealth() > j) {
                    g.drawImage(heart.getSubimage(0, 0, 8, 16), 32 * j + 5, Game.shownSizeY * Game.pixelY, 16, 32, null);
                }
                // show coins counter
                g.drawImage(coin, 12, Game.shownSizeY * Game.pixelY + 32, 24, 24, null);
                g.setColor(Color.WHITE);
                g.drawString(("x" + game.player.getCoins()), 42, Game.shownSizeY * Game.pixelY + 49);

                if (game.player.weapon instanceof Bow) {
                    g.drawImage(arrow, 250, Game.shownSizeY * Game.pixelY + 10, 16, 16, null);
                    g.drawString(("x" + ((Bow) game.player.weapon).arrowsCount), 280, Game.shownSizeY * Game.pixelY + 25);
                }
                if (game.player.weapon instanceof Bow) {
                    g.drawImage(inventoryBg, (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxItem + game.player.inventory.sizeMaxItem + 7)) * Game.pixelX, (Game.shownSizeY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                    g.drawImage(arrow, (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxItem + game.player.inventory.sizeMaxItem + 7)) * Game.pixelX + 6, (Game.shownSizeY) * Game.pixelY + 6, 48, 48, null);

                    g.drawString(String.valueOf(((Bow) game.player.weapon).arrowsCount), (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxItem + game.player.inventory.sizeMaxItem + 7)) * Game.pixelX + 30, (Game.shownSizeY + 1) * Game.pixelY);
                }

                // inventory!
                for (int i = 0; i < game.player.inventory.sizeMaxWeapon; i++) {
                    g.drawImage(inventoryBg, (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxWeapon + game.player.inventory.sizeMaxItem + 3 - i)) * Game.pixelX, (Game.shownSizeY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                    g.drawString(String.valueOf(i + 1), (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxWeapon + game.player.inventory.sizeMaxItem + 3 - i)) * Game.pixelX + 5, (Game.shownSizeY + 1) * Game.pixelY - 5);
                }
                for (int i = 0; i < game.player.inventory.countWeapons(); i++) {
                    Weapon weapon = game.player.inventory.getWeapon(i);
                    g.drawImage(weapon.getStaticImg(), (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxWeapon + game.player.inventory.sizeMaxItem + 3 - i)) * Game.pixelX, (Game.shownSizeY) * Game.pixelY, 64, 64, null);
                }
                // potions
                String[] touches = new String[]{"Y", "U", "I"};
                for (int i = 0; i < game.player.inventory.sizeMaxItem; i++) {
                    g.drawImage(inventoryBg, (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxItem + 2 - i)) * Game.pixelX, (Game.shownSizeY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                    g.drawString(touches[i], (Game.shownSizeX - 1 - (+game.player.inventory.sizeMaxItem + 2 - i)) * Game.pixelX + 5, (Game.shownSizeY + 1) * Game.pixelY - 5);
                }
                for (int i = 0; i < game.player.inventory.countItems(); i++) {
                    if (game.player.inventory.getItem(i) instanceof Potion) {
                        g.drawImage(((Potion) game.player.inventory.getItem(i)).getImage(), (Game.shownSizeX - 1 - (game.player.inventory.sizeMaxItem + 2 - i)) * Game.pixelX + 16, (Game.shownSizeY) * Game.pixelY + 16, 32, 32, null);
                    }
                }
                // key case
                g.drawImage(inventoryBg, (Game.shownSizeX - 2) * Game.pixelX, (Game.shownSizeY) * Game.pixelY, Game.pixelX, Game.pixelY, null);
                g.drawString("O", (Game.shownSizeX - 2) * Game.pixelX + 5, (Game.shownSizeY + 1) * Game.pixelY - 5);
                if (game.player.hasKey) {
                    g.drawImage(keyImg, (Game.shownSizeX - 2) * Game.pixelX + 16, (Game.shownSizeY) * Game.pixelY + 16, 32, 32, null);
                }

            } catch (Exception e) {

            }
        } else {
            hasStarted = true;
        }
    }


    public void setMapMatrix(int[][] map) {
        this.map = map;
        repaint();
    }


}
