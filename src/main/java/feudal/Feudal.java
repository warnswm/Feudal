package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.info.CacheKingdomInfoBuilder;
import feudal.info.CachePlayerInfoBuilder;
import feudal.info.KingdomInfoDB;
import feudal.info.PlayerInfoDB;
import feudal.listeners.GameClassesListeners;
import feudal.listeners.PlayerJoinAndQuit;
import feudal.listeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.listeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.listeners.menuListeners.GameClassUpMenuInteractListener;
import feudal.utils.CacheKingdoms;
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
        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);

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
        final KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> {

            CachePlayerInfoBuilder cachePlayerInfoBuilder = CachePlayers.getPlayerInfo().get(player);

            new Thread(() -> {

                playerInfoDB.setField(player, "classID", cachePlayerInfoBuilder.getAClassID());
                playerInfoDB.setField(player, "experience", cachePlayerInfoBuilder.getExperience());
                playerInfoDB.setField(player, "gameClassExperience", cachePlayerInfoBuilder.getGameClassExperience());
                playerInfoDB.setField(player, "balance", cachePlayerInfoBuilder.getBalance());
                playerInfoDB.setField(player, "deaths", cachePlayerInfoBuilder.getDeaths());
                playerInfoDB.setField(player, "kills", cachePlayerInfoBuilder.getKills());
                playerInfoDB.setField(player, "luckLvl", cachePlayerInfoBuilder.getLuckLvl());
                playerInfoDB.setField(player, "speedLvl", cachePlayerInfoBuilder.getSpeedLvl());
                playerInfoDB.setField(player, "staminaLvl", cachePlayerInfoBuilder.getStaminaLvl());
                playerInfoDB.setField(player, "strengthLvl", cachePlayerInfoBuilder.getStrengthLvl());
                playerInfoDB.setField(player, "survivabilityLvl", cachePlayerInfoBuilder.getSurvivabilityLvl());
                playerInfoDB.setField(player, "kingdomName", cachePlayerInfoBuilder.getKingdomName());

                CachePlayers.getPlayerInfo().remove(player);


                if (kingdomInfoDB.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) return;

                String kingdomName = kingdomInfoDB.getPlayerKingdom(player);

                CacheKingdomInfoBuilder cacheKingdomInfoBuilder = CacheKingdoms.getKingdomInfo().get(kingdomName);

                kingdomInfoDB.setField(kingdomName, "king", cacheKingdomInfoBuilder.getKing());
                kingdomInfoDB.setField(kingdomName, "banner", cacheKingdomInfoBuilder.getBanner().toString());
                kingdomInfoDB.setField(kingdomName, "members", cacheKingdomInfoBuilder.getMembers());
                kingdomInfoDB.setField(kingdomName, "barons", cacheKingdomInfoBuilder.getBarons());
                kingdomInfoDB.setField(kingdomName, "territory", cacheKingdomInfoBuilder.getTerritory());

                CacheKingdoms.getKingdomInfo().remove(kingdomName);


            }).start();
        });
    }
}
