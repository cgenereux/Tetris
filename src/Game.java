import Blocks.*;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    //initializing values inside of the JPanel(game window)
    private final Grid grid;
    // private final Blocks.RandomBlock randomBlock;
    private final Score score;
    private final GameSpeed gameSpeed;
    private Block currentBlock;
    private int currentX;
    private int currentY;

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

        // testing displaying a regular o block
        OBlock oBlock = new OBlock();
        IBlock iBlock = new IBlock();
        JBlock jBlock = new JBlock();
        LBlock lBlock = new LBlock();

        // very ugly i know; they should be merged into 1 coordinate to represent x and y
        // this is also just for testing
        oBlock.setCurrentX(0);
        oBlock.setCurrentY(0);
        iBlock.setCurrentX(5);
        iBlock.setCurrentY(0);
        jBlock.setCurrentX(0);
        jBlock.setCurrentY(10);
        lBlock.setCurrentX(0);
        lBlock.setCurrentY(14);

        gamePanel.grid.placeBlock(oBlock, oBlock.getCurrentX(), oBlock.getCurrentY());
        gamePanel.grid.placeBlock(iBlock, iBlock.getCurrentX(), iBlock.getCurrentY());
        gamePanel.grid.placeBlock(jBlock, jBlock.getCurrentX(), jBlock.getCurrentY());
        gamePanel.grid.placeBlock(lBlock, lBlock.getCurrentX(), lBlock.getCurrentY());


        gamePanel.shiftBlock(oBlock, "right");
        gamePanel.shiftBlock(iBlock, "down");
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

            block.setCurrentX(newX);
            block.setCurrentY(newY);

            grid.placeBlock(block, newX, newY);
        }

    }

    public void dropBlock() {}

    public void rotateBlock() {}

    public void startGame() {}

}