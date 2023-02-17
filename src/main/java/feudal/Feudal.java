package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.commands.StaffCommands;
import feudal.donateItemsListeners.*;
import feudal.fishing.PlayerCaughtFish;
import feudal.generalListeners.ArmorL;
import feudal.generalListeners.MobL;
import feudal.generalListeners.PlayerJoinAndQuitL;
import feudal.generalListeners.PlayerL;
import feudal.generalListeners.craftItems.CraftItemsL;
import feudal.interactListeners.*;
import feudal.possessions.territoryListeners.BlocksListener;
import feudal.possessions.territoryListeners.InteractListener;
import feudal.professionListeners.expListeners.*;
import feudal.professionListeners.peasantsListeners.*;
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

    public static void setPlugin(Plugin plugin) {
        Feudal.plugin = plugin;
    }

    @Override
    public void onDisable() {

        LoadAndSaveDataUtils.saveAllPlayers();
        LoadAndSaveDataUtils.saveAllKingdoms();
        LoadAndSaveDataUtils.savePlacedBlocks();
        LoadAndSaveDataUtils.saveAllConfigs();
        LoadAndSaveDataUtils.saveAllPlayerMail();

    }

    private void registerCommands() {

        getCommand("admin").setExecutor(new AdminCommands());
        getCommand("ls").setExecutor(new LocalStaffCommands());
        getCommand("f").setExecutor(new PlayerCommands());
        getCommand("s").setExecutor(new StaffCommands());

    }

    private void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new ProfessionChangeMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUpMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new ProfessionUpMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new MailMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new GeneralMenuL(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkMenuL(), this);

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
        Bukkit.getPluginManager().registerEvents(new BlocksListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new ArmorL(), this);
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

        Bukkit.getPluginManager().registerEvents(new FarmerExpL(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkExpL(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanExpL(), this);
        Bukkit.getPluginManager().registerEvents(new HunterExpL(), this);
        Bukkit.getPluginManager().registerEvents(new MinerExpL(), this);
        Bukkit.getPluginManager().registerEvents(new ShepherdExpL(), this);
        Bukkit.getPluginManager().registerEvents(new WoodcutterExpL(), this);

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
