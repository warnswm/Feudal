package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.enums.ClassesIDEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class HunterListener implements Listener {


    @EventHandler
    public void playerHunted(@NotNull EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        if (player == null) return;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != ClassesIDEnum.HUNTER.getId() ||
                playerInfo.getGameClassLvl() < 25) return;

        event.getEntity().setFireTicks(20);

    }
}
