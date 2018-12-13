package ftblag.stoneblockdimensions;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ResourceLocationMeta {

    public static List<ResourceLocationMeta> teleporterWhiteListArray = new ArrayList<>();
    public final ResourceLocation rl;
    public final int meta;

    public ResourceLocationMeta(String str) {
        String[] split = str.split(":");
        try {
            rl = new ResourceLocation(split[0], split[1]);
            meta = split.length < 3 ? 0 : split[2].equals("*") ? OreDictionary.WILDCARD_VALUE : Integer.parseInt(split[2]);
        } catch (Exception ex) {
            throw new LoaderException("Failed to load block for \"" + str + "\". Use \"modid:name:meta\" or \"modid:name\"", ex);
        }
    }

    public ResourceLocationMeta(IBlockState state) {
        rl = state.getBlock().getRegistryName();
        meta = state.getBlock().getMetaFromState(state);
    }

    public static void updateArrayList() {
        teleporterWhiteListArray.clear();
        for (String str : SBUConfig.teleporterWhiteList)
            teleporterWhiteListArray.add(new ResourceLocationMeta(str));
    }

    @Override
    public String toString() {
        return rl.toString() + ":" + meta;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (!(obj instanceof ResourceLocationMeta))
            return false;
        else {
            ResourceLocationMeta rlm = (ResourceLocationMeta) obj;
            return rlm.rl.equals(rl) && rlm.meta == meta;
        }
    }
}
