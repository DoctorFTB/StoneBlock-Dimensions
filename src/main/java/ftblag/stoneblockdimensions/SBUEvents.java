package ftblag.stoneblockdimensions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = StoneBlockDimensions.MODID)
public class SBUEvents {

    public static Map<String, Integer> invulnerabilityPlayers = new HashMap<>();

    @SubscribeEvent
    public static void onDamage(LivingHurtEvent e) {
        if (e.getEntity() instanceof EntityPlayer && !e.getEntity().world.isRemote)
            if (invulnerabilityPlayers.containsKey(e.getEntity().getName()))
                e.setAmount(0);
    }

    @SubscribeEvent
    public static void move(TickEvent.PlayerTickEvent e) {
        if (!e.side.isServer() || e.phase == Phase.START) {
            return;
        }

        if (!invulnerabilityPlayers.isEmpty())
            for (String str : invulnerabilityPlayers.keySet()) {
                int curr = invulnerabilityPlayers.get(str) - 1;
                if (curr <= 0)
                    invulnerabilityPlayers.remove(str);
                else
                    invulnerabilityPlayers.put(str, curr);
            }

        EntityPlayerMP player = (EntityPlayerMP) e.player;
        if (player.timeUntilPortal > 0) {
            return;
        }
        int dimFrom = player.world.provider.getDimension();

        if ((dimFrom == -1 && SBUConfig.disableNether) || (dimFrom == 1 && SBUConfig.disableNether)) {
            teleport(player, 0, 0, true);
        }

        if (dimFrom == 0) {
            if (player.posY >= SBUConfig.overToMining) {
                teleport(player, SBUConfig.dimIDMining, SBUConfig.overToMiningPosition, false);
                StoneBlockDimensions.log("Starting teleport Overworld -> Mining");
            }
        } else if (dimFrom == SBUConfig.dimIDMining) {
            if (player.posY <= SBUConfig.miningToOver) {
                teleport(player, 0, SBUConfig.miningToOverPosition, false);
                StoneBlockDimensions.log("Starting teleport Mining -> Overworld");
            } else if (player.posY >= SBUConfig.miningToEnd) {
                teleport(player, SBUConfig.dimIDEnd, SBUConfig.miningToEndPosition, false);
                StoneBlockDimensions.log("Starting teleport Mining -> End");
            }
        } else if (dimFrom == SBUConfig.dimIDEnd) {
            if (player.posY <= SBUConfig.endToMining) {
                teleport(player, SBUConfig.dimIDMining, SBUConfig.endToMiningPosition, false);
                StoneBlockDimensions.log("Starting teleport End -> Mining");
            }
        }
    }

    private static void teleport(EntityPlayerMP player, int dim, int y, boolean toSpawn) {
        if (player.getStatFile().readStat(StatList.PLAY_ONE_MINUTE) < SBUConfig.wait_time) {
            return;
        }
        if (!ForgeHooks.onTravelToDimension(player, dim)) {
            return;
        }
        if (player.timeUntilPortal > 0) {
            return;
        }
        WorldServer worldServer = player.server.getWorld(dim);
        player.timeUntilPortal = SBUConfig.tp_cooldown;
        player.server.getPlayerList().transferPlayerToDimension(player, dim, new SBUTeleporter(worldServer, toSpawn ? worldServer.getSpawnCoordinate() : new BlockPos(player.posX, y, player.posZ)));
        player.timeUntilPortal = SBUConfig.tp_cooldown;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent event) {
        if (event.getModID().equals(StoneBlockDimensions.MODID)) {
            ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
            ResourceLocationMeta.updateArrayList();
        }
    }

    @SubscribeEvent
    public static void teleport(EntityTravelToDimensionEvent e) {
        if ((e.getDimension() == -1 && SBUConfig.disableNether) || (e.getDimension() == 1 && SBUConfig.disableEnd)) {
            e.setCanceled(true);
        }
    }

//    @SubscribeEvent
//    public static void respawn1(net.minecraftforge.event.entity.player.PlayerEvent.Clone e) {
//        System.out.println("Try fix bug. Debug form [1] method");
//        e.getEntityPlayer().timeUntilPortal = SBUConfig.tp_cooldown;
//    }

    @SubscribeEvent
    public static void respawn2(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent e) {
//        System.out.println("Try fix bug. Debug form [2] method");
        e.player.timeUntilPortal = SBUConfig.tp_cooldown;
    }

//    @SubscribeEvent
//    public static void respawn3(LivingDeathEvent e) {
//        if (e.getEntity() instanceof EntityPlayer) {
//            System.out.println("Try fix bug. Debug form [3] method");
//            e.getEntity().timeUntilPortal = SBUConfig.tp_cooldown;
//        }
//    }
//
//    @SubscribeEvent
//    public static void respawn4(EntityJoinWorldEvent e) {
//        if (e.getEntity() instanceof EntityPlayer) {
//            System.out.println("Try fix bug. Debug form [4] method");
//            e.getEntity().timeUntilPortal = SBUConfig.tp_cooldown;
//        }
//    }
}
