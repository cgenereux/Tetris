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

    public String getColor(char typeID) {
        return switch (typeID) {
            case 'I' -> "Cyan";
            case 'O' -> "Yellow";
            case 'J' -> "Purple";
            case 'L' -> "Orange";
            case 'S' -> "Green";
            case 'Z' -> "Red";
            case 'T' -> "Magenta";
            default -> "White"; // use white if it's an unknown shape
        };
    }

}
