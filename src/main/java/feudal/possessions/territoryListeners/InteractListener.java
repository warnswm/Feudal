package feudal.possessions.territoryListeners;

import feudal.data.cache.CacheFeudalKingdoms;
import feudal.utils.enums.PrivateBlocksEnum;
import feudal.utils.wrappers.ChunkWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;


public class InteractListener implements Listener {

    @EventHandler
    public void playerInteract(@NotNull PlayerInteractEvent event) {

        if (event.getClickedBlock() == null ||
                !CacheFeudalKingdoms.checkPrivate(ChunkWrapper.chunkToChunkWrapper(event.getClickedBlock().getChunk()).hashCode(), event.getPlayer()) ||
                !PrivateBlocksEnum.checkMaterial(event.getClickedBlock().getType()))
            return;

        event.setCancelled(true);

    }
}
