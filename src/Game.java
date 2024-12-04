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
    private Timer gameTimer;


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
        setPreferredSize(new Dimension(540+180, 990)); // the tile images nicely downscale to 50x50
        //put extra width for score and next block section section 540 -> 720

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

        gamePanel.startGame();
    }


    public void startGame() {
        spawnNewBlock();

        gameTimer = new Timer(gameSpeed.getCurrentSpeed(), e -> {
            if (currentBlock != null) {
                shiftBlock(currentBlock, "down");
                repaint();
            }
        });
        gameTimer.start(); // start the timer
    }


    public void shiftBlock(Block block, String direction) {
        int newX = block.getCurrentX();
        int newY = block.getCurrentY();

        if (direction.equalsIgnoreCase("left")) {
            newX -= 1;
        } else if (direction.equalsIgnoreCase("right")) {
            newX += 1;
        } else if (direction.equalsIgnoreCase("down")) {
            newY += 1;
        }

        // remove the current block
        grid.removeBlock(block, block.getCurrentX(), block.getCurrentY());

        // check if the intended new position is valid
        if (grid.canPlaceBlock(block, newX, newY)) {
            // update and place the block
            block.setCurrentPosition(newX, newY);
            grid.placeBlock(block, newX, newY);
        } else {
            if (direction.equalsIgnoreCase("down")) {
                grid.placeBlock(block, block.getCurrentX(), block.getCurrentY());
                spawnNewBlock();
            } else {
                // if not revert the change
                grid.placeBlock(block, block.getCurrentX(), block.getCurrentY());
            }
        }
        repaint();
    }


    public void spawnNewBlock() {
        System.out.println("spawning new block");
        int linesCleared = grid.clearFullRows(); //get the linesCleared
        if (linesCleared > 0) {
            score.addPoints(linesCleared);
            System.out.println("Score: " + score.getScore());

            gameSpeed.updateSpeed(score.getScore());
            gameTimer.setDelay(gameSpeed.getCurrentSpeed());
        }


        // generate a new random block
        RandomBlock randomBlockGenerator = new RandomBlock();
        Block newBlock = randomBlockGenerator.generateBlock();
        int startX = 5; // about the center
        int startY = 1;

        // set the block's position
        newBlock.setCurrentPosition(startX, startY);

        // check if the new block can be placed
        if (grid.canPlaceBlock(newBlock, startX, startY)) {
            // place the block
            grid.placeBlock(newBlock, startX, startY);
            currentBlock = newBlock;
        } else {
            // if not game over
            gameTimer.stop();
            System.out.println("Game over, Final Score: " + score.getScore());
        }
    }

    public void rotateBlock() {
        // the o block shouldn't rotate
        if (currentBlock == null || currentBlock.getTypeID() == 'O') {
            return;
        }

        // remove the block from the grid before rotation
        grid.removeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());

        // make a copy of the block's shape
        int[][] originalShape = new int[currentBlock.getShape().length][2];
        for (int i = 0; i < currentBlock.getShape().length; i++) {
            originalShape[i][0] = currentBlock.getShape()[i][0];
            originalShape[i][1] = currentBlock.getShape()[i][1];
        }

        currentBlock.rotateOnceClockwise();

        // check if the rotated block can be placed at the current position
        if (grid.canPlaceBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY())) {
            grid.placeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());
        } else {
            // if the rotation is invalid revert it
            currentBlock.setShape(originalShape);
            grid.placeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());
        }
        repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 45;

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

        //draw score at side of screen
        int scoreX = grid.getWidth() * cellSize + 20; //positioning for right side of grid
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        g.drawString("Score: ", scoreX, 50);
        g.drawString(String.valueOf(score.getScore()), scoreX, 70);


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


    // doesn't work
    public void dropBlock() {
        if (currentBlock == null) {
            return;
        }

        int currentX = currentBlock.getCurrentX();
        int currentY = currentBlock.getCurrentY();

        while(grid.canPlaceBlock(currentBlock, currentX, currentY + 1)) {
            currentY++; //drops block until canPlaceBlock = false
        }

        grid.placeBlock(currentBlock, currentX, currentY);
        spawnNewBlock();
        repaint();
    }


}