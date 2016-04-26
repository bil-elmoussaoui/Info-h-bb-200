package View;

import Model.Player;

public class GamePanel {
    private Player player;
    private Map levelMap;

    public void setPlayer(Player player){
        this.player = player;
        //this.repaint();
    }

    /*public JPanel getJPanel(){
        JPanel playerBar = new JPanel();
        playerBar.setLayout(new GridLayout(3 , 4,  5, 5));
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
        coinsIcon.setText("x 0" );
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
        playerBar.add(new JLabel("Arm : "));
        playerBar.add(armIcon);

        playerBar.add(new JLabel("Exp : "));
        playerBar.add(new JLabel("0"));

        return playerBar;
    }*/
}
