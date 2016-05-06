package View;

import Controller.Animation;
import Model.AnimationObserver;
import Model.Game;
import Model.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class MainWindow implements Observer, AnimationObserver{
    private Observer observer;
    public static boolean gamePaused = false;
    public static boolean newGame = false;
    public static boolean gameStarted = false;
    public boolean menuAlive = true;
    public transient GraphicsDevice device;
    private JFrame mainWindow;
    private transient Animation animation;
    private Map levelMap = new Map();

    public MainWindow() {
        // utilisé pour pouvoir jouer en plein écran
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        initialize();
    }

    public static void main(String args[]) {
        new MainWindow();
    }

    public void initialize() {
        mainWindow = new JFrame();
        // pour pouvoir accéder au menu en appyuant sur la touche escape
        mainWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (menuAlive) {
                        if (!MainWindow.gamePaused) {
                            MainWindow.gamePaused = true;
                            showMenuWindow();
                        } else {
                            MainWindow.gamePaused = false;
                            Game.playerIsShopping = false;
                            showLevelWindow();
                        }
                        notifier();
                    }
                }
            }

        });
        mainWindow.setTitle("The Little Knight");
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setUndecorated(true);
        mainWindow.setResizable(false);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLocationByPlatform(true);
        //pour pouvoir jouer en plein écran
        device.setFullScreenWindow(mainWindow);
        mainWindow.setVisible(true);
    }

    // utilisé pour l'obeserver de MenuWindow
    public void setAnimation(Animation animation){
        this.animation = animation;
    }

    public void showLevelWindow() {
        // afficher la map
        if (!MainWindow.gameStarted) MainWindow.gameStarted = true;
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(this.levelMap);
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
        notifier();
    }

    public void closeWindow() {
        mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
    }

    public void showMenuWindow() {
        // afficher le menu principale
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(new MenuWindow(this,  menuAlive, animation));
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

    public void showLoadWindow() {
        // afficher les diffèrentes parties sauvgardées
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(new LoadWindow());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

    public void showSettingsWindow(){
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(new SettingsWindow(this));
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

    public void showBuyWindow(Game game) {
        // afficher le marchand
        gamePaused = true;
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(new BuyWindow(game));
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
        notifier();
    }

    public void draw(int[][] map, Game game) {
        // mettre à jour la map
        levelMap.setGame(game);
        levelMap.setMapMatrix(map);
    }

    @Override
    public void update(){
        // pour afficher un menu diffèrent quand le joueur est mort
        this.menuAlive = false;
    }

    @Override
    public void attach(Observer o) {
        observer = o;
    }

    @Override
    public void notifier() {
       observer.update();
    }

    public void setKeyListener(KeyListener keyboard) {
        mainWindow.addKeyListener(keyboard);
    }

}