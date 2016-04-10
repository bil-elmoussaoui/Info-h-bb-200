package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//puisque ces classes ne peuvent se lancer tttes seules elles ne sont pas public

class MenuWindow {
	
	public JPanel getJPanel(){
		JPanel menuWindow = new JPanel(new BorderLayout());
		Box menuPanel = Box.createVerticalBox();
		menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.add(Box.createGlue());

		JButton Play = new JButton("Jouer");
        Play.setPreferredSize(new Dimension(80, 40));
        Play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showPlayWindow();
			}
		});
        menuPanel.add(Play);
        menuPanel.add(Box.createVerticalStrut(25));

        JButton Option = new JButton("Option");
        Option.setPreferredSize(new Dimension(80, 40));
        Option.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showOptionWindow();
			}
		});
        menuPanel.add(Option);
        menuPanel.add(Box.createVerticalStrut(25));

		JButton About = new JButton("About");
        About.setPreferredSize(new Dimension(80, 40));
        About.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showAboutWindow();
			}
		});
		menuPanel.add(About);
        menuPanel.add(Box.createVerticalStrut(25));


        JButton Quit = new JButton("Quitter");
        Quit.setPreferredSize(new Dimension(80, 40));
        Quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.closeWindow();
			}
		});
		menuPanel.add(Quit);
        menuPanel.add(Box.createVerticalStrut(25));
        menuWindow.add(menuPanel, BorderLayout.CENTER);

        JLabel authors = new JLabel("fucking bitches");
		menuWindow.add(authors, BorderLayout.PAGE_END);
		return menuWindow;
	}
	

}
