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
    private static MainWindow window;
    private static Animation animation;
    private static Keyboard keyboard;

    //intialisation du jeu
    public static void main(String args[]) throws Exception {
        window = new MainWindow();
        game = new Game(window);
        keyboard = new Keyboard(game);
        window.setKeyListener(keyboard);
        animation = new Animation(game);
        window.setAnimation(animation);
        // observer utilisé pour arreter les threads durant l'arret du jeu
        window.attach(animation);
        window.showMenuWindow();
    }

    // sauvgarde de la partie
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
            i.printStackTrace();
        }
    }

    // reprise de la partie
    public static void load(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream("Data/" + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println(game.getMonsters().size());
            game = (Game) in.readObject();
            game.setWindow(window);
            window.setAnimation(animation);
            window.setKeyListener(keyboard);
            game.refreshImages();
            game.window.showLevelWindow();
            animation.initThreads();
            game.refreshMap();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }

    }

}

