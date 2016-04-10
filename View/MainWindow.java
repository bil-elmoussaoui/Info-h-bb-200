package Interface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow {
	private static JFrame mainWindow;

	public MainWindow(){
		this.initialize();
	}

	public static void main(String[] args){
		MainWindow mainWindow = new MainWindow();
	}

	public void initialize(){
		mainWindow = new JFrame("Le Nom du Jeu");
		MenuWindow Window = new MenuWindow();
		mainWindow.getContentPane().setPreferredSize(new Dimension(800, 600));
		mainWindow.setResizable(false);
		mainWindow.getContentPane().add(Window.getJPanel());
    		mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}

	public static void showPlayWindow(){
		MenuGameWindow Window = new MenuGameWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.setVisible(false);
		mainWindow.setVisible(true);
	}

	public static void showOptionWindow() {
		OptionWindow Window = new OptionWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.setVisible(false);
		mainWindow.setVisible(true);
	}

	public static void showAboutWindow(){
		AboutWindow Window = new AboutWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.setVisible(false);
		mainWindow.setVisible(true);
	}

	public static void closeWindow(){
		mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
	}

	public static void showMainWindow() {
		MenuWindow Window = new MenuWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.setVisible(false);
		mainWindow.setVisible(true);
	}

}
