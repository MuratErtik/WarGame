
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameScreen extends JFrame {

    public GameScreen(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        
        GameScreen gameScreen = new GameScreen("GAME WAR");
        gameScreen.setResizable(false);
        gameScreen.setFocusable(false);
        gameScreen.setSize(800,600);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game game = new Game();
        game.requestFocus();
        game.addKeyListener(game);
        
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        
        gameScreen.add(game);
        
        gameScreen.setVisible(true);
    }

}
