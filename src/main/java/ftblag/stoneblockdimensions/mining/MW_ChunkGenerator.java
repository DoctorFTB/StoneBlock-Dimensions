package ftblag.stoneblockdimensions.mining;

import ftblag.stoneblockdimensions.SBUConfig;
import net.minecraft.block.Block;
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
import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;

public class MW_ChunkGenerator implements IChunkGenerator {

    private World world;
    private MapGenBase caveGenerator = new MapGenCustomCaves();

    public MW_ChunkGenerator(World world) {
        this.world = world;
    }

    @Override
    public void populate(int x, int z) {
        Biomes.PLAINS.decorate(world, world.rand, new BlockPos(x * 16, 0, z * 16));
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return Biomes.PLAINS.getSpawnableList(creatureType);
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer primer = new ChunkPrimer();
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
        IBlockState stone = Blocks.STONE.getDefaultState();
        IBlockState bedrock = Blocks.BEDROCK.getDefaultState();
        int x1, y1, z1;
        for (x1 = 0; x1 < 16; x1++) {
            for (z1 = 0; z1 < 16; z1++) {
                primer.setBlockState(x1, 0, z1, bedrock);
            }
        }
        for (x1 = 0; x1 < 16; x1++) {
            for (y1 = 1; y1 < 255; y1++) {
                for (z1 = 0; z1 < 16; z1++) {
                    primer.setBlockState(x1, y1, z1, stone);
                }
            }
        }
        for (x1 = 0; x1 < 16; x1++) {
            for (z1 = 0; z1 < 16; z1++) {
                primer.setBlockState(x1, 255, z1, bedrock);
            }
        }

        if (SBUConfig.caves_enable)
            this.caveGenerator.generate(this.world, x, z, primer);

        Chunk chunk = new Chunk(this.world, primer, x, z);

        byte[] biomeArray = chunk.getBiomeArray();
        byte id = (byte) Biome.getIdForBiome(Biomes.PLAINS);
        for (int i = 0; i < biomeArray.length; ++i) {
            biomeArray[i] = id;
        }

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
