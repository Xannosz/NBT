package hu.xannosz.mc.generator;

import hu.xannosz.mc.generator.multiblocks.MultiBlock;

public class Test {

    public void run() throws Exception {
        BlockStorage storage = new BlockStorage();

        ThreeDVector pos1 = new ThreeDVector();
        pos1.x = -10;
        pos1.y = 5;
        pos1.z = -10;
        ThreeDVector pos2 = new ThreeDVector();
        pos2.x = 10;
        pos2.y = 15;
        pos2.z = 10;

        // MultiBlock.generateRectangle(storage,pos1,pos2,Block.GOLD_BLOCK,true);
        MultiBlock.generateCylinder(storage, pos1, 40, 10, Block.GOLD_BLOCK, true);

//        for (int x = 0; x < 1000; x++) {
//            for (int z = 0; z < 1000; z++) {
//                storage.setBlock(x, 0, z, Block.BEDROCK);
//                storage.setBlock(x, 1, z, Block.GRAVEL);
//                storage.setBlock(x, 2, z, Block.GRAVEL);
//                storage.setBlock(x, 3, z, Block.GRAVEL);
//                for (int y = 4; y <= 30; y++) {
//                    storage.setBlock(x, y, z, Block.WATER);
//                }
//            }
//        }
        storage.createMCAFiles();
    }

    public static void main(String[] args) throws Exception { Test test = new Test();test.run(); }
}
