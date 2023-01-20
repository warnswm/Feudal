package feudal.listeners.inventoryListeners;

import feudal.utils.PlayerGameClass;
import feudal.view.menu.AttributesUpMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractGameClassUpMenuListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка класса")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":
                AttributesUpMenu attributesUpMenu = new AttributesUpMenu(player, PlayerGameClass.getPlayerInfo().get(player).getStrengthLvl());
                attributesUpMenu.attributesUpMenu();
        }
    }
}
