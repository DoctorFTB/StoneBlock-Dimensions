package ftblag.stoneblockdimensions.client;

import ftblag.stoneblockdimensions.StoneBlockDimensions;
import ftblag.stoneblockdimensions.SBUConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.List;
import java.util.Set;

public class ModGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiConfig(parentScreen, getElements(), StoneBlockDimensions.MODID, false, false, StoneBlockDimensions.NAME);
    }

    private static List<IConfigElement> getElements() {
        List<IConfigElement> ret = ConfigElement.from(SBUConfig.class).getChildElements();
        ret.removeIf(i -> i.getName().contains("dimID"));
        return ret;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}
