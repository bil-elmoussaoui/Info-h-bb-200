package Interface;
import java.awt.*;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MainWindow {
	private static JFrame mainWindow;		//pk static??
	
	public MainWindow(){
		this.initialize();
	}
	
	public static void main(String[] args){
		MainWindow mwindow = new MainWindow();
	}
	
	public void initialize(){
		mainWindow = new JFrame("Le Nom du Jeu");
		//JLabel fondecran = new JLabel(new ImageIcon(ImageIO.read(bibi.jpg)));
		MenuWindow Window = new MenuWindow();
		mainWindow.getContentPane().setPreferredSize(new Dimension(800, 600));
		mainWindow.setResizable(false);
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.pack();//close jai finis d'ajouuter des trucs
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true); // lance l'affichage
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
		mainWindow.getContentPane().removeAll();//on repart du main window mias on enlève tout le contenu
		mainWindow.getContentPane().add(Window.getJPanel());//on modifie le contenu du panel
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
