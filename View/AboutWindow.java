package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AboutWindow {
	
	public JPanel getJPanel(){
		JPanel aboutWindow = new JPanel();
		Box aboutBox = Box.createVerticalBox();

		JLabel maker = new JLabel("cette merde est faite par deux hamals");
		aboutBox.add(maker);

		JLabel law = new JLabel("cette merde est faite par deux hamals");
		aboutBox.add(law);

		JLabel end = new JLabel("cette merde est faite par deux hamals");
		aboutBox.add(end);

		JButton backButton = new JButton("Retour");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showMainWindow();
			}
		});
		aboutBox.add(backButton);
		aboutWindow.add(aboutBox);

		return (aboutWindow);
	}

}
