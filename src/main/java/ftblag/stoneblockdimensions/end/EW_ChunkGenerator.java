package ftblag.stoneblockdimensions.end;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class EW_ChunkGenerator implements IChunkGenerator {

    private World world;
    private ChunkPrimer primer;

    public EW_ChunkGenerator(World world) {
        this.world = world;
        primer = new ChunkPrimer();
        IBlockState end_stone = Blocks.END_STONE.getDefaultState();
        IBlockState bedrock = Blocks.BEDROCK.getDefaultState();
        int x, y, z;
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                primer.setBlockState(x, 0, z, bedrock);
            }
        }
        for (x = 0; x < 16; x++) {
            for (y = 1; y < 255; y++) {
                for (z = 0; z < 16; z++) {
                    primer.setBlockState(x, y, z, end_stone);
                }
            }
        }
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                primer.setBlockState(x, 255, z, bedrock);
            }
        }
    }

    @Override
    public void populate(int x, int z) {
//        Biomes.SKY.decorate(world, world.rand, new BlockPos(x * 16, 0, z * 16));
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return Biomes.SKY.getSpawnableList(creatureType);
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public Chunk generateChunk(int x, int z) {
        final Chunk chunk = new Chunk(this.world, this.primer, x, z);
        byte id = (byte) Biome.getIdForBiome(Biomes.SKY);
        Arrays.fill(chunk.getBiomeArray(), id);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
                                           boolean findUnexplored) {
        return null;
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
