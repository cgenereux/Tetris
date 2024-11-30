import Blocks.Block;

public class Grid {

    private final int height = 20;
    private final int width = 10;

    private char[][] grid;

    // initialize the grid
    public Grid() {
        grid = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // set border cells to border tiles: '#'
                if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
                    grid[y][x] = '#';
                } else { // otherwise empty cells are: '.'
                    grid[y][x] = '.';
                }
            }
        }
    }

    // displays game grid
    public void displayGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x] + " ");  // represent blocks with grid[y][x]
            }
            System.out.println("\n");
        }
    }

    public boolean placeBlock(Block block, int startX, int startY) {
        // check if all the tiles of the block can be placed without collision
        for (int[] coordinate : block.getShape()) {
            int x = startX + coordinate[0];
            int y = startY + coordinate[1];
            if (!isWithinBounds(x, y) || grid[y][x] != '.') {
                return false; // a tile can't be placed
            }
        }

        // place the block since we know it's valid
        for (int[] coordinate : block.getShape()) {
            int x = startX + coordinate[0];
            int y = startY + coordinate[1];
            grid[y][x] = block.getTypeID();
        }

        return true; // the block was successfully placed
    }

    // bounds check
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean canPlaceBlock(Block block, int startX, int startY) {
        for (int[] coordinate : block.getShape()) {
            int x = startX + coordinate[0];
            int y = startY + coordinate[1];
            if (!isWithinBounds(x, y) || grid[y][x] != '.') {
                return false;
            }
        }
        return true;
    }

    public void removeBlock(Block block, int startX, int startY) {
        for (int[] coordinate : block.getShape()) {
            int x = startX + coordinate[0];
            int y = startY + coordinate[1];
            if (isWithinBounds(x, y) && grid[y][x] == block.getTypeID()) {
                grid[y][x] = '.';
            }
        }
    }

    // getters for grid dimensions
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
