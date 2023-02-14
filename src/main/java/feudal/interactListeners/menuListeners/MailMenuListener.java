package feudal.interactListeners.menuListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;


public class MailMenuListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Почта")) return;

        event.setCancelled(true);

    }
}
