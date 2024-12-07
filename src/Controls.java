import java.awt.event.*;
import Blocks.*;

public class Controls extends KeyAdapter { //receive key inputs
    private final Game game;

    public Controls(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Block block = game.getBlockController().getCurrentBlock();

        if (block == null) {
            return;
        }
        int key = e.getKeyCode();

        // Allows the start screen to go away when space key is pressed on start screen
        if (game.onStartScreen && key == KeyEvent.VK_SPACE) {
            game.onStartScreen = false;
            return;
        }

        switch (key) {
            case KeyEvent.VK_LEFT:
                game.shiftBlock(block, "left");
                game.repaint();
                break;
            case KeyEvent.VK_RIGHT:
                game.shiftBlock(block, "right");
                game.repaint();
                break;
            case KeyEvent.VK_DOWN: //move down
                game.shiftBlock(block, "down");
                break;
            case KeyEvent.VK_UP:
                game.rotateBlock();
                game.repaint();
                break;
            // drop
            case KeyEvent.VK_SPACE:
                game.dropBlock();
                break;

            default:
                break;
        }
        game.repaint();
    }
}