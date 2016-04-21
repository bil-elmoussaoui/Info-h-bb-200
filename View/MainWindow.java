package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.Player;
public class MainWindow  {
	public static GraphicsDevice device = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getScreenDevices()[0];
	public static boolean gamePaused = false;
	public static boolean newGame = false;

	private static JFrame mainWindow;
    private static Map levelMap = new Map();
	public static Player player;

	public MainWindow(){
		initialize();
	}
    public static void main(String args[]){
        MainWindow mainWindow = new MainWindow();
    }
	public void initialize(){
		mainWindow = new JFrame();
		mainWindow.setTitle("The Little Knight");
		MenuWindow Window = new MenuWindow();
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setUndecorated(true);
		mainWindow.setResizable(false);
		mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setLocationByPlatform(true);
		device.setFullScreenWindow(mainWindow);
		mainWindow.setVisible(true);
	}

    public static void showLevelWindow(){
        LevelWindow Window = new LevelWindow(MainWindow.levelMap);
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

	public static void closeWindow(){
		mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
	}

	public static void showMainWindow() {
		MenuWindow Window = new MenuWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.revalidate();
	}

	public static void showLoadWindow(){
		LoadWindow Window = new LoadWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.revalidate();
	}


    public static void draw(int[][] map){
        MainWindow.levelMap.setMapMatrix(map);
    }

    public static void setKeyListener(KeyListener keyboard){
        mainWindow.addKeyListener(keyboard);
    }

	public static void setPlayer(Player player){
		MainWindow.player = player;
	}

}