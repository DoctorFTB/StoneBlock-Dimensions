package ftblag.stoneblockdimensions;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class SBUTeleporter extends Teleporter {
    private WorldServer worldServer;
    private BlockPos pos;

    public SBUTeleporter(WorldServer par1WorldServer, BlockPos pos) {
        super(par1WorldServer);
        worldServer = par1WorldServer;
        this.pos = pos;
    }

    @Override
    public void placeInPortal(Entity entity, float rotationYaw) {
        entity.timeUntilPortal = SBUConfig.tp_cooldown;
        for (int y = 0; y < 3; y++) {
            BlockPos pos = this.pos.up(y);

            if (ResourceLocationMeta.teleporterWhiteListArray.contains(new ResourceLocationMeta(worldServer.getBlockState(pos))))
//            Block block = worldServer.getBlockState(pos).getBlock();
//            if (block == Blocks.STONE || block == Blocks.END_STONE)
                 worldServer.setBlockToAir(pos);
        }
        if (worldServer.isAirBlock(pos.down())) {
            worldServer.setBlockState(pos.down(), Blocks.COBBLESTONE.getDefaultState());
        }

        SBUEvents.invulnerabilityPlayers.put(entity.getName(), SBUConfig.invulnerabilityTime);
        entity.setPosition(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
        entity.motionX = entity.motionY = entity.motionZ = 0;
        entity.timeUntilPortal = SBUConfig.tp_cooldown;
    }
}
