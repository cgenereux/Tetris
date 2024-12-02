import Blocks.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Game extends JPanel {
    //initializing values inside of the JPanel(game window)
    private final Grid grid;
    // private final Blocks.RandomBlock randomBlock;
    private final Score score;
    private final GameSpeed gameSpeed;
    private Block currentBlock;

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
        // tetris is usually a 1:2 aspect ratio
        setPreferredSize(new Dimension(500, 1000)); // the tile images nicely downscale to 50x50
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();

        grid = new Grid();
        score = new Score();
        gameSpeed = new GameSpeed();

        Controls controls = new Controls(this);
        addKeyListener(controls);

        loadImages();


    }

    public static void main(String[] args) {
        // create the j frame
        JFrame frame = new JFrame("Game");
        // close the game via clicking the "X"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // resizing might need to be disabled but it's fine for now
        frame.setResizable(true);
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
        gamePanel.requestFocusInWindow();

        RandomBlock randomBlockGenerator = new RandomBlock();
        Block randomBlock = randomBlockGenerator.generateBlock();
        randomBlock.setCurrentPosition(4, 1);
        gamePanel.grid.placeBlock(randomBlock, 4, 1);
        gamePanel.currentBlock = randomBlock;

        gamePanel.grid.displayGrid();
        gamePanel.startGame();
    }


    public void startGame() {
        // shift the block down 1 every 1000 ms for now
        Timer timer = new Timer(1000, e -> {
            if (currentBlock != null) {
                shiftBlock(currentBlock, "down");
                System.out.println(currentBlock.getCurrentX() + " , " + currentBlock.getCurrentY());
                System.out.println("repainting");
                repaint();
            }
        });
        timer.start(); // start the timer
    }

    public void shiftBlock(Block block, String direction) {

        int newX = block.getCurrentX(); int newY = block.getCurrentY();

        if (direction.equalsIgnoreCase("left")) {
            newX -= 1;
        } else if (direction.equalsIgnoreCase("right")) {
            newX += 1;
        } else if (direction.equalsIgnoreCase("down")) {
            newY +=1;
        }

        if (grid.isWithinBounds(newX, newY)) {
            grid.removeBlock(block, block.getCurrentX(), block.getCurrentY());

            block.setCurrentPosition(newX, newY);
            grid.placeBlock(block, newX, newY);
        }

    }

    public void rotateBlock() {

        if (currentBlock == null) {
            return;
        }
        grid.removeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());
        currentBlock.rotateOnceCounterClockwise();
        grid.placeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());

        /*
        if (grid.canPlaceBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY())) {
            grid.placeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());
        } else {
            currentBlock.rotateOnceCounterClockwise();
            grid.placeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());
        }
        */


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
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 35;

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                int drawX = x * cellSize;
                int drawY = y * cellSize;

                // determine the type of cell and draw it
                char cell = grid.getCell(x, y);
                BufferedImage cellImage = getBlockImage(cell);

                g.drawImage(cellImage, drawX, drawY, cellSize, cellSize, this);
            }
        }
    }

    private BufferedImage getBlockImage(char cell) {
        return switch (cell) {
            case '#' -> gridImage; // Grid cell
            case '.' -> backGroundImage; // Background cell
            case 'O' -> oImage;
            case 'T' -> tImage;
            case 'I' -> iImage;
            case 'L' -> lImage;
            case 'J' -> jImage;
            case 'S' -> sImage;
            case 'Z' -> zImage;
            default -> oImage;
        };
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("failed to load image: " + path);
            return null;
        }
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }
}