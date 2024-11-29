import java.util.Random;

public class RandomBlock {
    private static final int[][][] SHAPES = {
            {{1,1,1,1}},        // I
            {{1,1}, {1,1}},     // O
            {{1,1,1}, {0,0,1}}, // J
            {{1,1,1}, {1,0,0}}, // L
            {{1,1,0},{0,1,1}},  // S
            {{0,1,1},{1,1,0}},  // Z
            {{0,1,0},{1,1,1}},  // T
    };

    private final Random random = new Random();


    public RandomBlock() {}

    public Block generateBlock() {}
}
