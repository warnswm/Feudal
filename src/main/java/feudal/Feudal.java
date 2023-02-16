package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.commands.StaffCommands;
import feudal.donateItemsListeners.*;
import feudal.fishing.PlayerCaughtFish;
import feudal.gcListeners.expListeners.*;
import feudal.gcListeners.peasantsListeners.*;
import feudal.generalListeners.ArmorListener;
import feudal.generalListeners.MobListener;
import feudal.generalListeners.PlayerJoinAndQuitListener;
import feudal.generalListeners.PlayerListener;
import feudal.generalListeners.craftItems.CraftItemsListener;
import feudal.interactListeners.*;
import feudal.possessions.territoryListeners.BlocksListener;
import feudal.possessions.territoryListeners.InteractListener;
import feudal.utils.LoadAndSaveDataUtils;
import feudal.utils.PlannedActivitiesUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Feudal extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {

        return plugin;

    }

    @Override
    public void onDisable() {

        LoadAndSaveDataUtils.saveAllPlayers();
        LoadAndSaveDataUtils.saveAllKingdoms();

        LoadAndSaveDataUtils.saveAllConfigs();
        LoadAndSaveDataUtils.savePlacedBlocks();
        LoadAndSaveDataUtils.saveAllPlayerMail();

    }

    private void registerCommands() {

        getCommand("admin").setExecutor(new AdminCommands());
        getCommand("ls").setExecutor(new LocalStaffCommands());
        getCommand("f").setExecutor(new PlayerCommands());
        getCommand("s").setExecutor(new StaffCommands());

    }

    private void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new GCChangeMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUpMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new GCUpMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new MailMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new GeneralMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkMenuL(), this);

        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftItemsListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        Bukkit.getPluginManager().registerEvents(new BuilderListener(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkListener(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanListener(), this);
        Bukkit.getPluginManager().registerEvents(new MinerListener(), this);
        Bukkit.getPluginManager().registerEvents(new WoodcutterListener(), this);
        Bukkit.getPluginManager().registerEvents(new HunterListener(), this);
        Bukkit.getPluginManager().registerEvents(new FarmerListener(), this);
        Bukkit.getPluginManager().registerEvents(new CookListener(), this);

        Bukkit.getPluginManager().registerEvents(new MobListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlocksListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new ArmorListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCaughtFish(), this);

        Bukkit.getPluginManager().registerEvents(new BlindnessL(), this);
        Bukkit.getPluginManager().registerEvents(new DesiccationL(), this);
        Bukkit.getPluginManager().registerEvents(new DoubleDamageL(), this);
        Bukkit.getPluginManager().registerEvents(new LevitationL(), this);
        Bukkit.getPluginManager().registerEvents(new NauseaL(), this);
        Bukkit.getPluginManager().registerEvents(new PoisoningL(), this);
        Bukkit.getPluginManager().registerEvents(new SlowdownL(), this);
        Bukkit.getPluginManager().registerEvents(new SwordStunL(), this);
        Bukkit.getPluginManager().registerEvents(new VampirismL(), this);

        Bukkit.getPluginManager().registerEvents(new FarmerExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new HunterExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new MinerExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new ShepherdExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new WoodcutterExpListener(), this);

    }

    public static void setPlugin(Plugin plugin) {
        Feudal.plugin = plugin;
    }

    @Override
    public void onEnable() {

        setPlugin(this);

        registerCommands();
        registerEvents();

        LoadAndSaveDataUtils.loadAllConfigs();
        LoadAndSaveDataUtils.loadPlacedBlocks();

        PlannedActivitiesUtils.taxCollection();
//        PlannedActivitiesUtils.restart();

    }

}
