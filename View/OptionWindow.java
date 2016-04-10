package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OptionWindow {
	
	public JPanel getJPanel(){
		JPanel optionWindow = new JPanel(new BorderLayout());
		JPanel optionPanel = new JPanel();
		
		JPanel soundPanel = new JPanel(new BorderLayout());	
		JLabel soundLabel = new JLabel("Son");
		JCheckBox soundBox = new JCheckBox();
		
		soundPanel.add(soundBox, BorderLayout.LINE_END);//ajout du boutton à la box
		soundPanel.add(soundLabel, BorderLayout.LINE_START);//ajout du mot à la box
		optionPanel.add(soundPanel);//ajout son à la box option
		
		JButton backButton = new JButton("Retour");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showMainWindow();
			}
		});
		
		
		optionWindow.add(optionPanel);
		optionWindow.add(backButton, BorderLayout.WEST);
		
		return (optionWindow);
	}

}
