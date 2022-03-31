import javax.swing.JFrame;
import java.awt.HeadlessException;
public class GameScreen extends JFrame{
    public GameScreen(String title) throws HeadlessException
    {
        super(title);
    }
    public static void main(String[] args)
    {
        GameScreen screen = new GameScreen("Space Game");

        screen.setResizable(false);
        //Game will be on JPanel not JFrame so JFrame should be notFocusable
        screen.setFocusable(false);

        screen.setSize(800, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        /*Do not break the order of this listeners
        keyword may not work if you change the place 
        of the lines.
        */
        //To get the keyword
        game.requestFocus();
        //keyListener is an interface. Listen game
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);

        screen.add(game);
        //JFrame will be formed when the Jpanel is formed
        screen.setVisible(true);
    }
}
