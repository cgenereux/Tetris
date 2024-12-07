import Blocks.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Game extends JPanel {

    private final Grid grid;
    private final Score score;
    private final GameSpeed gameSpeed;
    private final BlockController blockController;
    public boolean onStartScreen = true;
    public boolean gameOver = false;
    private Timer gameTimer;

    private BufferedImage startScreenImage;
    private BufferedImage endScreenImage;
    private BufferedImage gridImage;
    private BufferedImage backGroundImage;
    private BufferedImage oImage;
    private BufferedImage tImage;
    private BufferedImage iImage;
    private BufferedImage lImage;
    private BufferedImage jImage;
    private BufferedImage sImage;
    private BufferedImage zImage;

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

        Controls controls = new Controls(this);
        addKeyListener(controls);

        loadImages();
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

    // Getter to check if the game is on the start screen
    public boolean isOnStartScreen() {
        return onStartScreen;
    }

    private void loadImages() {
        gridImage = loadImage("img/grid.png");
        backGroundImage = loadImage("img/background.png");
        oImage = loadImage("img/O.png");
        tImage = loadImage("img/T.png");
        iImage = loadImage("img/I.png");
        lImage = loadImage("img/L.png");
        jImage = loadImage("img/J.png");
        sImage = loadImage("img/S.png");
        zImage = loadImage("img/Z.png");
        startScreenImage = loadImage("img/start-screen.png");
        endScreenImage = loadImage("img/end-screen.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Determines if the player is on the start screen to draw the image
        if (onStartScreen) {
            // Draw the start screen
            g.drawImage(startScreenImage, 0, 0, getWidth(), getHeight(), this);
            return;
        }
        // Displays the end screen for the game
        if (gameOver) {
            // Draw the end screen
            g.drawImage(endScreenImage, 0, 0, getWidth(), getHeight(), this);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 40));

            g.drawString("Your Score: " + String.valueOf(score.getScore()), 230, 350);
            return;
        }

        int cellSize = 45;

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                int drawX = x * cellSize;
                int drawY = y * cellSize;

                // Determine the type of cell and draw it
                char cell = grid.getCell(x, y);
                BufferedImage cellImage = getBlockImage(cell);

                g.drawImage(cellImage, drawX, drawY, cellSize, cellSize, this);
            }
        }

        // Draw score at the side of the screen
        int scoreX = grid.getWidth() * cellSize + 20; // Positioning for right side of grid
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        g.drawString("Score: ", scoreX, 50);
        g.drawString(String.valueOf(score.getScore()), scoreX, 70);

        // Draw next block
        Block nextBlock = blockController.getNextBlock();
        if (nextBlock != null) {
            g.drawString("Next Block: ", scoreX, 150);
            int previewX = scoreX;
            int previewY = 170;

            for (int[] coordinate : nextBlock.getShape()) {
                int x = previewX + (coordinate[0] * cellSize);
                int y = previewY + (coordinate[1] * cellSize);
                BufferedImage blockImage = getBlockImage(nextBlock.getTypeID());
                g.drawImage(blockImage, x, y, cellSize, cellSize, this);
            }
        }
    }

    private BufferedImage getBlockImage(char cell) {
        return switch (cell) {
            case '#' -> gridImage;
            case '.' -> backGroundImage;
            case 'O' -> oImage;
            case 'T' -> tImage;
            case 'I' -> iImage;
            case 'L' -> lImage;
            case 'J' -> jImage;
            case 'S' -> sImage;
            case 'Z' -> zImage;
            default -> null;
        };
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Failed to load image: " + path);
            return null;
        }
    }

    public BlockController getBlockController() {
        return blockController;
    }

    public void setOnStartScreen(boolean onStartScreen) {
        this.onStartScreen = onStartScreen;
    }
}
