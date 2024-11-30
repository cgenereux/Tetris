package Blocks;

public class Block {

    private final int[][] shape; // it's relative coordinates. so like: {{0, 0}, {1, 0}, etc}}
    private final String color;
    private final int uniqueId;
    private final char typeId;
    private static int uniqueIdCounter = 1;

    public Block(int[][] shape, char typeId, String color) {
        this.typeId = typeId;
        this.uniqueId = uniqueIdCounter++;
        this.shape = shape;
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public char getTypeID() {
        return typeId;
    }

    public int getUniqueID() {
        return uniqueId;
    }

    public String getColor() {
        return color;
    }

}
