import java.awt.event.*;
import Blocks.*;

public class Controls extends KeyAdapter { //receive key inputs
    private final Game game;

    public Controls(Game game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {

        Block block = game.getCurrentBlock();

        if (block == null) {
            return;
        }
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                game.shiftBlock(block, "left");
                game.repaint();
                break;
            case KeyEvent.VK_RIGHT:
                game.shiftBlock(block, "right");
                game.repaint();
                break;
            case KeyEvent.VK_UP:
                game.rotateBlock();
                game.repaint();
                break;
            default:
                break;
        }
    }
}
