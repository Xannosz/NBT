package hu.xannosz.mc.generator.multiblocks;

import hu.xannosz.mc.generator.Block;
import hu.xannosz.mc.generator.ThreeDVector;
import hu.xannosz.mc.generator.Voxel;

public class MultiBlock {
    public static void generateRectangle(Voxel storage, ThreeDVector pos1, ThreeDVector pos2, Block block, boolean hollow) {
        int xMax, xMin, yMax, yMin, zMax, zMin;
        if (pos1.x <= pos2.x) {
            xMax = pos2.x;
            xMin = pos1.x;
        } else {
            xMax = pos1.x;
            xMin = pos2.x;
        }
        if (pos1.y <= pos2.y) {
            yMax = pos2.y;
            yMin = pos1.y;
        } else {
            yMax = pos1.y;
            yMin = pos2.y;
        }
        if (pos1.z <= pos2.z) {
            zMax = pos2.z;
            zMin = pos1.z;
        } else {
            zMax = pos1.z;
            zMin = pos2.z;
        }
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    if (!hollow || x == xMin || x == xMax || y == yMin || y == yMax || z == zMin || z == zMax) {
                        storage.setBlock(x, y, z, block);
                    }
                }
            }
        }
    }

    public static void generateCylinder(Voxel storage, ThreeDVector center, int radius, int height, Block block, boolean hollow) {
        double PI = 3.1415926535;

        for (int y = center.y+1; y < height + center.y; y++) {
            for (int i = 0; i < 360; i++) {
                double angle = i;
                double x1 = radius * Math.cos(angle * PI / 180);
                double z1 = radius * Math.sin(angle * PI / 180);

                long ElX = Math.round(center.x + x1);
                long ElZ = Math.round(center.z + z1);
                storage.setBlock((int) ElX, y, (int) ElZ, block);
            }
        }
    }

    public static void generatePyramid(Voxel storage, ThreeDVector center, int radius, int height, Block block, boolean hollow) {
        return;
    }
}
