package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.commands.staffCommands.SpyCommand;
import feudal.info.PlayerInfoDB;
import feudal.listeners.GameClassesListeners;
import feudal.listeners.PlayerJoinAndQuit;
import feudal.listeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.listeners.menuListeners.CreateKingdomMenuInteractListener;
import feudal.listeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.listeners.menuListeners.GameClassUpMenuInteractListener;
import feudal.utils.CachePlayers;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
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

        Bukkit.getPluginManager().registerEvents(new GameClassesListeners(), this);
        Bukkit.getPluginManager().registerEvents(new GameClassChangeMenuInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUpMenuInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new GameClassUpMenuInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new CreateKingdomMenuInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);

        getCommand("spy").setExecutor(new SpyCommand());
        getCommand("admin").setExecutor(new AdminCommands());
        getCommand("ls").setExecutor(new LocalStaffCommands());
        getCommand("f").setExecutor(new PlayerCommands());
    }
    public static Plugin getPlugin() {

        return plugin;

    }

    @Override
    public void onDisable() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            playerInfoDB.setField(player, "classID", CachePlayers.getPlayerInfo().get(player).getAClassID());
            playerInfoDB.setField(player, "experience", CachePlayers.getPlayerInfo().get(player).getExperience());
            playerInfoDB.setField(player, "balance", CachePlayers.getPlayerInfo().get(player).getBalance());
            playerInfoDB.setField(player, "deaths", CachePlayers.getPlayerInfo().get(player).getDeaths());
            playerInfoDB.setField(player, "kills", CachePlayers.getPlayerInfo().get(player).getKills());
            playerInfoDB.setField(player, "luckLvl", CachePlayers.getPlayerInfo().get(player).getLuckLvl());
            playerInfoDB.setField(player, "speedLvl", CachePlayers.getPlayerInfo().get(player).getSpeedLvl());
            playerInfoDB.setField(player, "staminaLvl", CachePlayers.getPlayerInfo().get(player).getStaminaLvl());
            playerInfoDB.setField(player, "strengthLvl", CachePlayers.getPlayerInfo().get(player).getStrengthLvl());
            playerInfoDB.setField(player, "survivabilityLvl", CachePlayers.getPlayerInfo().get(player).getSurvivabilityLvl());

            CachePlayers.getPlayerInfo().remove(player);

        }).start());
    }
}
