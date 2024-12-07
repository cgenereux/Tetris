import Blocks.*;
import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    private final Grid grid;
    private final Score score;
    private final GameSpeed gameSpeed;
    private final Renderer renderer;
    private final BlockController blockController;
    public boolean onStartScreen = true;
    public boolean gameOver = false;
    private Timer gameTimer;

    public Game() {
        // Tetris is usually a 1:2 aspect ratio
        setPreferredSize(new Dimension(540 + 180, 990));
        // + 180 for the right side black space

        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();

        grid = new Grid();
        score = new Score();
        gameSpeed = new GameSpeed();
        blockController = new BlockController(grid, score, gameSpeed, this);
        renderer = new Renderer(this, grid, score, blockController);

        Controls controls = new Controls(this);
        addKeyListener(controls);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game"); // Create the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the game via clicking the "X"
        frame.setResizable(true); // Create an instance of the game class
        Game gamePanel = new Game(); // Add game to the JFrame
        frame.add(gamePanel); // Make the frame fit the game dimensions
        frame.pack(); // Center the JFrame
        frame.setLocationRelativeTo(null); // Make it visible
        frame.setVisible(true);
        gamePanel.requestFocusInWindow();

        gamePanel.startGame();
    }

    public void startGame() {
        // Initialize the game
        blockController.generateNextBlock();
        blockController.spawnNewBlock();

        gameTimer = new Timer(gameSpeed.getCurrentSpeed(), e -> {
            if (!onStartScreen) {
                blockController.shiftBlock(blockController.getCurrentBlock(), "down");
                repaint();
            }
        });
        blockController.setGameTimer(gameTimer);
        gameTimer.start();
    }

    public void shiftBlock(Block block, String direction) {
        blockController.shiftBlock(block, direction);
    }

    public void rotateBlock() {
        blockController.rotateBlock();
    }

    public void dropBlock() {
        blockController.dropBlock();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g, onStartScreen, gameOver);
    }

    public BlockController getBlockController() {
        return blockController;
    }

}
