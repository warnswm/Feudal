package feudal.listeners;

import feudal.info.PlayerInfo;
import feudal.info.PlayerInfoDB;
import feudal.utils.PlayerGameClass;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerJoinAndQuit implements Listener {

    FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerInfoDB.hasPlayer(player))
            playerInfoDB.createNewPlayer(player);

        PlayerInfo playerInfo = new PlayerInfo()
                .setPlayer(player)
                .setaClassID((Integer) playerInfoDB.getField(player, "classID"))
                .setExperience((Integer) playerInfoDB.getField(player, "experience"))
                .setBalance((Integer) playerInfoDB.getField(player, "balance"))
                .setDeaths((Integer) playerInfoDB.getField(player, "deaths"))
                .setKills((Integer) playerInfoDB.getField(player, "kills"))
                .setLuckLvl((Integer) playerInfoDB.getField(player, "luckLvl"))
                .setSpeedLvl((Integer) playerInfoDB.getField(player, "speedLvl"))
                .setStaminaLvl((Integer) playerInfoDB.getField(player, "staminaLvl"))
                .setStrengthLvl((Integer) playerInfoDB.getField(player, "strengthLvl"))
                .setSurvivabilityLvl((Integer) playerInfoDB.getField(player, "survivabilityLvl"));

        PlayerGameClass.getPlayerInfo().put(player, playerInfo);

    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {

        if (PlayerGameClass.getPlayerInfo().get(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

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

    }
}
