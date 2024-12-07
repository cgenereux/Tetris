import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Objects;
import Blocks.*;

/* code was refactored by curtis but was not all written by curtis */

public class Renderer {
    private final Game game;
    private final Grid grid;
    private final Score score;
    private final BlockController blockController;

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

    public Renderer(Game game, Grid grid, Score score, BlockController blockController) {
        this.game = game;
        this.grid = grid;
        this.score = score;
        this.blockController = blockController;
        loadImages();
    }

    public void render(Graphics g, boolean onStartScreen, boolean gameOver) {
        // draw the start screen
        if (onStartScreen) {
            g.drawImage(startScreenImage, 0, 0, game.getWidth(), game.getHeight(), game);
            return;
        }
        // draw the end screen
        if (gameOver) {
            g.drawImage(endScreenImage, 0, 0, game.getWidth(), game.getHeight(), game);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g.drawString("Your Score: " + score.getScore(), 230, 350);
            return;
        }

        int cellSize = 45;

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                int drawX = x * cellSize;
                int drawY = y * cellSize;
                char cell = grid.getCell(x, y);
                BufferedImage cellImage = getBlockImage(cell);
                g.drawImage(cellImage, drawX, drawY, cellSize, cellSize, game);
            }
        }

        // draw the score
        int scoreX = grid.getWidth() * cellSize + 20;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("Score: ", scoreX, 50);
        g.drawString(String.valueOf(score.getScore()), scoreX, 70);

        Block nextBlock = blockController.getNextBlock();
        if (nextBlock != null) {
            g.drawString("Next Block: ", scoreX, 150);
            int previewX = scoreX;
            int previewY = 170;
            for (int[] coordinate : nextBlock.getShape()) {
                int x = previewX + (coordinate[0] * cellSize);
                int y = previewY + (coordinate[1] * cellSize);
                BufferedImage blockImage = getBlockImage(nextBlock.getTypeID());
                g.drawImage(blockImage, x, y, cellSize, cellSize, game);
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

    private void loadImages() {
        startScreenImage = loadImage("img/start-screen.png");
        endScreenImage = loadImage("img/end-screen.png");
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

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (Exception e) {
            System.err.println("Failed to load image: " + path);
            return null;
        }
    }
}
