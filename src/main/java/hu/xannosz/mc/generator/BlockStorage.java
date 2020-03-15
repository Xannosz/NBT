package hu.xannosz.mc.generator;

import net.querz.nbt.CompoundTag;
import net.querz.nbt.mca.Chunk;
import net.querz.nbt.mca.MCAFile;
import net.querz.nbt.mca.MCAUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BlockStorage implements Voxel{

    private static final String PATH = "C:\\Users\\kissa\\AppData\\Roaming\\.minecraft\\saves\\testGenerator\\region\\r.%s.%s.mca";
    private  BlockVoxel voxel = new BlockVoxel();
    private Map<Integer, Map<Integer, Integer>> yMaxes = new HashMap<>();
    private int xMin, xMax, zMin, zMax = 0;

    public boolean setBlock(int x, int y, int z, Block block) {
        if (y > 255 || y < 0) {
            return false;
        }
        voxel.setBlock(x,y,z,block);
        if (x < xMin) {
            xMin = x;
        }
        if (x > xMax) {
            xMax = x;
        }
        if (z < zMin) {
            zMin = z;
        }
        if (z > zMax) {
            zMax = z;
        }
        if (!yMaxes.containsKey(x)) {
            yMaxes.put(x, new HashMap<>());
        }
        if (!yMaxes.get(x).containsKey(z)) {
            yMaxes.get(x).put(z, y);
        } else {
            if (y > yMaxes.get(x).get(z)) {
                yMaxes.get(x).put(z, y);
            }
        }
        return true;
    }

    public Block getBlock(int x, int y, int z) {
        return voxel.getBlock(x,y,z);
    }

    public void createMCAFiles() {
        for (int x = MCAUtil.blockToRegion(xMin); x <= MCAUtil.blockToRegion(xMax); x++) {
            for (int z = MCAUtil.blockToRegion(zMin); z <= MCAUtil.blockToRegion(zMax); z++) {
                writeMCA(x, z);
            }
        }
    }

    private void writeMCA(int mCAX, int mCAZ) {

        MCAFile file = new MCAFile(mCAX, mCAZ);
        for (int i = 0; i < 1024; i++) {
            file.setChunk(i, Chunk.newChunk());
        }

        for (int x = MCAUtil.regionToBlock(mCAX); x <= MCAUtil.regionToBlock(mCAX + 1); x++) {
            for (int z = MCAUtil.regionToBlock(mCAZ); z <= MCAUtil.regionToBlock(mCAZ + 1); z++) {
                for (int y = 0; y <= getYMaxes(x, z); y++) {
                    setMCABlock(x, y, z, file);
                }
            }
        }

        file.cleanupPalettesAndBlockStates();
        try {
            MCAUtil.writeMCAFile(file, String.format(PATH, mCAX, mCAZ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getYMaxes(int x, int z) {
        if (!yMaxes.containsKey(x) || !yMaxes.get(x).containsKey(z)) {
            return -1;
        }
        return yMaxes.get(x).get(z);
    }

    private void setMCABlock(int x, int y, int z, MCAFile file) {
        if (!getBlock(x, y, z).equals(Block.AIR)) {
            CompoundTag tag = new CompoundTag();
            tag.putString("Name", getBlock(x, y, z).toString());
            file.setBlockStateAt(x % 512, y, z % 512, tag, false);
        }
    }
}
