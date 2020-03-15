package hu.xannosz.mc.generator;

import java.util.HashMap;
import java.util.Map;

public class BlockVoxel implements Voxel{
    private Map<Integer, Map<Integer, Map<Integer, Block>>> blocks = new HashMap<>();

    public boolean setBlock(int x, int y, int z, Block block) {
        if (y > 255 || y < 0) {
            return false;
        }
        if (!blocks.containsKey(x)) {
            blocks.put(x, new HashMap<>());
        }
        if (!blocks.get(x).containsKey(y)) {
            blocks.get(x).put(y, new HashMap<>());
        }
        blocks.get(x).get(y).put(z, block);

        return true;
    }

    public Block getBlock(int x, int y, int z) {
        if (!blocks.containsKey(x)) {
            return Block.AIR;
        }
        if (!blocks.get(x).containsKey(y)) {
            return Block.AIR;
        }
        if (!blocks.get(x).get(y).containsKey(z)) {
            return Block.AIR;
        }
        return blocks.get(x).get(y).get(z);
    }

    public Map<ThreeDVector, Block> getBlockMap() {
        Map<ThreeDVector, Block> result = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Map<Integer, Block>>> x : blocks.entrySet()) {
            for (Map.Entry<Integer, Map<Integer, Block>> y : x.getValue().entrySet()) {
                for (Map.Entry<Integer, Block> z : y.getValue().entrySet()) {
                    ThreeDVector vec = new ThreeDVector();
                    vec.x = x.getKey();
                    vec.y = y.getKey();
                    vec.z = z.getKey();
                    result.put(vec, z.getValue());
                }
            }
        }
        return result;
    }
}
