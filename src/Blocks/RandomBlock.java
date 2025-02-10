package Blocks;

import java.util.Random;

public class RandomBlock {

    private final Random random = new Random();

    // pick a random block
    public Block generateBlock() {
        int blockType = random.nextInt(7);
        return switch (blockType) {
            case 1 -> new OBlock();
            case 2 -> new JBlock();
            case 3 -> new LBlock();
            case 4 -> new SBlock();
            case 5 -> new ZBlock();
            case 6 -> new TBlock();
            default -> new IBlock();
        };
    }
}
