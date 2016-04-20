package View;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BButton extends JButton {

    public BButton(String text){
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("/home/bilal/Projects/info-h-bb-200/Fonts/ARCADE.TTF")).deriveFont(22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/home/bilal/Projects/info-h-bb-200/Fonts/ARCADE.TTF")));
            this.setFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        this.setText(text);
        this.setPreferredSize(new Dimension(100, 40));
    }

}
