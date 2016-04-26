package View;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class LevelWindow{
    private JPanel levelMap;

    public LevelWindow(JPanel levelMap){
        this.levelMap = levelMap;
    }

    public JPanel getJPanel(){
        JPanel levelWindow = new JPanel(new BorderLayout());
        levelWindow.add(this.levelMap, BorderLayout.CENTER);
        levelWindow.setFocusable(true);
        levelWindow.requestFocus();
        levelWindow.requestFocusInWindow();
        return levelWindow;
    }

}