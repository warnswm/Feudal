package feudal.listeners.menuListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CreateKingdomMenuInteractListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Создание королевства")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Закрыть меню"))
            player.closeInventory();
        else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Поддтвердить создание королевства")){

            //create kingdom
            player.closeInventory();
        }
    }
}
