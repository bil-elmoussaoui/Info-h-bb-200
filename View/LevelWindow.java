package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

class LevelWindow{
    private JPanel levelMap;

    public LevelWindow(JPanel levelMap){
        this.levelMap = levelMap;
    }

    public JPanel getJPanel(){
        JPanel levelWindow = new JPanel(new BorderLayout());
        levelWindow.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 10));
        JPanel playerBar = new JPanel();
        playerBar.setLayout(new GridLayout(3 , 8,  5, 5));
        playerBar.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        JLabel heartIcon = new JLabel();
        heartIcon.setLayout(new BoxLayout(heartIcon, BoxLayout.X_AXIS));
        for(int i = 0; i < 5; i++) {
            JLabel heartLabel = new JLabel();
            heartLabel.setIcon(new ImageIcon("Images/heart.png"));
            heartIcon.add(heartLabel);
        }
        playerBar.add(new JLabel("Health : "));
        playerBar.add(heartIcon);

        JLabel armorIcon = new JLabel();
        armorIcon.setLayout(new BoxLayout(armorIcon, BoxLayout.X_AXIS));
        for(int i = 0; i < 3; i++) {
            JLabel armorLabel = new JLabel();
            armorLabel.setIcon(new ImageIcon("Images/Armor.png"));
            armorIcon.add(armorLabel);
        }
        playerBar.add(new JLabel("Armor : "));
        playerBar.add(armorIcon);

        JLabel coinsIcon = new JLabel();
        coinsIcon.setIcon(new ImageIcon("Images/coin1.png"));
        coinsIcon.setText("x 30");
        playerBar.add(new JLabel("Coins : "));
        playerBar.add(coinsIcon);

        JLabel keysIcon = new JLabel();
        keysIcon.setLayout(new BoxLayout(keysIcon, BoxLayout.X_AXIS));
        for(int i = 0; i < 3; i++) {
            JLabel keysLabel = new JLabel();
            keysLabel.setIcon(new ImageIcon("Images/Key.png"));
            keysIcon.add(keysLabel);
        }
        playerBar.add(new JLabel("Keys : "));
        playerBar.add(keysIcon);

        JLabel armIcon = new JLabel();
        armIcon.setIcon(new ImageIcon("Images/sword.png"));
        playerBar.add(new JLabel("Weapon : "));
        playerBar.add(armIcon);

        playerBar.add(new JLabel("Exp : "));
        playerBar.add(new JLabel("12030"));

        levelWindow.add(playerBar, BorderLayout.PAGE_END);
        levelWindow.add(this.levelMap, BorderLayout.CENTER);
        return levelWindow;
    }


}
