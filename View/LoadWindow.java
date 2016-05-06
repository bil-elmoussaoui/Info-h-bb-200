package View;

import Main.Main;
import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class LoadWindow extends JPanel {
    private ArrayList<String> filesList = new ArrayList<>();

    public LoadWindow() {
        File folder = new File("Data/");
        if (folder.exists()) {
            for (File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    filesList.add(fileEntry.getName());
                }
            }
        }

        this .setBorder(BorderFactory.createEmptyBorder(260, 400, 220, 350));

        JPanel menuLoad = new JPanel();
        menuLoad.setOpaque(false);

        menuLoad.setLayout(new GridLayout(filesList.size(), 0, 10, 10));
        for (int i = 0; i < filesList.size(); i++) {
            final String fileName = filesList.get(i);
            BButton loadButton = new BButton(fileName.replace(".ser", ""));
            loadButton.setPreferredSize(new Dimension(300, 50));
            loadButton.addActionListener((ActionEvent e) -> {
                Main.load(fileName);
            });
            menuLoad.add(loadButton);
        }
        this.add(menuLoad);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            BufferedImage img = ImageIO.read(new File("Images/tile.png"));
            for (int i = 0; i < Game.shownSizeX; i++) {
                for (int j = 0; j < Game.shownSizeY + 1; j++) {
                    g.drawImage(img, i * Game.pixelX, j * Game.pixelY, Game.pixelX, Game.pixelY, null);
                }
            }
        } catch (Exception e) {
        }
    }

}
