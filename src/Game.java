import Blocks.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Game extends JPanel {

    private final Grid grid;
    private final Score score;
    private final GameSpeed gameSpeed;
    private final Renderer renderer;
    private final BlockController blockController;
    private boolean onStartScreen = true;
    private boolean isGameOver = false;
    private int highScore = 0; // Default 0 in case file is modified by user to be blank which would cause errors
    private Timer gameTimer;

    public Game() {
        // Tetris is usually a 1:2 aspect ratio
        // + 180 for the right side black space
        setPreferredSize(new Dimension(540 + 180, 990));

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

        //Sets the highscore variable
        gamePanel.readHighScore();

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

    public void readHighScore() {

        try {
            File file = new File("data/highscore.txt");

            Scanner sc = new Scanner(file);

            highScore = sc.nextInt();
            System.out.println("High score: " + highScore);

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    public void writeHighScore() {

       //Set the new alltime high score if needed
       if (score.getScore() > highScore) {
           try {
               System.out.println("Writing");
               File file = new File("data/highscore.txt");
               PrintWriter pw = new PrintWriter(file);

               pw.print(score.getScore());
               pw.close();

           } catch (FileNotFoundException e) {
               e.printStackTrace();

           }
        }
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
        renderer.render(g, onStartScreen, isGameOver);
    }

    public BlockController getBlockController() {
        return blockController;
    }

    public boolean isStartScreen() {
        return onStartScreen;
    }

    public void setStartScreen(boolean onStartScreen) {
        this.onStartScreen = onStartScreen;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
