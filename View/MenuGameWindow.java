package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuGameWindow {

	public Component getJPanel() {
		JPanel menuGameWindow = new JPanel(new BorderLayout());
		JPanel levelsPanel = new JPanel();
		
		JLabel level1Label = new JLabel();
		level1Label.setIcon(new ImageIcon("C:\\Users\\Bejamin\\workspace\\Projet Info\\src\\Interface\\bibi.jpg"));
		level1Label.setPreferredSize(new Dimension(80, 80));
		level1Label.addMouseListener(new MouseAdapter() {

			public void mouseClick(MouseEvent e){
				System.out.println("yeeaaah");
			}
		});
		
		levelsPanel.add(level1Label);
		
		JLabel level2Label = new JLabel();
		level2Label.setIcon(new ImageIcon("C:\\Users\\Bejamin\\workspace\\Projet Info\\src\\Interface\\bibi.jpg"));
		level2Label.setPreferredSize(new Dimension(80, 80));
		levelsPanel.add(level2Label);
		
		JLabel level3Label = new JLabel();
		level3Label.setIcon(new ImageIcon("C:\\Users\\Bejamin\\workspace\\Projet Info\\src\\Interface\\bibi.jpg"));
		level3Label.setPreferredSize(new Dimension(80, 80));
		levelsPanel.add(level3Label);
		
		menuGameWindow.add(levelsPanel, BorderLayout.LINE_START);
		
		
		JButton backButton = new JButton("Retour");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showMainWindow();
			}
		});
		
		menuGameWindow.add(backButton, BorderLayout.PAGE_END);
		
		return menuGameWindow;
	}

}
