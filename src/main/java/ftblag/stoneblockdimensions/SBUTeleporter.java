package ftblag.stoneblockdimensions;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

public class SBUTeleporter implements ITeleporter {
    private WorldServer worldServer;
    private BlockPos pos;

    public SBUTeleporter(WorldServer par1WorldServer, BlockPos pos) {
        worldServer = par1WorldServer;
        this.pos = pos;
    }

    @Override
    public void placeEntity(World world, Entity entity, float yaw) {
        entity.timeUntilPortal = SBUConfig.tp_cooldown;
        for (int y = 0; y < 3; y++) {
            BlockPos pos = this.pos.up(y);

            if (ResourceLocationMeta.teleporterWhiteListArray.contains(new ResourceLocationMeta(worldServer.getBlockState(pos))))
                worldServer.setBlockToAir(pos);
        }
        if (worldServer.isAirBlock(pos.down())) {
            worldServer.setBlockState(pos.down(), Blocks.COBBLESTONE.getDefaultState());
        }

        entity.setLocationAndAngles(pos.getX() + .4, pos.getY() + .5, pos.getZ() + .4, entity.rotationYaw, entity.rotationPitch);
        entity.motionX = entity.motionY = entity.motionZ = 0;
        entity.fallDistance = 0;
        entity.timeUntilPortal = SBUConfig.tp_cooldown;
    }
}
