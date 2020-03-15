package hu.xannosz.mc.generator;

public interface Voxel {
     boolean setBlock(int x, int y, int z, Block block);
     Block getBlock(int x, int y, int z);
}
