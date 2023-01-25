package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.info.CacheKingdoms;
import feudal.info.CachePlayers;
import feudal.info.KingdomInfo;
import feudal.info.PlayerInfo;
import feudal.listeners.GameClassesListeners;
import feudal.listeners.PlayerJoinAndQuit;
import feudal.listeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.listeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.listeners.menuListeners.GameClassUpMenuInteractListener;
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
        final PlayerInfo playerInfo = new PlayerInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
        final KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> {

            CachePlayerInfoBuilder cachePlayerInfoBuilder = CachePlayers.getPlayerInfo().get(player);

            new Thread(() -> {

                playerInfo.setField(player, "classID", cachePlayerInfoBuilder.getAClassID());
                playerInfo.setField(player, "experience", cachePlayerInfoBuilder.getExperience());
                playerInfo.setField(player, "gameClassExperience", cachePlayerInfoBuilder.getGameClassExperience());
                playerInfo.setField(player, "balance", cachePlayerInfoBuilder.getBalance());
                playerInfo.setField(player, "deaths", cachePlayerInfoBuilder.getDeaths());
                playerInfo.setField(player, "kills", cachePlayerInfoBuilder.getKills());
                playerInfo.setField(player, "luckLvl", cachePlayerInfoBuilder.getLuckLvl());
                playerInfo.setField(player, "speedLvl", cachePlayerInfoBuilder.getSpeedLvl());
                playerInfo.setField(player, "staminaLvl", cachePlayerInfoBuilder.getStaminaLvl());
                playerInfo.setField(player, "strengthLvl", cachePlayerInfoBuilder.getStrengthLvl());
                playerInfo.setField(player, "survivabilityLvl", cachePlayerInfoBuilder.getSurvivabilityLvl());
                playerInfo.setField(player, "kingdomName", cachePlayerInfoBuilder.getKingdomName());

                CachePlayers.getPlayerInfo().remove(player);


                if (kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) return;

                String kingdomName = kingdomInfo.getPlayerKingdom(player);

                KingdomInfo cacheKingdomInfoBuilder = CacheKingdoms.getKingdomInfo().get(kingdomName);

                kingdomInfo.setField(kingdomName, "king", cacheKingdomInfoBuilder.getKing());
                kingdomInfo.setField(kingdomName, "banner", cacheKingdomInfoBuilder.getBanner().toString());
                kingdomInfo.setField(kingdomName, "members", cacheKingdomInfoBuilder.getMembers());
                kingdomInfo.setField(kingdomName, "barons", cacheKingdomInfoBuilder.getBarons());
                kingdomInfo.setField(kingdomName, "territory", cacheKingdomInfoBuilder.getTerritory());

                CacheKingdoms.getKingdomInfo().remove(kingdomName);


            }).start();
        });
    }
}
