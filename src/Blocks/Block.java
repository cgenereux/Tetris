package Blocks;

public class Block {

    private final int[][] shape; // it's relative coordinates. so like: {{0, 0}, {1, 0}, etc}}
    private final String color;
    private final int uniqueId;
    private final char typeId;
    private static int uniqueIdCounter = 1;
    private int currentX;
    private int currentY;
    private int rotationState;

    public Block(int[][] shape, char typeId, String color) {
        this.typeId = typeId;
        this.uniqueId = uniqueIdCounter++;
        this.shape = shape;
        this.color = color;
        this.currentX = 0;
        this.currentY = 0;
        this.rotationState = 0;

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

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentPosition(int currentX, int currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public void setRotationState(int rotationState) {
        if (rotationState >= 3) {
            this.rotationState = 0;
        }
        this.rotationState = rotationState+1;
    }

    public void rotateOnceCounterClockwise() {
        for (int[] coordinate : shape) {
            int x = coordinate[0];
            int y = coordinate[1];
            coordinate[0] = -y;
            coordinate[1] = x;
        }
        setRotationState(rotationState - 1);
    }

}
