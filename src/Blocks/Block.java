package Blocks;

public class Block {

    private final int[][] shape; // it's relative coordinates. so like: {{0, 0}, {1, 0}, etc}}

    private final char typeId;
    private static int uniqueIdCounter = 1;
    private int currentX;
    private int currentY;
    private final int pivotX;
    private final int pivotY;

    public Block(int[][] shape, char typeId, String color) {
        this.typeId = typeId;
        this.shape = shape;
        this.currentX = 0;
        this.currentY = 0;
        this.pivotX = shape[1][0];
        this.pivotY = shape[1][1];
    }

    public int[][] getShape() {
        return shape;
    }

    public char getTypeID() {
        return typeId;
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

    public void rotateOnceClockwise() {
        int[][] newShape = new int[shape.length][2];
        for (int i = 0; i < shape.length; i++) {
            int x = shape[i][0] - pivotX;
            int y = shape[i][1] - pivotY;

            int rotatedX = y;
            int rotatedY = -x;

            newShape[i][0] = rotatedX + pivotX;
            newShape[i][1] = rotatedY + pivotY;
        }
        // replace the old shape with the new one
        for (int i = 0; i < shape.length; i++) {
            shape[i][0] = newShape[i][0];
            shape[i][1] = newShape[i][1];
        }
    }

    public void setShape(int[][] newShape) {
        for (int i = 0; i < shape.length; i++) {
            shape[i][0] = newShape[i][0];
            shape[i][1] = newShape[i][1];
        }
    }
}
