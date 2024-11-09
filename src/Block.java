public class Block {

    private final int[][] shape; // it's relative coordinates. so like: {{0, 0}, {1, 0}, etc}}
    private final int id;
    private final String color;

    public Block(int[][] shape, int id, String color) {
        this.shape = shape;
        this.id = id;
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

}
