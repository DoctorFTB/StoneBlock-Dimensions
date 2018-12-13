package ftblag.stoneblockdimensions.end;

import ftblag.stoneblockdimensions.StoneBlockDimensions;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;

public class EW_WorldProvider extends WorldProvider {

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new EW_ChunkGenerator(world);
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.0F;
    }

    @Nullable
    @Override
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return null;
    }

    @Override
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
        float f = MathHelper.cos(p_76562_1_ * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.627451F;
        float f2 = 0.5019608F;
        float f3 = 0.627451F;
        f1 = f1 * (f * 0.0F + 0.15F);
        f2 = f2 * (f * 0.0F + 0.15F);
        f3 = f3 * (f * 0.0F + 0.15F);
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    public boolean isSkyColored() {
        return false;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public float getCloudHeight() {
        return 8.0F;
    }

    @Override
    public int getAverageGroundLevel() {
        return 50;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
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
