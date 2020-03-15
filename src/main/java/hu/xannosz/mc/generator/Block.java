package hu.xannosz.mc.generator;

public enum Block {
    AIR, BEDROCK, GOLD_BLOCK, GRAVEL, WATER;

    public static Block getBlock(String token) {
        for (Block block : Block.values()) {
            if (block.toString().equals(token)) {
                return block;
            }
        }
        return AIR;
    }

    @Override
    public String toString() {
        return "minecraft:" + super.toString().toLowerCase();
    }
}