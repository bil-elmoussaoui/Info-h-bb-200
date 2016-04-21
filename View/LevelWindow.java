package View;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

public class LevelWindow{
    private JPanel levelMap;
    private JPanel gamePanel;

    public LevelWindow(JPanel levelMap){
        this.levelMap = levelMap;
        gamePanel = new GamePanel().getJPanel();
    }

    public JPanel getJPanel(){
        JPanel levelWindow = new JPanel(new BorderLayout());
        levelWindow.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        levelWindow.add(gamePanel, BorderLayout.PAGE_END);
        if(MainWindow.newGame){

            this.levelMap.repaint();
        }
        levelWindow.add(this.levelMap, BorderLayout.CENTER);
        return levelWindow;
    }

}