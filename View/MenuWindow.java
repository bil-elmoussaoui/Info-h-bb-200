package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//puisque ces classes ne peuvent se lancer tttes seules elles ne sont pas public

class MenuWindow {
	
	public JPanel getJPanel(){
		JPanel menuWindow = new JPanel(new BorderLayout());
		JPanel menuPanel = new JPanel();
		//menuPanel.setAlignmentY(Compenent.CENTER_ALIGNMENT);
		
		JButton Play = new JButton("Jouer");
		menuPanel.add(Play,BorderLayout.PAGE_START);
		Play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent p){
				MainWindow.showPlayWindow();
			}
		});
		
		JButton Option = new JButton("Option");
		menuPanel.add(Option);
		Option.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent o){
				MainWindow.showOptionWindow();
			}
		});
		
		JButton About = new JButton("About");
		About.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				MainWindow.showAboutWindow();
			}
		});
		menuPanel.add(About);
		
		JButton Quit = new JButton("Quitter");
		Quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent q){
				MainWindow.closeWindow();
			}
		});
		menuPanel.add(Quit,BorderLayout.PAGE_END);
		
		
		
		menuWindow.add(menuPanel, BorderLayout.CENTER);
		JLabel authors = new JLabel("fucking bitches");
		menuWindow.add(authors, BorderLayout.PAGE_END);
		return menuWindow;
	}
	

}
