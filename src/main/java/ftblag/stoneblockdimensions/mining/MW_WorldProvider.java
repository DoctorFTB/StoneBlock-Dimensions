package ftblag.stoneblockdimensions.mining;

import ftblag.stoneblockdimensions.StoneBlockDimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class MW_WorldProvider extends WorldProvider {

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new MW_ChunkGenerator(world);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public double getVoidFogYFactor() {
        return 1.0F;
    }

    @Override
    public DimensionType getDimensionType() {
        return StoneBlockDimensions.typeMW;
    }
}
