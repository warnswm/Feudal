package feudal.listeners;

import feudal.classes.PlayerClass;
import feudal.info.PlayerInfo;
import feudal.info.PlayerInfoDB;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinAndQuit implements Listener {


    final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    final PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerInfoDB.hasPlayer(player))
            playerInfoDB.createNewPlayer(event.getPlayer());


        PlayerInfo playerInfo = new PlayerInfo(player, (Integer) playerInfoDB.getField(player, "lvl"), (Double) playerInfoDB.getField(player, "gain"), (Integer) playerInfoDB.getField(player, "classID"), (Integer) playerInfoDB.getField(player, "experience"));

        PlayerClass.putElement(event.getPlayer(), playerInfo);


    }
    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {

        if (PlayerClass.getPlayerInfo().get(event.getPlayer()) == null) return;

        playerInfoDB.setField(event.getPlayer(), "classID", PlayerClass.getPlayerInfo().get(event.getPlayer()).getAClassID());
        playerInfoDB.setField(event.getPlayer(), "experience", PlayerClass.getPlayerInfo().get(event.getPlayer()).getExperience());

        PlayerClass.removeElement(event.getPlayer());

    }
}
