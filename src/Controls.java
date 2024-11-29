import java.awt.event.*;


public class Controls extends KeyAdapter{ //receive key inputs
    private Game game;

    public Controls(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: game.rotateBlock();
            case KeyEvent.VK_RIGHT: game.moveRight();
            case KeyEvent.VK_LEFT: game.moveLeft();
            case KeyEvent.VK_DOWN: game.moveDown();
            case KeyEvent.VK_SPACE: game.dropBlock();
        }
    }


}
