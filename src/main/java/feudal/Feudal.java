package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.AhCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.databaseAndCache.CacheKingdoms;
import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.KingdomInfo;
import feudal.databaseAndCache.PlayerInfo;
import feudal.listeners.gameClassesListeners.*;
import feudal.listeners.generalListeners.PlayerJoinAndQuit;
import feudal.listeners.generalListeners.craftItems.CraftItemsListener;
import feudal.listeners.interactListeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.listeners.interactListeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.listeners.interactListeners.menuListeners.GameClassUpMenuInteractListener;
import feudal.optimizationPatches.redstone.PlaceRedstoneListener;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Feudal extends JavaPlugin {

    static Plugin plugin;
    @Override
    public void onEnable() {

        if (!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib"))
            Bukkit.getPluginManager().disablePlugin(this);

        plugin = this;

        registerCommands();
        registerEvents();
        loadConfig();

        timer();

    }
    public static Plugin getPlugin() {

        return plugin;

    }

    @Override
    public void onDisable() {

        savePlayers();
        saveKingdoms();

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
        Bukkit.getPluginManager().registerEvents(new PlayerGeneralListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlaceRedstoneListener(), this);
        Bukkit.getPluginManager().registerEvents(new BuilderListener(), this);
        Bukkit.getPluginManager().registerEvents(new ClerkListener(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanListener(), this);
        Bukkit.getPluginManager().registerEvents(new MinerListener(), this);
        Bukkit.getPluginManager().registerEvents(new WoodcutterListener(), this);
        Bukkit.getPluginManager().registerEvents(new HunterListener(), this);

    }

    private void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

    }

    private void savePlayers() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final PlayerInfo playerInfo = new PlayerInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            PlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

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

            CachePlayers.getPlayerInfo().remove(player);
        }).start());

        System.gc();

    }

    private void saveKingdoms() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        new Thread(() -> {

            for (Map.Entry<String, KingdomInfo> kingdom : CacheKingdoms.getKingdomInfo().entrySet()) {

                val cacheKingdomInfo = kingdom.getValue();
                val kingdomName = cacheKingdomInfo.getKingdomName();

                kingdomInfo.setField(kingdomName, "king", cacheKingdomInfo.getKing());
                kingdomInfo.setField(kingdomName, "members", cacheKingdomInfo.getMembers());
                kingdomInfo.setField(kingdomName, "barons", cacheKingdomInfo.getBarons());
                kingdomInfo.setField(kingdomName, "territory", cacheKingdomInfo.getTerritory());
                kingdomInfo.setField(kingdomName, "balance", cacheKingdomInfo.getBalance());
                kingdomInfo.setField(kingdomName, "reputation", cacheKingdomInfo.getReputation());

                CacheKingdoms.getKingdomInfo().remove(kingdomName);

            }
        }).start();

        System.gc();

    }
    private void timer() {

        scheduleRepeatAtTime(this, () -> Bukkit.getScheduler().runTaskLater(plugin, () -> {

            for (Map.Entry<String, KingdomInfo> kingdom : CacheKingdoms.getKingdomInfo().entrySet()) {

                val kingdomInfo = kingdom.getValue();
                val reputation = kingdom.getValue().getReputation();

                if (reputation <= 0)
                    kingdomInfo.takeAllTerritory();


                val balance = kingdom.getValue().getBalance();
                val territory = kingdom.getValue().getTerritory();
                val members = kingdom.getValue().getMembers();

                val landTax = reputation == 1000 ? 1500 : 1500 * (1000 - reputation) / 1000 + 1500;
                val taxOnResidents = reputation == 1000 ? 300 : 300 * (1000 - reputation) / 1000 + 300;


                kingdomInfo.takeBalance(balance / 100 * 3);

                for (Chunk chunk : territory) {

                    if (balance < landTax) {

                        kingdomInfo.takeTerritory(chunk);
                        continue;
                    }

                    kingdomInfo.takeBalance(landTax);
                }

                for (String ignored : members) {

                    if (balance < taxOnResidents) {

                        kingdomInfo.takeReputation(30);
                        continue;
                    }

                    kingdomInfo.takeBalance(taxOnResidents);

                }

            }

        }, 0L), 432000L);

        System.gc();

    }
    public static void scheduleRepeatAtTime(Plugin plugin, Runnable task, long period) {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0L, period);

    }
}
