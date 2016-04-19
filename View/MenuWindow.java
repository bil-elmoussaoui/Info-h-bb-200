import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BSwing.*;

class MenuWindow {

	public JPanel getJPanel(){
		JPanel menuWindow = new JPanel();
        menuWindow.setBorder(BorderFactory.createEmptyBorder(220, 350, 220, 350));
        JPanel menuPanel = new JPanel(new GridLayout(3, 0, 10, 10));

		BButton Play = new BButton("Play");
		Play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showLevelWindow();
			}
		});
		menuPanel.add(Play);

		BButton Load = new BButton("Load");
		Load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					MainWindow.showLoadWindow();
			}
		});
        menuPanel.add(Load);

        BButton Quit = new BButton("Exit");
		Quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.closeWindow();
			}
		});
		menuPanel.add(Quit);

        menuWindow.add(menuPanel);
		return menuWindow;
	}

}
