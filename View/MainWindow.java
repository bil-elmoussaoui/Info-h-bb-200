package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow  {
	private static JFrame mainWindow;
    private static Map levelMap = new Map();


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
		mainWindow.getContentPane().setPreferredSize(new Dimension(800, 600));
        mainWindow.setResizable(false);
		mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.getContentPane().setBackground(Color.gray);
        mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
        mainWindow.setLocationByPlatform(true);
        mainWindow.setVisible(true);
	}

    public static void showLevelWindow(){
        LevelWindow Window = new LevelWindow(MainWindow.levelMap);
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.setFocusable(true);
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

}
