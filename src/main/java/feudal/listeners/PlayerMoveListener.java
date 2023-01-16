package feudal.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {

        if (!event.getPlayer().hasPermission("feudal.ls") && !event.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) return;

        event.setCancelled(true);
    }
}
