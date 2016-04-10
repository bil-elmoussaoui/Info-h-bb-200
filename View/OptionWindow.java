package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OptionWindow {

	public JPanel getJPanel(){
		JPanel optionWindow = new JPanel(new BorderLayout());
		JPanel optionPanel = new JPanel();

		Box soundBox = Box.createHorizontalBox();
		JLabel soundLabel = new JLabel("Son");
		JCheckBox soundCheckBox = new JCheckBox();

		soundBox.add(soundLabel);
		soundBox.add(soundCheckBox);
		optionPanel.add(soundBox);
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
