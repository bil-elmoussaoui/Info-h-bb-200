package View;

import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static Model.Game.shownSizeX;
import static Model.Game.shownSizeY;

public class SettingsWindow extends JPanel {
    private MainWindow window;

    public SettingsWindow(MainWindow window){
        this.window = window;
        this.setBorder(BorderFactory.createEmptyBorder(260, 500, 220, 450));
        this.setLayout(new GridLayout(3, 1, 10, 10));
        int mapX = 0, mapY = 0;
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("settings.ini"));
            mapX = Integer.valueOf(p.getProperty("sizeX"));
            mapY = Integer.valueOf(p.getProperty("sizeY"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel sizeX = new JLabel("Size X : ");
        sizeX.setForeground(Color.white);
        JTextField sizeXField = new JTextField();
        sizeXField.setText(String.valueOf(mapX));
        this.add(sizeX);
        this.add(sizeXField);

        JLabel sizeY = new JLabel("Size Y :");
        sizeY.setForeground(Color.white);
        JTextField sizeYField = new JTextField();
        sizeYField.setText(String.valueOf(mapY));
        this.add(sizeY);
        this.add(sizeYField);

        BButton reset = new BButton("Reset");
        reset.addActionListener((ActionEvent e) -> {
            sizeXField.setText(String.valueOf(shownSizeX));
            sizeYField.setText(String.valueOf(shownSizeY));
        });
        this.add(reset);
        BButton save = new BButton("Save");
        save.addActionListener((ActionEvent e) -> {
            int mapXRead, mapYRead;
            try {
                Properties p = new Properties();
                p.load(new FileInputStream("settings.ini"));
                mapXRead = Integer.valueOf(sizeXField.getText());
                mapYRead = Integer.valueOf(sizeYField.getText());
                mapXRead = (mapXRead >= shownSizeX && mapXRead <= 100) ? mapXRead : Game.shownSizeX;
                mapYRead = (mapYRead >= shownSizeY && mapYRead <= 100) ? mapYRead : Game.shownSizeY;
                p.setProperty("sizeX", String.valueOf(mapXRead));
                p.setProperty("sizeY", String.valueOf(mapYRead));
                Game.sizeX = mapXRead;
                Game.sizeY = mapYRead;
                p.store(new FileOutputStream("settings.ini"), "");//write the Properties object values to our ini file
                window.showMenuWindow();
            }catch (Exception o){
                mapXRead = shownSizeX;
                mapYRead = shownSizeY;
            }
        });
        this.add(save);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            BufferedImage img = ImageIO.read(new File("Images/tile.png"));
            for (int i = 0; i < shownSizeX; i++) {
                for (int j = 0; j < shownSizeY + 1; j++) {
                    g.drawImage(img, i * Game.pixelX, j * Game.pixelY, Game.pixelX, Game.pixelY, null);
                }
            }
        } catch (Exception e) {
        }
    }
}
