package Main;

import Controller.Animation;
import Controller.Keyboard;
import Model.Game;
import View.MainWindow;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static Game game;
    public static MainWindow window;

    public static void main(String args[]) throws Exception {
        window = new MainWindow();
        game = new Game(window);
        Keyboard keyboard = new Keyboard(game);
        window.setKeyListener(keyboard);
        Animation animation = new Animation(game);
    }

    public static void save() {
        try {
            String fileName = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            FileOutputStream fileOut = new FileOutputStream("Data/" + fileName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.flush();
            out.close();
            fileOut.close();
        } catch (IOException i) {
        }
    }

    public static void load(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream("Data/" + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            game.window = window;
            game.window.showLevelWindow();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }

    }


}

