package hu.xannosz.mc.generator.multiblocks;

import hu.xannosz.mc.generator.Block;
import hu.xannosz.mc.generator.BlockVoxel;
import hu.xannosz.mc.generator.ThreeDVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Schema {

    private BlockVoxel voxel = new BlockVoxel();

    public Block getBlock(int x, int y, int z) {
        return voxel.getBlock(x, y, z);
    }

    public boolean setBlock(int x, int y, int z, Block block) {
        return voxel.setBlock(x, y, z, block);
    }

    public void read(String input) {
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] tokens = line.split(";");
            voxel.setBlock(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Block.getBlock(tokens[3]));
        }
    }

    public String write() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<ThreeDVector, Block> blockMap : voxel.getBlockMap().entrySet()) {
            builder.append(blockMap.getKey().x);
            builder.append(";");
            builder.append(blockMap.getKey().y);
            builder.append(";");
            builder.append(blockMap.getKey().z);
            builder.append(";");
            builder.append(blockMap.getValue().toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    public void readFile(String filePath) {
        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        read(builder.toString());
    }

    public void writeFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(write());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
