package feudal;

import feudal.commands.AdminCommands;
import feudal.commands.LocalStaffCommands;
import feudal.commands.PlayerCommands;
import feudal.databaseAndCache.CacheKingdoms;
import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.KingdomInfo;
import feudal.databaseAndCache.PlayerInfo;
import feudal.listeners.gameClassesListeners.GameClassesExpListeners;
import feudal.listeners.gameClassesListeners.PlayerGeneralListener;
import feudal.listeners.generalListeners.PlayerJoinAndQuit;
import feudal.listeners.generalListeners.craftItems.CraftItemsListener;
import feudal.listeners.interactListeners.menuListeners.AttributesUpMenuInteractListener;
import feudal.listeners.interactListeners.menuListeners.GameClassChangeMenuInteractListener;
import feudal.listeners.interactListeners.menuListeners.GameClassUpMenuInteractListener;
import feudal.optimizationPatches.redstone.PlaceRedstoneListener;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

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

        restartTimer();

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

    }

    private void saveKingdoms() {

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            if (kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) return;

            String kingdomName = kingdomInfo.getPlayerKingdom(player);

            KingdomInfo cacheKingdomInfo = CacheKingdoms.getKingdomInfo().get(kingdomName);

            kingdomInfo.setField(kingdomName, "king", cacheKingdomInfo.getKing());
            kingdomInfo.setField(kingdomName, "banner", cacheKingdomInfo.getBanner().toString());
            kingdomInfo.setField(kingdomName, "members", cacheKingdomInfo.getMembers());
            kingdomInfo.setField(kingdomName, "barons", cacheKingdomInfo.getBarons());
            kingdomInfo.setField(kingdomName, "territory", cacheKingdomInfo.getTerritory());

            CacheKingdoms.getKingdomInfo().remove(kingdomName);
        }).start());

    }
    private void restartTimer() {

        scheduleRepeatAtTime(this, () -> Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.spigot().restart(), 20L), 6);

    }
    public static void scheduleRepeatAtTime(Plugin plugin, Runnable task, int hour) {

        Calendar cal = Calendar.getInstance();
        long now = cal.getTimeInMillis();

        if (cal.get(Calendar.HOUR_OF_DAY) >= hour)
            cal.add(Calendar.DATE, 1);

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long offset = cal.getTimeInMillis() - now;
        long ticks = offset / 50L;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, ticks, 432000L);
    }
}
