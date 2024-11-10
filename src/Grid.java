public class Grid {

    private final int height = 20;
    private final int width = 10;

    private int[][] grid; // maybe map is a better name guys idrk

    public Grid() {
        grid = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = 0;
            }
        }
    }

    //Displays game grid
    public void displayGrid() {
        grid = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.println(grid[x][y] +  " ");
            }
            System.out.println("\n");
        }
    }

}
