package ftblag.stoneblockdimensions.portal;

import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNullEndPortal extends BlockEndPortal {
    protected BlockNullEndPortal() {
        super(Material.PORTAL);
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {

    }
}
