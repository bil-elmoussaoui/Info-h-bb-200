package View;

import Model.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow {
	public static GraphicsDevice device;
	public static boolean gamePaused = false;
	public static boolean newGame = false;
	private JFrame mainWindow;
    private Map levelMap = new Map();
    private Game game;

	public MainWindow(){
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		initialize();
	}

	public static void main(String args[]){
        MainWindow mainWindow = new MainWindow();
    }

	public void initialize(){
		mainWindow = new JFrame();
        mainWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}
            @Override
            public void keyReleased(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
                    MainWindow.gamePaused = true;
                    showMenuWindow();
                }
            }

        });
		mainWindow.setTitle("The Little Knight");
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setUndecorated(true);
		mainWindow.setResizable(false);
        showMenuWindow();
        mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setLocationByPlatform(true);
		device.setFullScreenWindow(mainWindow);
		mainWindow.setVisible(true);
	}

    public void showLevelWindow(){
        LevelWindow Window = new LevelWindow(levelMap);
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

	public  void closeWindow(){
        mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
	}

	public void showMenuWindow() {
		MenuWindow Window = new MenuWindow(this);
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.revalidate();
	}

	public void showLoadWindow(){
		LoadWindow Window = new LoadWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.revalidate();
	}

    public void draw(int[][] map, Game game){
        levelMap.setGame(game);
        levelMap.setMapMatrix(map);
	}


    public void setKeyListener(KeyListener keyboard){
        mainWindow.addKeyListener(keyboard);
    }


    }