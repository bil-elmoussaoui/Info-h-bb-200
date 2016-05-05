package View;

import Controller.Animation;
import Model.Game;
import Model.Observer;
import Model.AnimationObserver;
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
    public GraphicsDevice device;
    private JFrame mainWindow;
    private Animation animation;
    private Map levelMap = new Map();

    public MainWindow() {
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        initialize();
    }

    public void setAnimation(Animation animation){
        this.animation = animation;
    }

    public static void main(String args[]) {
        new MainWindow();
    }


    public void initialize() {
        mainWindow = new JFrame();
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
        device.setFullScreenWindow(mainWindow);
        mainWindow.setVisible(true);
    }

    public void showLevelWindow() {
        if (!MainWindow.gameStarted) MainWindow.gameStarted = true;
        LevelWindow Window = new LevelWindow(levelMap);
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
        notifier();
    }

    public void closeWindow() {
        mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
    }

    public void showMenuWindow() {
        MenuWindow Window = new MenuWindow(this,  menuAlive, animation);
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

    public void showLoadWindow() {
        LoadWindow Window = new LoadWindow(this);
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

    public void showBuyWindow(Game game) {
        BuyWindow Window = new BuyWindow(game);
        gamePaused = true;
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
        notifier();
    }

    public void draw(int[][] map, Game game) {
        levelMap.setGame(game);
        levelMap.setMapMatrix(map);
    }

    public void update(){
        this.menuAlive = false;
    }

    public void attach(Observer o) {
        observer = o;
    }

    public void notifier() {
       observer.update();
    }

    public void setKeyListener(KeyListener keyboard) {
        mainWindow.addKeyListener(keyboard);
    }

}