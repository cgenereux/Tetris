import javax.swing.*;

public class Game extends JPanel {
    //initializing values inside of the JPanel(game window)
    private final Grid grid;
    private final randomBlock randomBlock;
    private final Score score;
    private final GameSpeed gameSpeed;
    private Block currentBlock;

    public Game() {
        grid = new Grid(20, 10); //size of grid

    }

    public void moveLeft(){}
    public void moveRight(){}
    public void moveDown(){}
    public void dropBlock(){}
    public void rotateBlock(){}


    public static void main(String[] args) {


    }
}