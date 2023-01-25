package feudal;

import com.comphenix.protocol.ProtocolManager;
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
    ProtocolManager protocolManager;

    @Override
    public void onEnable() {

        if (!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib"))
            Bukkit.getPluginManager().disablePlugin(this);

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

            PlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

            new Thread(() -> {

                playerInfo.setField(player, "classID", cachePlayerInfo.getAClassID());
                playerInfo.setField(player, "experience", cachePlayerInfo.getExperience());
                playerInfo.setField(player, "gameClassExperience", cachePlayerInfo.getGameClassExperience());
                playerInfo.setField(player, "balance", cachePlayerInfo.getBalance());
                playerInfo.setField(player, "deaths", cachePlayerInfo.getDeaths());
                playerInfo.setField(player, "kills", cachePlayerInfo.getKills());
                playerInfo.setField(player, "luckLvl", cachePlayerInfo.getLuckLvl());
                playerInfo.setField(player, "speedLvl", cachePlayerInfo.getSpeedLvl());
                playerInfo.setField(player, "staminaLvl", cachePlayerInfo.getStaminaLvl());
                playerInfo.setField(player, "strengthLvl", cachePlayerInfo.getStrengthLvl());
                playerInfo.setField(player, "survivabilityLvl", cachePlayerInfo.getSurvivabilityLvl());
                playerInfo.setField(player, "kingdomName", cachePlayerInfo.getKingdomName());

                CachePlayers.getPlayerInfo().remove(player);


                if (kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) return;

                String kingdomName = kingdomInfo.getPlayerKingdom(player);

                KingdomInfo cacheKingdomInfo = CacheKingdoms.getKingdomInfo().get(kingdomName);

                kingdomInfo.setField(kingdomName, "king", cacheKingdomInfo.getKing());
                kingdomInfo.setField(kingdomName, "banner", cacheKingdomInfo.getBanner().toString());
                kingdomInfo.setField(kingdomName, "members", cacheKingdomInfo.getMembers());
                kingdomInfo.setField(kingdomName, "barons", cacheKingdomInfo.getBarons());
                kingdomInfo.setField(kingdomName, "territory", cacheKingdomInfo.getTerritory());

                CacheKingdoms.getKingdomInfo().remove(kingdomName);


            }).start();
        });
    }
}
