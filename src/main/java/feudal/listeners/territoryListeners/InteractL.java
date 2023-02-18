package feudal.listeners.territoryListeners;

import feudal.data.cache.CacheFeudalKingdoms;
import feudal.utils.enums.PrivateBlocksE;
import feudal.utils.wrappers.ChunkWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;


public class InteractL implements Listener {

    @EventHandler
    public void playerInteract(@NotNull PlayerInteractEvent event) {

        if (event.getClickedBlock() == null ||
                !CacheFeudalKingdoms.checkPrivate(new ChunkWrapper(event.getClickedBlock().getChunk().getWorld().getName(), event.getClickedBlock().getChunk().getX(), event.getClickedBlock().getChunk().getZ()).hashCode(), event.getPlayer()) ||
                !PrivateBlocksE.checkMaterial(event.getClickedBlock().getType()))
            return;

        event.setCancelled(true);

    }
}
