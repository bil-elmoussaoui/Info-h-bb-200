package View;

import Main.Main;
import Model.Game;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

class MenuWindow {
	private MainWindow window;
    private Game game;

	public MenuWindow(MainWindow window){
		this.window = window;
    }

	public JPanel getJPanel(){
		JPanel menuWindow = new JPanel(){
			@Override
            public void paintComponent(Graphics g){
                try{
                    BufferedImage img = ImageIO.read(new File("Images/tile.png"));
                    for(int i = 0; i < Game.shownSizeX; i ++){
                        for(int j = 0 ; j < Game.shownSizeY + 1; j++){
                            g.drawImage(img, i* Game.pixelX, j*Game.pixelY, Game.pixelX, Game.pixelY, null);
                        }
                    }
                }catch (Exception e){}
            }
		};
        menuWindow.setBorder(BorderFactory.createEmptyBorder(260, 400, 220, 350));
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        BButton Play;

		if(MainWindow.gamePaused  && MainWindow.gameStarted){
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
		if(MainWindow.gamePaused && MainWindow.gameStarted) {
			BButton Save = new BButton("Save");
            Save.addActionListener((ActionEvent e) -> {
                Main.save();
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
