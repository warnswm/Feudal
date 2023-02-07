package feudal;

import feudal.auction.Auction;
import feudal.commands.AdminCommands;
import feudal.commands.AhCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.fishing.PlayerCaughtFish;
import feudal.gameClassesListeners.peasants.*;
import feudal.generalListeners.ArmorListener;
import feudal.generalListeners.MobListener;
import feudal.generalListeners.PlayerJoinAndQuit;
import feudal.generalListeners.PlayerListener;
import feudal.generalListeners.craftItems.CraftItemsListener;
import feudal.interactListeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.interactListeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.interactListeners.menuListeners.GameClassUpMenuInteractListener;
import feudal.possessions.privatesTerritoryListeners.BlocksListener;
import feudal.possessions.privatesTerritoryListeners.InteractListener;
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

        if (!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib"))
            Bukkit.getPluginManager().disablePlugin(this);

        plugin = this;

        registerCommands();
        registerEvents();
        loadConfig();
        Auction.load();

        PlannedActivitiesUtils.taxCollection();
        PlannedActivitiesUtils.restart();

    }

    @Override
    public void onDisable() {

        LoadAndSaveDataUtils.saveAllPlayers();
        LoadAndSaveDataUtils.saveAllKingdoms();
        Auction.save();

    }

    private void registerCommands() {

        getCommand("admin").setExecutor(new AdminCommands());
        getCommand("ls").setExecutor(new LocalStaffCommands());
        getCommand("ah").setExecutor(new AhCommands());
        getCommand("f").setExecutor(new PlayerCommands());

    }

    private void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new GameClassesExpListeners(), this);
        Bukkit.getPluginManager().registerEvents(new GameClassChangeMenuInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUpMenuInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new GameClassUpMenuInteractListener(), this);
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

    }

    private void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

    }
}
