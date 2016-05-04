package View;

import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

class BuyWindow {

    private Game game;


    public BuyWindow(Game game){
        this.game = game;
    }

    public JPanel getJPanel() {

        JPanel buyWindow = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                try {
                    BufferedImage imgFond = ImageIO.read(new File("Images/tile.png"));
                    BufferedImage inventoryBg = ImageIO.read(new File("Images/inventory.png"));
                    BufferedImage imgPotion = ImageIO.read(new File("Images/health-potion.png"));
                    BufferedImage imgArmure = ImageIO.read(new File("Images/shield-walking.png"));
                    BufferedImage imgSpell = ImageIO.read(new File("Images/key.png"));
                    BufferedImage imgArme = ImageIO.read(new File("Images/sword_iron.png"));
                    BufferedImage imgCoins = ImageIO.read(new File("Images/coin.png"));

                    for (int i = 0; i < Game.shownSizeX;  i++) {
                        for (int j = 0; j < Game.shownSizeY + 1; j++) {
                            g.drawImage(imgFond, i * Game.pixelX, j * Game.pixelY, Game.pixelX, Game.pixelY, null);
                        }
                    }


                    //Bonhomme/vie/sous
                    int startY = (Game.screenY - 4*96)/2;
                    int startX = (Game.screenX - 4*96)/2;
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.BOLD, 16));

                    BufferedImage imgPlayer = ImageIO.read(new File("Images/player.png")).getSubimage(64,128,64,64);


                    //vie
                    BufferedImage imgheart = ImageIO.read(new File("Images/heart.png"));
                    int m = 0;
                    for (int l= 0; l < Math.floor(game.player.getHealth()); l++) {
                        g.drawImage(imgheart, 32 * l + 5 + startX + 400 , startY + 40 , 32, 32, null);
                        m += 1;
                    }
                    if(game.player.getHealth() > m){
                        g.drawImage(imgheart.getSubimage(0, 0, 8, 16), 32 * m + 5 + startX + 400 , startY + 40, 16, 32, null);
                    }

                    //sous
                    g.drawImage(imgPlayer, startX + 250, startY-40, 200, 200, null);
                    g.drawImage(imgCoins,startX + 410, startY + 80, 24, 24, null);
                    g.drawString("X " + String.valueOf(game.player.getCoins()), startX + 435, startY + 97);



                    for (int i=0; i<4; i++){
                        for (int j=0; j<4; j++){
                            g.drawImage(inventoryBg, i*96 + startX -250, j*96 + startY, 96, 96, null);
                            if(i == game.salesman.getSelectorX() && j == game.salesman.getSelectorY()){
                                g.drawRect(i*96 + startX -250, j*96 + startY, 96, 96);
                            }
                            switch (game.salesman.carteAchat[i][j][0]){
                                case(5):
                                    g.drawImage(imgPotion, i*96 + startX+12 -250, 12+startY, 64, 64, null);
                                break;
                                case(1):
                                    g.drawImage(imgArmure, i*96 + startX+12 -250, 96 + 12+startY, 64, 64, null);
                                break;
                                case(2):
                                    g.drawImage(imgSpell, i*96 + startX+12 -250, 96*2 + 12+startY, 64, 64, null);
                                break;
                                case(3):
                                    g.drawImage(imgArme, i*96 + startX+12 -250, 96*3 + 12+startY, 64, 64, null);
                                break;
                            }
                            if(game.salesman.carteAchat[i][j][0] != 0) {
                                g.drawString(String.valueOf(game.salesman.carteAchat[i][j][1]) + " X", i * 96 + startX + 20 - 250, j * 96 + 12 + startY + 80);
                                g.drawImage(imgCoins, i * 96 + startX + 64 - 250, j * 96 + 12 + startY + 67, 16, 16, null);
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



