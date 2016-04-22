import Controller.Animation;
import Model.Game;
import Controller.Keyboard;
import View.MainWindow;

public class Main {

    public static void main(String args[]) throws Exception {
        MainWindow window = new MainWindow();
        Game game = new Game(window);
        Keyboard keyboard = new Keyboard(game);
        MainWindow.setKeyListener(keyboard);
        Animation animation = new Animation(game);
    }
}
