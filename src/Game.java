import Blocks.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel {
    //initializing values inside of the JPanel(game window)
    private final Grid grid;
    // private final Blocks.RandomBlock randomBlock;
    private final Score score;
    private final GameSpeed gameSpeed;
    private Block currentBlock;
    private int currentX;
    private int currentY;

    private BufferedImage gridImage;
    private BufferedImage blockImage;
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
        startGame();
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


        // this is just for testing
        /*
        OBlock oBlock = new OBlock();
        IBlock iBlock = new IBlock();
        JBlock jBlock = new JBlock();
        LBlock lBlock = new LBlock();
        SBlock sBlock = new SBlock();
        TBlock tBlock = new TBlock();
        ZBlock zBlock = new ZBlock();

        oBlock.setCurrentPosition(1, 1);
        iBlock.setCurrentPosition(3, 3);

        gamePanel.grid.placeBlock(jBlock, 5, 5);
        gamePanel.grid.placeBlock(oBlock, 1, 1);
        gamePanel.grid.placeBlock(iBlock, 3, 3);
        gamePanel.grid.placeBlock(zBlock, 1, 10);
        gamePanel.grid.placeBlock(sBlock, 5, 10);
        gamePanel.grid.placeBlock(tBlock, 1, 15);
        gamePanel.grid.placeBlock(lBlock, 6, 15);

        gamePanel.shiftBlock(oBlock, "right");
        gamePanel.shiftBlock(iBlock, "down");
         */

        RandomBlock randomBlockGenerator = new RandomBlock();
        Block randomBlock = randomBlockGenerator.generateBlock();
        gamePanel.grid.placeBlock(randomBlock, 4, 1);

        gamePanel.grid.displayGrid();

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
            System.out.println(true);
            grid.removeBlock(block, block.getCurrentX(), block.getCurrentY());

            block.setCurrentPosition(newX, newY);
            grid.placeBlock(block, newX, newY);
        }

    }

    private void loadImages() {
        gridImage = loadImage("img/grid.png");
        blockImage = loadImage("img/block.png");
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

        int cellSize = 50;

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
            default -> blockImage;
        };
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("failed to load image: " + path);
            e.printStackTrace();
            return null;
        }
    }

    public void dropBlock() {}

    public void rotateBlock() {}

    public void startGame() {}

}