package feudal.listeners;

import feudal.info.CacheKingdomInfoBuilder;
import feudal.info.CachePlayerInfoBuilder;
import feudal.info.KingdomInfoDB;
import feudal.info.PlayerInfoDB;
import feudal.utils.CacheKingdoms;
import feudal.utils.CachePlayers;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerJoinAndQuit implements Listener{

    FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    @EventHandler
    public void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerInfoDB.hasPlayer(player))
            playerInfoDB.createNewPlayer(player);


        CachePlayerInfoBuilder cachePlayerInfoBuilder = new CachePlayerInfoBuilder()
                .setPlayer(player)
                .setaClassID((Integer) playerInfoDB.getField(player, "classID"))
                .setExperience((Integer) playerInfoDB.getField(player, "experience"))
                .setGameClassExperience((Integer) playerInfoDB.getField(player, "gameClassExperience"))
                .setBalance((Integer) playerInfoDB.getField(player, "balance"))
                .setDeaths((Integer) playerInfoDB.getField(player, "deaths"))
                .setKills((Integer) playerInfoDB.getField(player, "kills"))
                .setLuckLvl((Integer) playerInfoDB.getField(player, "luckLvl"))
                .setSpeedLvl((Integer) playerInfoDB.getField(player, "speedLvl"))
                .setStaminaLvl((Integer) playerInfoDB.getField(player, "staminaLvl"))
                .setStrengthLvl((Integer) playerInfoDB.getField(player, "strengthLvl"))
                .setKingdomName((String) playerInfoDB.getField(player, "kingdomName"))
                .setSurvivabilityLvl((Integer) playerInfoDB.getField(player, "survivabilityLvl"));

        CachePlayers.getPlayerInfo().put(player, cachePlayerInfoBuilder);

        float tmp = cachePlayerInfoBuilder.getSpeedLvl();
        player.setMaxHealth(20 * (tmp / 100) + 20);


        if (!kingdomInfoDB.playerInKingdom(player)) return;

        CacheKingdomInfoBuilder cacheKingdomInfoBuilder = new CacheKingdomInfoBuilder()
                .setKingdomName(kingdomInfoDB.getPlayerKingdom(player))
                .setKing((String) kingdomInfoDB.getField(kingdomInfoDB.getPlayerKingdom(player), "king"))
                .setBanner((String) kingdomInfoDB.getField(kingdomInfoDB.getPlayerKingdom(player), "banner"))
                .setMembers((List<String>) kingdomInfoDB.getField(kingdomInfoDB.getPlayerKingdom(player), "members"))
                .setBarons((List<String>) kingdomInfoDB.getField(kingdomInfoDB.getPlayerKingdom(player), "barons"))
                .setTerritory((List<Chunk>) kingdomInfoDB.getField(kingdomInfoDB.getPlayerKingdom(player), "territory"));


        CacheKingdoms.getKingdomInfo().put(kingdomInfoDB.getPlayerKingdom(player), cacheKingdomInfoBuilder);
    }

    @EventHandler
    public void playerQuit(@NotNull PlayerQuitEvent event) {

        if (CachePlayers.getPlayerInfo().get(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        new Thread(() -> {

            CachePlayerInfoBuilder cachePlayerInfoBuilder = CachePlayers.getPlayerInfo().get(player);

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


            String kingdomName = kingdomInfoDB.getPlayerKingdom(player);

            if (kingdomName.equalsIgnoreCase("notInTheKingdom")) return;

            CacheKingdomInfoBuilder cacheKingdomInfoBuilder = CacheKingdoms.getKingdomInfo().get(kingdomName);

            kingdomInfoDB.setField(kingdomName, "king", cacheKingdomInfoBuilder.getKing());
            kingdomInfoDB.setField(kingdomName, "banner", cacheKingdomInfoBuilder.getBanner().toString());
            kingdomInfoDB.setField(kingdomName, "members", cacheKingdomInfoBuilder.getMembers());
            kingdomInfoDB.setField(kingdomName, "barons", cacheKingdomInfoBuilder.getBarons());
            kingdomInfoDB.setField(kingdomName, "territory", cacheKingdomInfoBuilder.getTerritory());

            CacheKingdoms.getKingdomInfo().remove(kingdomName);

        }).start();

    }
}
