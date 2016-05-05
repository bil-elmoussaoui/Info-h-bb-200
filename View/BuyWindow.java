package View;

import Model.Game;
import Model.Potion;
import Model.Weapon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

class BuyWindow {

    private Game game;


    public BuyWindow(Game game) {
        this.game = game;
    }

    public JPanel getJPanel() {

        JPanel buyWindow = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                try {
                    BufferedImage imgFond = ImageIO.read(new File("Images/tile.png"));
                    BufferedImage inventoryBg = ImageIO.read(new File("Images/inventory.png"));
                    BufferedImage imgHealthPotion = ImageIO.read(new File("Images/health-potion.png"));
                    BufferedImage imgFireLionPotion = ImageIO.read(new File("Images/firelion-potion.png"));
                    BufferedImage imgIcetaclePotion = ImageIO.read(new File("Images/icetacle-potion.png"));
                    BufferedImage imgCoins = ImageIO.read(new File("Images/coin.png"));
                    BufferedImage imgKey = ImageIO.read(new File("Images/key.png"));
                    BufferedImage imgShield = ImageIO.read(new File("Images/upg_shield.png"));
                    BufferedImage imgBow = ImageIO.read(new File("Images/upg_bow.png"));
                    BufferedImage imgDagger = ImageIO.read(new File("Images/upg_dagger.png"));
                    BufferedImage imgSpear = ImageIO.read(new File("Images/upg_spear.png"));
                    BufferedImage imgArrows = ImageIO.read(new File("Images/arrows.png"));

                    for (int i = 0; i < Game.shownSizeX; i++) {
                        for (int j = 0; j < Game.shownSizeY + 1; j++) {
                            g.drawImage(imgFond, i * Game.pixelX, j * Game.pixelY, Game.pixelX, Game.pixelY, null);
                        }
                    }


                    //Bonhomme/vie/sous
                    int startY = (Game.screenY - 4 * 96) / 2;
                    int startX = (Game.screenX - 4 * 96) / 2;
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.BOLD, 16));

                    BufferedImage imgPlayer = ImageIO.read(new File("Images/player.png")).getSubimage(64, 128, 64, 64);


                    //vie
                    BufferedImage imgHeart = ImageIO.read(new File("Images/heart.png"));
                    int m = 0;
                    for (int l = 0; l < Math.floor(game.player.getHealth()); l++) {
                        g.drawImage(imgHeart, 32 * l + 5 + startX + 370, startY + 40, 32, 32, null);
                        m += 1;
                    }
                    if (game.player.getHealth() > m) {
                        g.drawImage(imgHeart.getSubimage(0, 0, 8, 16), 32 * m + 5 + startX + 370, startY + 40, 16, 32, null);
                    }

                    //sous
                    g.drawImage(imgPlayer, startX + 250, startY , 128, 128, null);
                    g.drawImage(imgCoins, startX + 380, startY + 80, 24, 24, null);
                    g.drawString("X " + String.valueOf(game.player.getCoins()), startX + 405  , startY + 97);
                    // inventory!
                    for (int i = 0; i < game.player.inventory.sizeMaxWeapon; i++) {
                        g.drawImage(inventoryBg, startX + 250 + 96*i, startY + 140, 96, 96, null);
                    }
                    for (int i = 0; i < game.player.inventory.countWeapons(); i++) {
                        Weapon weapon = game.player.inventory.getWeapon(i);
                        g.drawImage(weapon.getStaticImg(), startX + 250 + 96*i + 24, startY + 140 + 24, 48, 48, null);
                    }
                    // potions
                    for (int i = 0; i < game.player.inventory.sizeMaxItem; i++) {
                        g.drawImage(inventoryBg, startX + 250 + 96*i, startY + 250, 96, 96, null);
                    }
                    for (int i = 0; i < game.player.inventory.countItems(); i++) {
                        if (game.player.inventory.getItem(i) instanceof Potion) {
                            g.drawImage(((Potion) game.player.inventory.getItem(i)).getImage(), startX + 250 + 96*i + 24, startY + 250 + 24, 48, 48, null);
                        }
                    }
                    // key case
                    g.drawImage(inventoryBg, startX + 250 + 96*4, startY + 250, 96, 96, null);
                    if (game.player.hasKey) {
                        g.drawImage(imgKey, startX + 250 + 96*4 + 32, startY + 250 +24, 32, 32, null);
                    }
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(3));

                    for (int i = 0; i < game.salesman.carteAchat.length; i++) {
                        for (int j = 0; j < game.salesman.carteAchat[0].length; j++) {
                            g.drawImage(inventoryBg, i * 96 + startX - 250 + 4, j * 96 + startY + 4, 96, 96, null);
                            if (i == game.salesman.getSelectorX() && j == game.salesman.getSelectorY()) {
                                g.drawRect(i * 96 + startX - 250, j * 96 + startY, 96, 96);
                            }
                            switch (game.salesman.carteAchat[i][j][0]) {
                                case (1):
                                    g.drawImage(imgHealthPotion, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);
                                    break;
                                case (2):
                                    g.drawImage(imgFireLionPotion, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);
                                    break;
                                case (3):
                                    g.drawImage(imgIcetaclePotion, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);
                                    break;
                                case 4:
                                    g.drawImage(imgShield, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);
                                    break;
                                case 5:

                                    g.drawImage(imgArrows, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);

                                    break;
                                case 6:
                                    g.drawImage(imgBow, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);
                                    break;
                                case 7:
                                    g.drawImage(imgDagger, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);
                                    break;
                                case 8:
                                    g.drawImage(imgSpear, i * 96 + startX + 12 - 250, j * 96 + 12 + startY, 64, 64, null);
                                    break;
                                case 16:
                                    g.drawImage(imgKey, i * 96 + startX + 32 - 250, j * 96 + 32 + startY, 32, 32, null);
                                    break;

                            }
                            if (game.salesman.carteAchat[i][j][0] != 0) {
                                String price = String.valueOf(game.salesman.carteAchat[i][j][1]);
                                g.drawString(price + " X", i * 96 + startX - 240, j * 96 + 12 + startY + 80);
                                g.drawImage(imgCoins, (i + 1) * 96 + startX - 270, j * 96 + 12 + startY + 67, 16, 16, null);
                            }
                        }

                    }

                } catch (Exception e) {
                }
            }
        };
        return buyWindow;
    }


}



