package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AboutWindow {
	
	public JPanel getJPanel(){
		JPanel aboutWindow = new JPanel(new BorderLayout());
		JPanel aboutPanel = new JPanel();
		
		JLabel maker = new JLabel("cette merde est faite par deux hamals");
		aboutWindow.add(maker, BorderLayout.PAGE_START);
		
		JLabel law = new JLabel("cette merde est faite par deux hamals");
		aboutWindow.add(law, BorderLayout.CENTER);
		
		JLabel end = new JLabel("cette merde est faite par deux hamals");
		aboutWindow.add(end, BorderLayout.PAGE_END);
		
		JButton backButton = new JButton("Retour");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showMainWindow();
			}
		});
		backButton.setPreferredSize(new Dimension(50, 50));
		aboutWindow.add(backButton, BorderLayout.WEST);
		
		return (aboutWindow);
	}

}
