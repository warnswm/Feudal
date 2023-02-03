package feudal;

import feudal.auction.Auction;
import feudal.commands.AdminCommands;
import feudal.commands.AhCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomInfo;
import feudal.data.database.PlayerInfo;
import feudal.listeners.gameClassesListeners.*;
import feudal.listeners.generalListeners.MobListener;
import feudal.listeners.generalListeners.PlayerJoinAndQuit;
import feudal.listeners.generalListeners.PlayerListener;
import feudal.listeners.generalListeners.craftItems.CraftItemsListener;
import feudal.listeners.interactListeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.listeners.interactListeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.listeners.interactListeners.menuListeners.GameClassUpMenuInteractListener;
import feudal.listeners.privatesTerritoryListeners.BlocksListener;
import feudal.listeners.privatesTerritoryListeners.InteractListener;
import feudal.optimizationPatches.redstone.PlaceRedstoneListener;
import feudal.utils.PlannedActivities;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

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

        PlannedActivities.taxCollection();
        PlannedActivities.restart();

    }

    @Override
    public void onDisable() {

        savePlayers();
        saveKingdoms();
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
        Bukkit.getPluginManager().registerEvents(new PlaceRedstoneListener(), this);
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

    }

    private void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

    }

    private void savePlayers() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final PlayerInfo playerInfo = new PlayerInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            PlayerInfo cachePlayerInfo = CachePlayersMap.getPlayerInfo().get(player);

            playerInfo.setField(player, "classID", cachePlayerInfo.getAClassID());
            playerInfo.setField(player, "experience", cachePlayerInfo.getExperience());
            playerInfo.setField(player, "gameClassLvl", cachePlayerInfo.getGameClassLvl());
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

            CachePlayersMap.getPlayerInfo().remove(player);
        }).start());

        System.gc();

    }

    private void saveKingdoms() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        new Thread(() -> {

            for (Map.Entry<String, KingdomInfo> kingdom : CacheKingdomsMap.getKingdomInfo().entrySet()) {

                KingdomInfo cacheKingdomInfo = kingdom.getValue();
                String kingdomName = cacheKingdomInfo.getKingdomName();

                kingdomInfo.setField(kingdomName, "king", cacheKingdomInfo.getKing());
                kingdomInfo.setField(kingdomName, "members", cacheKingdomInfo.getMembers());
                kingdomInfo.setField(kingdomName, "barons", cacheKingdomInfo.getBarons());
                kingdomInfo.setField(kingdomName, "territory", cacheKingdomInfo.getTerritory());
                kingdomInfo.setField(kingdomName, "balance", cacheKingdomInfo.getBalance());
                kingdomInfo.setField(kingdomName, "reputation", cacheKingdomInfo.getReputation());

                CacheKingdomsMap.getKingdomInfo().remove(kingdomName);

            }
        }).start();

        System.gc();

    }
}
