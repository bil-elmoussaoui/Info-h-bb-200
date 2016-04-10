package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OptionWindow {
	
	public JPanel getJPanel(){
		JPanel optionWindow = new JPanel(new BorderLayout());
		JPanel optionPanel = new JPanel();
		
		Box soundBox = Box.createHorizontalBox();
		JLabel soundLabel = new JLabel("Son");
		JCheckBox soundCheckBox = new JCheckBox();

		soundBox.add(soundLabel);//ajout du mot � la box
		soundBox.add(soundCheckBox);//ajout du boutton � la box
		optionPanel.add(soundBox);//ajout son � la box option
		JButton backButton = new JButton("Retour");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showMainWindow();
			}
		});
		
		
		optionWindow.add(optionPanel);
		optionWindow.add(backButton, BorderLayout.PAGE_END);
		
		return (optionWindow);
	}

}
