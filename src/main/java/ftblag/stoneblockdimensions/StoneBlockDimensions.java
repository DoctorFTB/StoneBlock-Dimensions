package ftblag.stoneblockdimensions;

import ftblag.stoneblockdimensions.end.EW_WorldProvider;
import ftblag.stoneblockdimensions.mining.MW_WorldProvider;
import ftblag.stoneblockdimensions.portal.BlockNullEndPortal;
import ftblag.stoneblockdimensions.portal.BlockNullNetherPortal;
import ftblag.stoneblockdimensions.portal.EndPortalReplacer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = StoneBlockDimensions.MODID, name = StoneBlockDimensions.NAME, version = StoneBlockDimensions.version, guiFactory = "ftblag.stoneblockdimensions.client.ModGuiFactory")
public class StoneBlockDimensions {

    public static final String MODID = "stoneblockdimensions", NAME = "StoneBlock Dimensions", version = "@VERSION@";
    public static DimensionType typeMW, typeEW;
    public static Logger log;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        log = e.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        typeMW = DimensionType.register(MODID + "_mw_world", "", SBUConfig.dimIDMining, MW_WorldProvider.class, false);
        DimensionManager.registerDimension(typeMW.getId(), typeMW);
        typeEW = DimensionType.register(MODID + "_ew_world", "", SBUConfig.dimIDEnd, EW_WorldProvider.class, false);
        DimensionManager.registerDimension(typeEW.getId(), typeEW);
        if (SBUConfig.disableEnd)
            EndPortalReplacer.replaceBlock(Blocks.END_PORTAL, BlockNullEndPortal.class, ItemBlock.class);
        if (SBUConfig.disableNether)
            EndPortalReplacer.replaceBlock(Blocks.PORTAL, BlockNullNetherPortal.class, ItemBlock.class);
        MinecraftForge.TERRAIN_GEN_BUS.register(new SBUEventsTerrain());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {

    }

    public static void log(String str) {
        if (SBUConfig.debug_log)
            log.debug(str);
    }
}
