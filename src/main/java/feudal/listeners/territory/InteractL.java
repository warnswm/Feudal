package feudal.listeners.territory;

import feudal.data.cache.CacheFeudalKingdoms;
import feudal.utils.enums.PrivateBlocksE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;


public class InteractL implements Listener {

    @EventHandler
    public void playerInteract(@NotNull PlayerInteractEvent event) {

        if (event.getClickedBlock() == null ||
                !CacheFeudalKingdoms.checkPrivate(event.getClickedBlock().getChunk(), event.getPlayer()) ||
                !PrivateBlocksE.checkMaterial(event.getClickedBlock().getType()))
            return;

        event.setCancelled(true);

    }
}
