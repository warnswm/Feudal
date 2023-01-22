package feudal;

import feudal.commands.adminCommands.*;
import feudal.commands.playerCommands.FCommands;
import feudal.commands.staffCommands.SpyCommand;
import feudal.info.PlayerInfoDB;
import feudal.listeners.GameClassesListeners;
import feudal.listeners.PlayerJoinAndQuit;
import feudal.listeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.listeners.menuListeners.CreateKingdomMenuInteractListener;
import feudal.listeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.listeners.menuListeners.GameClassUpMenuInteractListener;
import feudal.utils.CachePlayersHashMap;
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
        getCommand("f").setExecutor(new FCommands());
    }
    public static Plugin getPlugin() {

        return plugin;

    }

    @Override
    public void onDisable() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            playerInfoDB.setField(player, "classID", CachePlayersHashMap.getPlayerInfo().get(player).getAClassID());
            playerInfoDB.setField(player, "experience", CachePlayersHashMap.getPlayerInfo().get(player).getExperience());
            playerInfoDB.setField(player, "balance", CachePlayersHashMap.getPlayerInfo().get(player).getBalance());
            playerInfoDB.setField(player, "deaths", CachePlayersHashMap.getPlayerInfo().get(player).getDeaths());
            playerInfoDB.setField(player, "kills", CachePlayersHashMap.getPlayerInfo().get(player).getKills());
            playerInfoDB.setField(player, "luckLvl", CachePlayersHashMap.getPlayerInfo().get(player).getLuckLvl());
            playerInfoDB.setField(player, "speedLvl", CachePlayersHashMap.getPlayerInfo().get(player).getSpeedLvl());
            playerInfoDB.setField(player, "staminaLvl", CachePlayersHashMap.getPlayerInfo().get(player).getStaminaLvl());
            playerInfoDB.setField(player, "strengthLvl", CachePlayersHashMap.getPlayerInfo().get(player).getStrengthLvl());
            playerInfoDB.setField(player, "survivabilityLvl", CachePlayersHashMap.getPlayerInfo().get(player).getSurvivabilityLvl());

            CachePlayersHashMap.getPlayerInfo().remove(player);

        }).start());
    }
}
