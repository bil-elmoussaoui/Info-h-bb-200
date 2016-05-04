package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BButton extends JButton {

    public BButton(String text) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/ARCADE.TTF")).deriveFont(22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/ARCADE.TTF")));
            this.setFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        this.setText(text);
        this.setPreferredSize(new Dimension(200, 50));
    }

}
