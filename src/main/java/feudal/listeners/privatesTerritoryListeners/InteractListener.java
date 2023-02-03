package feudal.listeners.privatesTerritoryListeners;

import feudal.utils.enums.ItemInteractEnum;
import feudal.utils.enums.PrivateBlocksEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class InteractListener implements Listener {

    @EventHandler
    public void playerInteractItem(@NotNull PlayerInteractEvent event) {

        if (event.getItem() == null) return;

        if (ItemInteractEnum.getByItemMaterial(event.getItem().getType()))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerInteractBlock(@NotNull PlayerInteractEvent event) {

        if (PrivateBlocksEnum.getByMaterial(event.getClickedBlock().getType()))
            event.setCancelled(true);

    }
}
