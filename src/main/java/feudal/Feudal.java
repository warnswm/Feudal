package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.commands.StaffCommands;
import feudal.donateItemsListeners.*;
import feudal.fishing.PlayerCaughtFish;
import feudal.gameClassesListeners.expListeners.*;
import feudal.gameClassesListeners.peasants.*;
import feudal.generalListeners.ArmorListener;
import feudal.generalListeners.MobListener;
import feudal.generalListeners.PlayerJoinAndQuit;
import feudal.generalListeners.PlayerListener;
import feudal.generalListeners.craftItems.CraftItemsListener;
import feudal.interactListeners.menuListeners.*;
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

    static Plugin plugin;

    public static Plugin getPlugin() {

        return plugin;

    }

    @Override
    public void onEnable() {

        plugin = this;

        registerCommands();
        registerEvents();

        LoadAndSaveDataUtils.loadAllConfigs();
        LoadAndSaveDataUtils.loadPlacedBlocks();

        PlannedActivitiesUtils.taxCollection();
        PlannedActivitiesUtils.restart();

    }

    @Override
    public void onDisable() {

        LoadAndSaveDataUtils.saveAllPlayers();
        LoadAndSaveDataUtils.saveAllKingdoms();
        LoadAndSaveDataUtils.saveAllConfigs();

        LoadAndSaveDataUtils.savePlacedBlocks();

    }

    private void registerCommands() {

        getCommand("admin").setExecutor(new AdminCommands());
        getCommand("ls").setExecutor(new LocalStaffCommands());
        getCommand("f").setExecutor(new PlayerCommands());
        getCommand("s").setExecutor(new StaffCommands());

    }

    private void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new GameClassChangeMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUpMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new GameClassUpMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);
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
        Bukkit.getPluginManager().registerEvents(new MailMenuListener(), this);

        Bukkit.getPluginManager().registerEvents(new BlindnessListener(), this);
//        Bukkit.getPluginManager().registerEvents(new DesiccationListener(), this);
        Bukkit.getPluginManager().registerEvents(new DoubleDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new LevitationListener(), this);
        Bukkit.getPluginManager().registerEvents(new NauseaListener(), this);
        Bukkit.getPluginManager().registerEvents(new PoisoningListener(), this);
        Bukkit.getPluginManager().registerEvents(new SlowdownListener(), this);
        Bukkit.getPluginManager().registerEvents(new SwordStunListener(), this);
        Bukkit.getPluginManager().registerEvents(new VampirismListener(), this);

        Bukkit.getPluginManager().registerEvents(new FarmerExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new HunterExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new MinerExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new ShepherdExpListener(), this);
        Bukkit.getPluginManager().registerEvents(new WoodcutterExpListener(), this);

        Bukkit.getPluginManager().registerEvents(new ClerkMenuListener(), this);

    }
}
