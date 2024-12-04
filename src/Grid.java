import Blocks.Block;

public class Grid {

    private final int height = 22;
    private final int width = 12;

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

    public char getCell(int x, int y) {
        return grid[y][x];
    }

    // bounds check
    public boolean isWithinBounds(int x, int y) {
        return x >= 1 && x < width - 1 && y >= 1 && y < height - 1;
    }

    public boolean canPlaceBlock(Block block, int startX, int startY) {
        for (int[] coordinate : block.getShape()) {
            int x = startX + coordinate[0];
            int y = startY + coordinate[1];
            if (grid[y][x] != '.') {
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

    public int clearFullRows(){ //clearing the row
        int linesCleared = 0;

        for (int y = height - 2; y > 0; y--) { //-2 for boundaries
            if (isFullRow(y)) {
                clearRow(y);
                linesCleared++;
                y++;
            }
        }

        return linesCleared;
    }

    private boolean isFullRow(int row) {
        for (int x = 1; x < width - 1; x++) {
            if (grid[row][x] == '.') {
                return false; //row not full
            }
        }
        return true;
    }

    public void clearRow(int row){//the method to clear the row
       System.out.println("clearing row: " + row); //debug
        for (int y = row; y > 0; y--) {
            for (int x = 1; x < width; x++) {
                grid[y][x] = grid[y - 1][x]; //shift downward
            }
        }

        for (int x = 1; x < width - 1; x++) {
            grid[0][x] = '.';
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
