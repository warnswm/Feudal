package feudal;

import feudal.commands.adminCommands.*;
import feudal.commands.staffCommands.SpyCommand;
import feudal.info.PlayerInfoDB;
import feudal.listeners.ClassListeners;
import feudal.listeners.PlayerJoinAndQuit;
import feudal.listeners.inventoryListeners.InteractAttributesUpMenuListener;
import feudal.listeners.inventoryListeners.InteractGameClassChangeMenuListener;
import feudal.listeners.inventoryListeners.InteractGameClassUpMenuListener;
import feudal.utils.PlayerGameClass;
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

        Bukkit.getPluginManager().registerEvents(new ClassListeners(), this);
        Bukkit.getPluginManager().registerEvents(new InteractGameClassChangeMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractAttributesUpMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractGameClassUpMenuListener(), this);
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

    @Override
    public void onDisable() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> {

            playerInfoDB.setField(player, "classID", PlayerGameClass.getPlayerInfo().get(player).getAClassID());
            playerInfoDB.setField(player, "experience", PlayerGameClass.getPlayerInfo().get(player).getExperience());
            playerInfoDB.setField(player, "balance", PlayerGameClass.getPlayerInfo().get(player).getBalance());
            playerInfoDB.setField(player, "deaths", PlayerGameClass.getPlayerInfo().get(player).getDeaths());
            playerInfoDB.setField(player, "kills", PlayerGameClass.getPlayerInfo().get(player).getKills());
            playerInfoDB.setField(player, "luckLvl", PlayerGameClass.getPlayerInfo().get(player).getLuckLvl());
            playerInfoDB.setField(player, "speedLvl", PlayerGameClass.getPlayerInfo().get(player).getSpeedLvl());
            playerInfoDB.setField(player, "staminaLvl", PlayerGameClass.getPlayerInfo().get(player).getStaminaLvl());
            playerInfoDB.setField(player, "strengthLvl", PlayerGameClass.getPlayerInfo().get(player).getStrengthLvl());
            playerInfoDB.setField(player, "survivabilityLvl", PlayerGameClass.getPlayerInfo().get(player).getSurvivabilityLvl());

            PlayerGameClass.getPlayerInfo().remove(player);

        });
    }
}
