import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
    //initializing values inside of the JPanel(game window)
    private final Grid grid;
    // private final RandomBlock randomBlock;
    private final Score score;
    private final GameSpeed gameSpeed;
    private Block currentBlock;

    public Game() {
        // tetris is usually a 1:2 aspect ratio
        setPreferredSize(new Dimension(540, 1080)); // example dimensions
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();

        grid = new Grid();
        score = new Score();
        gameSpeed = new GameSpeed();

        Controls controls = new Controls(this);
        addKeyListener(controls);

        startGame();
    }

    public static void main(String[] args) {
        // create the j frame
        JFrame frame = new JFrame("Game");
        // close the game via clicking the "X"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // don't let the user resize the window because it could lead to dimension issues for now
        frame.setResizable(false);
        // create an instance of the game class
        Game gamePanel = new Game();
        // add game to the jframe
        frame.add(gamePanel);
        // make the frame fit the game dimensions
        frame.pack();
        // center the jframe
        frame.setLocationRelativeTo(null);
        // make it visible
        frame.setVisible(true);
    }

    public void moveLeft() {}

    public void moveRight() {}

    public void moveDown() {}

    public void dropBlock() {}

    public void rotateBlock() {}

    public void startGame() {}

}