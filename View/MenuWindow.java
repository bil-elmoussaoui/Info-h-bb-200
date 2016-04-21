package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.BButton;

class MenuWindow {


	public JPanel getJPanel(){
		JPanel menuWindow = new JPanel();
        menuWindow.setBorder(BorderFactory.createEmptyBorder(220, 400, 220, 350));
        JPanel menuPanel = new JPanel();
        BButton Play;

		if(MainWindow.gamePaused){
            menuPanel.setLayout(new GridLayout(4, 0, 10, 10));
			BButton Resume = new BButton("Resume");
			Resume.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
                    MainWindow.showLevelWindow();
				}
			});
            menuPanel.add(Resume);
            Play = new BButton("New Game");
        } else {
            menuPanel.setLayout(new GridLayout(3, 0, 10, 10));
            Play = new BButton("Play");

        }
		Play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
                if(MainWindow.gamePaused){
                    MainWindow.newGame = true;
                }
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
