package ftblag.stoneblockdimensions;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SBUEventsTerrain {

    @SubscribeEvent
    public void liq(DecorateBiomeEvent.Decorate e) {
        if (e.getWorld().provider.getDimension() == StoneBlockDimensions.typeMW.getId()) {
            if (e.getType() == EventType.LAKE_LAVA || e.getType() == EventType.LAKE_WATER) {
                e.setResult(Result.DENY);
            }
        }
    }
}
