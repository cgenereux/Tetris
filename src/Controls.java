import java.awt.event.*;
import Blocks.*;

public class Controls extends KeyAdapter{ //receive key inputs
    private Game game;

    public Controls(Game game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e, Block block) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: game.rotateBlock();
            case KeyEvent.VK_RIGHT: game.shiftBlock(block, "right");
            case KeyEvent.VK_LEFT: game.shiftBlock(block,"left");
            case KeyEvent.VK_DOWN: game.shiftBlock(block, "down");
            case KeyEvent.VK_SPACE: game.dropBlock();
        }
    }
}
