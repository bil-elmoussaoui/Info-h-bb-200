package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class MenuWindow {
	private MainWindow window;

	public MenuWindow(MainWindow window){
		this.window = window;
	}

	public JPanel getJPanel(){
		JPanel menuWindow = new JPanel();
        menuWindow.setBorder(BorderFactory.createEmptyBorder(220, 400, 220, 350));
        JPanel menuPanel = new JPanel();
        BButton Play;

		if(MainWindow.gamePaused){
            menuPanel.setLayout(new GridLayout(5, 0, 10, 10));
			BButton Resume = new BButton("Resume");
			Resume.addActionListener((ActionEvent e) -> {
                    window.showLevelWindow();
					MainWindow.gamePaused = false;
			});
            menuPanel.add(Resume);
            Play = new BButton("New Game");
        } else {
            menuPanel.setLayout(new GridLayout(3, 0, 10, 10));
            Play = new BButton("Play");
        }

		Play.addActionListener((ActionEvent e) -> {
            if(MainWindow.gamePaused){
                MainWindow.newGame = true;
            }
            window.showLevelWindow();
            MainWindow.gamePaused = false;
		});
		menuPanel.add(Play);
		if(MainWindow.gamePaused) {
			BButton Save = new BButton("Save");
            Save.addActionListener((ActionEvent e) -> {
					window.showLoadWindow();
			});
			menuPanel.add(Save);
		}
		BButton Load = new BButton("Load");
		Load.addActionListener((ActionEvent e) -> {
					window.showLoadWindow();
		});
        menuPanel.add(Load);

        BButton Quit = new BButton("Exit");
		Quit.addActionListener((ActionEvent e) -> {
				window.closeWindow();
		});
		menuPanel.add(Quit);

        menuWindow.add(menuPanel);
		return menuWindow;
	}

}
