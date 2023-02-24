package feudal;

import alterr.command.CommandHandler;
import feudal.commands.KingdomCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.commands.StaffCommands;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.cache.CacheSpyPlayers;
import feudal.listeners.donateItems.*;
import feudal.listeners.fishing.PlayerCaughtFish;
import feudal.listeners.general.ArmorL;
import feudal.listeners.general.MobL;
import feudal.listeners.general.PlayerJoinAndQuitL;
import feudal.listeners.general.PlayerL;
import feudal.listeners.general.craftItems.CraftItemsL;
import feudal.listeners.interact.*;
import feudal.listeners.profession.exp.*;
import feudal.listeners.profession.peasants.*;
import feudal.listeners.territory.BlocksL;
import feudal.listeners.territory.InteractL;
import feudal.utils.LoadAndSaveDataUtils;
import feudal.utils.PlannedActivitiesUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Feudal extends JavaPlugin {

    @Getter
    @Setter
    private static Plugin plugin;

    @Override
    public void onEnable() {

        setPlugin(this);

        registerCommands();
        registerListeners();

        LoadAndSaveDataUtils.loadAllConfigs();
        LoadAndSaveDataUtils.loadPlacedBlocks();
        LoadAndSaveDataUtils.loadAuction();

        PlannedActivitiesUtils.taxCollection();
        PlannedActivitiesUtils.restart();
        PlannedActivitiesUtils.clearMail();
        PlannedActivitiesUtils.secretOrder();

    }

    @Override
    public void onDisable() {

        LoadAndSaveDataUtils.saveAllPlayers();
        LoadAndSaveDataUtils.saveAllKingdoms();
        LoadAndSaveDataUtils.savePlacedBlocks();
        LoadAndSaveDataUtils.saveAllConfigs();
        LoadAndSaveDataUtils.saveAllPlayersMail();
        LoadAndSaveDataUtils.saveAuction();

        CacheFeudalPlayers.getFeudalPlayerInfo().clear();
        CacheFeudalKingdoms.getKingdomInfo().clear();
        CacheSpyPlayers.getSpyPlayerCache().clear();

    }

    private void registerCommands() {

        CommandHandler.registerCommands(StaffCommands.class, this);
        CommandHandler.registerCommands(LocalStaffCommands.class, this);
        CommandHandler.registerCommands(PlayerCommands.class, this);
        CommandHandler.registerCommands(KingdomCommands.class, this);

    }

    private void registerListeners() {

        Bukkit.getPluginManager().registerEvents(new ProfessionChangeMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUpMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new ProfessionUpMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new MailMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new AuctionMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new SwitchingProfessionMenuL(), this);

        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuitL(), this);
        Bukkit.getPluginManager().registerEvents(new CraftItemsL(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerL(), this);

        Bukkit.getPluginManager().registerEvents(new BuilderL(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkL(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanL(), this);
        Bukkit.getPluginManager().registerEvents(new MinerL(), this);
        Bukkit.getPluginManager().registerEvents(new WoodcutterL(), this);
        Bukkit.getPluginManager().registerEvents(new HunterL(), this);
        Bukkit.getPluginManager().registerEvents(new FarmerL(), this);
        Bukkit.getPluginManager().registerEvents(new CookL(), this);

        Bukkit.getPluginManager().registerEvents(new MobL(), this);
        Bukkit.getPluginManager().registerEvents(new BlocksL(), this);
        Bukkit.getPluginManager().registerEvents(new InteractL(), this);
        Bukkit.getPluginManager().registerEvents(new ArmorL(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCaughtFish(), this);

        Bukkit.getPluginManager().registerEvents(new BlindnessL(72), this);
        Bukkit.getPluginManager().registerEvents(new BowStunL(73), this);
        Bukkit.getPluginManager().registerEvents(new DesiccationL(74), this);
        Bukkit.getPluginManager().registerEvents(new DoubleDamageL(75), this);
        Bukkit.getPluginManager().registerEvents(new HookL(76), this);
        Bukkit.getPluginManager().registerEvents(new LevitationL(77), this);
        Bukkit.getPluginManager().registerEvents(new MultiShootingL(78), this);
        Bukkit.getPluginManager().registerEvents(new NauseaL(79), this);
        Bukkit.getPluginManager().registerEvents(new PoisoningL(80), this);
        Bukkit.getPluginManager().registerEvents(new SlowdownL(81), this);
        Bukkit.getPluginManager().registerEvents(new SwordStunL(82), this);
        Bukkit.getPluginManager().registerEvents(new VampirismL(83), this);

        Bukkit.getPluginManager().registerEvents(new FarmerExpL(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkExpL(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanExpL(), this);
        Bukkit.getPluginManager().registerEvents(new HunterExpL(), this);
        Bukkit.getPluginManager().registerEvents(new MinerExpL(), this);
        Bukkit.getPluginManager().registerEvents(new ShepherdExpL(), this);
        Bukkit.getPluginManager().registerEvents(new WoodcutterExpL(), this);

    }

}
