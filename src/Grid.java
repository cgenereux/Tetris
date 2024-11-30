public class Grid {

    private final int height = 20;
    private final int width = 10;

    private int[][] grid;

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

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}
