import Blocks.Block;

public class Grid {

    private final int height = 20;
    private final int width = 10;

    private int[][] grid;

    // initialize the grid
    public Grid() {
        grid = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = 0;
            }
        }
    }

    // displays game grid
    public void displayGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == 0) {
                    System.out.print(". "); // represent empty cells with a "."
                } else {
                    System.out.print(grid[y][x] + " ");  // represent blocks with grid[y][x]
                }
            }
            System.out.println("\n");
        }
    }

    // the name for shape might actually be block
    public boolean placeShape(Block block, int startX, int startY) {
        // check if all the blocks of the shape can be placed without collision
        for (int[] coordinate : block.getShape()) {
            int x = startX + coordinate[0];
            int y = startY + coordinate[1];
            if (!isWithinBounds(x, y) || grid[y][x] != 0) {
                return false; // a block can't be placed
            }
        }

        // place the shape since we know it's valid
        for (int[] coordinate : block.getShape()) {
            int x = startX + coordinate[0];
            int y = startY + coordinate[1];
            grid[y][x] = block.getId();
        }

        return true; // the shape was successfully placed
    }

    // bounds check
    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    // getters for grid dimensions
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
}
