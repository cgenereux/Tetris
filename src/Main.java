public class Main {
    public static void main(String[] args) {

        // creating the grid instance
        Grid grid = new Grid();

        // defining the o shape and it's properties
        int[][] oShape = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        Block oBlock = new Block(oShape, 1, "Red");

    }
}