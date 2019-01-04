package ftblag.stoneblockdimensions.end;

import ftblag.stoneblockdimensions.StoneBlockDimensions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;

public class EW_WorldProvider extends WorldProviderEnd {

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new EW_ChunkGenerator(world);
    }

    @Override
    public double getVoidFogYFactor() {
        return 1.0F;
    }

    @Override
    public DimensionType getDimensionType() {
        return StoneBlockDimensions.typeEW;
    }

    //These methods are overridden from WorldProviderEnd to ensure that the dragon, doesnt spawn

    @Override
    public void onWorldSave() {
        //Nope
    }

    @Override
    public void onWorldUpdateEntities() {
        //Nope
    }

    @Nullable
    @Override
    public DragonFightManager getDragonFightManager() {
        return null; //No dragon for you!
    }

    @Override
    public void onPlayerAdded(EntityPlayerMP player) {
        //Nope
    }

    @Override
    public void onPlayerRemoved(EntityPlayerMP player) {
        //Nope
    }
}
