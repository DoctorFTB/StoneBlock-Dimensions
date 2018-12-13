package ftblag.stoneblockdimensions.portal;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

public class EndPortalReplacer {
    public static void replaceBlock(Block toReplace, Class<? extends Block> blockClass,
                                    Class<? extends ItemBlock> itemBlockClass) {
        try {
            for (final Field blockField : Blocks.class.getDeclaredFields()) {
                if (Block.class.isAssignableFrom(blockField.getType())) {
                    final Block block = (Block) blockField.get(null);
                    if (block == toReplace) {
                        ResourceLocation name = Block.REGISTRY.getNameForObject(block);
                        Block newBlock = blockClass.newInstance();
                        ItemBlock itemBlock = itemBlockClass.getConstructor(Block.class).newInstance(newBlock);
                        blockField.setAccessible(true);
                        Field fBlock = ReflectionHelper.findField(Class.forName("net.minecraftforge.registries.NamespacedDefaultedWrapper"), "locked");
                        fBlock.set(Block.REGISTRY, false);
                        Block.REGISTRY.putObject(name, newBlock);
                        fBlock.set(Block.REGISTRY, true);
                        Field fItem = ReflectionHelper.findField(Class.forName("net.minecraftforge.registries.NamespacedWrapper"), "locked");
                        fItem.set(Item.REGISTRY, false);
                        Item.REGISTRY.putObject(name, itemBlock);
                        fItem.set(Item.REGISTRY, true);
                    }
                }
            }
        } catch (Exception e) {
            throw new LoaderException("Oh.. ", e);
        }
    }
}
