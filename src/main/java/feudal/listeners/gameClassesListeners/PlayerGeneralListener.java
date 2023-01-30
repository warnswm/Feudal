package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.enums.MoneyForMobsEnum;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

import static feudal.utils.MathUtils.getRandInt;

public class PlayerGeneralListener implements Listener {

    @EventHandler
    public void playerTeleport(@NotNull PlayerTeleportEvent event) {

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);
    }

    @EventHandler
    public void entityDeath(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (event.getEntityType() != EntityType.PLAYER) {

            playerInfo.addBalance(MoneyForMobsEnum.getByEntity(event.getEntityType()));

            return;
        }

        PlayerInfo playerDeathInfo = CachePlayers.getPlayerInfo().get((Player) event.getEntity());

        int temp = playerDeathInfo.getBalance() / 100 * getRandInt(3, 5);

        playerInfo.addBalance(temp);

        playerDeathInfo.takeBalance(temp + getRandInt(1, 3));
    }
}
