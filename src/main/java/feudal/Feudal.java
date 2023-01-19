package feudal;

import feudal.commands.adminCommands.*;
import feudal.commands.staffCommands.SpyCommand;
import feudal.listeners.ClassListeners;
import feudal.listeners.PlayerJoinAndQuit;
import feudal.listeners.inventoryListeners.InteractAttributesUpMenuListener;
import feudal.listeners.inventoryListeners.InteractGameClassChangeMenuListener;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Feudal extends JavaPlugin {

    static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new ClassListeners(), this);
        Bukkit.getPluginManager().registerEvents(new InteractGameClassChangeMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractAttributesUpMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);

        getCommand("givekingdomstats").setExecutor(new GiveKingdomStats());
        getCommand("giveplayerstats").setExecutor(new GivePlayerStats());
        getCommand("resetallclanmembers").setExecutor(new ResetAllClanMembers());
        getCommand("resetaplayer").setExecutor(new ResetAPlayer());
        getCommand("resetthekingdom").setExecutor(new ResetTheKingomdom());
        getCommand("spy").setExecutor(new SpyCommand());
        getCommand("addls").setExecutor(new AddLS());
        getCommand("getarmy").setExecutor(new GetArmy());
        getCommand("openclassselection").setExecutor(new OpenClassSelection());
        getCommand("openupgradegameclass").setExecutor(new OpenUpgradeGameClass());
        getCommand("changegameclass").setExecutor(new ChangeGameClass());
    }
    public static Plugin getPlugin() {

        return plugin;

    }
}
