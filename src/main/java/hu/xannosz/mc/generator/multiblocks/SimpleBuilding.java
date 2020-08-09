package hu.xannosz.mc.generator.multiblocks;

import hu.xannosz.mc.generator.ThreeDVector;
import hu.xannosz.mc.generator.Voxel;

public interface SimpleBuilding {
    void build(Voxel storage, ThreeDVector referencePosition);
}
