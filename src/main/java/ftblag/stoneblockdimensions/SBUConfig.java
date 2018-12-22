package ftblag.stoneblockdimensions;

import net.minecraftforge.common.config.Config;

@Config(modid = StoneBlockDimensions.MODID)
public class SBUConfig {

    @Config.Name("overToMining")
    @Config.Comment("At which Y-level in the overworld you'll be teleported to Mining dim")
    public static int overToMining = 254;
    @Config.Name("miningToOver")
    @Config.Comment("At which Y-level in Mining dim you'll be teleported to the overworld")
    public static int miningToOver = 2;
    @Config.Name("miningToEnd")
    @Config.Comment("At which Y-level in Mining dim you'll be teleported to End dim")
    public static int miningToEnd = 252;
    @Config.Name("endToMining")
    @Config.Comment("At which Y-level in End dim you'll be teleported to Mining dim")
    public static int endToMining = 2;

    @Config.Name("overToMiningPosition")
    @Config.Comment("At which Y-level in the Mining dim you'll spawn when coming from the overworld")
    public static int overToMiningPosition = 4;
    @Config.Name("miningToOverPosition")
    @Config.Comment("At which Y-level in overworld you'll be spawn when coming from the Mining dim")
    public static int miningToOverPosition = 250;
    @Config.Name("miningToEndPosition")
    @Config.Comment("At which Y-level in End dim you'll be spawn when coming from the Mining dim")
    public static int miningToEndPosition = 4;
    @Config.Name("endToMiningPosition")
    @Config.Comment("At which Y-level in Mining dim you'll spawn when coming from the end")
    public static int endToMiningPosition = 250;

    @Config.Name("dimIDMining")
    @Config.Comment("The actual dimension id")
    public static int dimIDMining = -7;
    @Config.Name("dimIDEnd")
    @Config.Comment("The actual dimension id")
    public static int dimIDEnd = -8;

    @Config.Name("disableNether")
    @Config.Comment("disabling of original Minecraft nether")
    public static boolean disableNether = true;
    @Config.Name("disableEnd")
    @Config.Comment("disabling of original Minecraft end")
    public static boolean disableEnd = true;

    @Config.Name("wait_time")
    @Config.Comment("Wait time to first login")
    public static int wait_time = 50;
    @Config.Name("tp_cooldown")
    @Config.Comment("Cooldown before teleport")
    public static int tp_cooldown = 50;
    @Config.Name("caves_enable")
    @Config.Comment("Can caves spawn in mining world (true - yes, false - no)")
    public static boolean caves_enable = true;
    @Config.Name("debug_log")
    @Config.Comment("Enable or disable debug.")
    public static boolean debug_log = false;

    @Config.Name("teleporterWhiteList")
    @Config.Comment("Blocks from this list can deleted by teleporter")
    public static String[] teleporterWhiteList = {};
}
