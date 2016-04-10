package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.json.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class MenuGameWindow {

	public Component getJPanel() {
		JPanel menuGameWindow = new JPanel(new BorderLayout());
        File folder = new File("/home/bilal/Info/info-h-bb-200/Levels/");
        File[] folders = folder.listFiles();
        Arrays.sort(folders);
        System.out.println(folders.length%2);
        JPanel levelsPanel = new JPanel(new GridLayout(folders.length%3,  4));

        try {
            for (File fileEntry : folders) {
                String data = new String(Files.readAllBytes(Paths.get(fileEntry + "/config.json")));
                JSONObject level = new JSONObject(data);
                JLabel levelLabel = new JLabel();
                levelLabel.setText(level.getString("title"));
                levelLabel.setIcon(new ImageIcon(fileEntry + level.getString("image")));
                levelLabel.setPreferredSize(new Dimension(240, 240));
                levelLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClick(MouseEvent e) {
                        System.out.println("yeeaaah");
                    }

                    public void mouseHover(MouseEvent e) {
                        System.out.println("yeaaaaaaaaaaaaaaaaaaaah");
                    }
                });
                levelLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                levelsPanel.add(levelLabel);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        menuGameWindow.add(levelsPanel, BorderLayout.LINE_START);
		
		
		JButton backButton = new JButton("Retour");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainWindow.showMainWindow();
			}
		});
		
		menuGameWindow.add(backButton, BorderLayout.PAGE_END);
		
		return menuGameWindow;
	}

}
