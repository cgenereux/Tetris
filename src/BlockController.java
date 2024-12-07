import Blocks.*;
import javax.swing.Timer;

/* code was refactored by curtis but was not all written by curtis */

public class BlockController {
    private final Grid grid;
    private final Score score;
    private final GameSpeed gameSpeed;
    private final Game game; // Reference to Game for repainting and state checks
    private Block currentBlock;
    private Block nextBlock;
    private Timer gameTimer;

    public BlockController(Grid grid, Score score, GameSpeed gameSpeed, Game game) {
        this.grid = grid;
        this.score = score;
        this.gameSpeed = gameSpeed;
        this.game = game;
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
    }

    public void setGameTimer(Timer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public void generateNextBlock() {
        RandomBlock randomBlockGenerator = new RandomBlock();
        nextBlock = randomBlockGenerator.generateBlock();
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

        currentBlock = nextBlock;
        // Generate a new nextBlock
        RandomBlock randomBlockGenerator = new RandomBlock();
        nextBlock = randomBlockGenerator.generateBlock();

        // Set the position for the new current block
        int startX = 5; // Center of the grid
        int startY = 1;
        currentBlock.setCurrentPosition(startX, startY);


        // set the block's position
        currentBlock.setCurrentPosition(startX, startY);

        // check if the new block can be placed
        if (grid.canPlaceBlock(currentBlock, startX, startY)) {
            // place the block
            grid.placeBlock(currentBlock, startX, startY);
        } else {
            // Runs when the block cannot be placed at the top indicating the game is over
            gameTimer.stop();
            game.gameOver = true;
            game.writeHighScore();
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
    }

    public void dropBlock() {
        if (currentBlock == null) return;

        // Remove block from grid to check for a valid position
        grid.removeBlock(currentBlock, currentBlock.getCurrentX(), currentBlock.getCurrentY());

        // Continuously move the block down until it can't move further
        int newY = currentBlock.getCurrentY();
        while (grid.canPlaceBlock(currentBlock, currentBlock.getCurrentX(), newY + 1)) {
            newY++;
        }

        // Finalize the block's position

        grid.placeBlock(currentBlock, currentBlock.getCurrentX(), newY);

        spawnNewBlock(); // Spawn a new block since this one is now fixed in place
    }


    // Getters for current and next blocks
    public Block getCurrentBlock() {
        return currentBlock;
    }

    public Block getNextBlock() {
        return nextBlock;
    }
}