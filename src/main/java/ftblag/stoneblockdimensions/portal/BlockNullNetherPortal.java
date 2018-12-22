package ftblag.stoneblockdimensions.portal;

import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNullNetherPortal extends BlockPortal {

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    }

    @Override
    public boolean trySpawnPortal(World worldIn, BlockPos pos) {
        return false;
    }
}
