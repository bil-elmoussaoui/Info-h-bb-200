package View;

import Controller.Animation;
import Main.Main;
import Model.AnimationObserver;
import Model.Game;
import Model.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

class MenuWindow extends JPanel implements AnimationObserver{
    private Observer observer;

    public MenuWindow(MainWindow window, boolean menuAlive, Animation animation) {
        // observer utilisé pour arreter les threads durant l'arret du jeu
        this.attach(animation);

        if (menuAlive) {
            this.setBorder(BorderFactory.createEmptyBorder(260, 400, 220, 350));
        } else {
            this.setBorder(BorderFactory.createEmptyBorder(20, 400, 220, 350));
        }

        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        BButton Play;

        if (MainWindow.gamePaused && MainWindow.gameStarted && menuAlive) {
            menuPanel.setLayout(new GridLayout(6, 0, 10, 10));
            BButton Resume = new BButton("Resume");
            Resume.addActionListener((ActionEvent e) -> {
                window.showLevelWindow();
                MainWindow.gamePaused = false;
                notifier();
            });
            menuPanel.add(Resume);
            Play = new BButton("New Game");
        } else {
            menuPanel.setLayout(new GridLayout(4, 0, 10, 10));
            Play = new BButton("Play");
        }

        Play.addActionListener((ActionEvent e) -> {
            if (MainWindow.gamePaused || !menuAlive) {
                MainWindow.newGame = true;
            }
            window.showLevelWindow();
            MainWindow.gamePaused = false;
            notifier();
        });
        menuPanel.add(Play);
        if (MainWindow.gamePaused && MainWindow.gameStarted && menuAlive) {
            BButton Save = new BButton("Save");
            Save.addActionListener((ActionEvent e) -> {
                Main.save();
            });
            menuPanel.add(Save);
        }
        BButton Load = new BButton("Load");
        Load.addActionListener((ActionEvent e) -> {
            window.showLoadWindow();
        });
        menuPanel.add(Load);
        if(!MainWindow.gamePaused) {
            BButton Settings = new BButton("Settings");
            Settings.addActionListener((ActionEvent e) -> {
                window.showSettingsWindow();
            });
            menuPanel.add(Settings);
        }
        BButton Quit = new BButton("Exit");
        Quit.addActionListener((ActionEvent e) -> {
            window.closeWindow();
        });
        menuPanel.add(Quit);


        if (!menuAlive) {
            MainWindow.gamePaused = true;
            JLabel gameOver = new JLabel();
            gameOver.setIcon(new ImageIcon("Images/death.jpg"));
            gameOver.setPreferredSize(new Dimension(500, 500));
            this.add(gameOver);
        }
        this.add(menuPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            BufferedImage img = ImageIO.read(new File("Images/tile.png"));
            for (int i = 0; i < Game.shownSizeX; i++) {
                for (int j = 0; j < Game.shownSizeY + 1; j++) {
                    g.drawImage(img, i * Game.pixelX, j * Game.pixelY, Game.pixelX, Game.pixelY, null);
                }
            }
        } catch (Exception e) {
        }
    }


    @Override
    public void attach(Observer o) {
        observer = o;
    }

    @Override
    public void notifier() {
        observer.update();
    }
}
