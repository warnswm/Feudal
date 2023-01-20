package feudal.listeners.inventoryListeners;

import feudal.info.PlayerInfo;
import feudal.utils.PlayerGameClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractAttributesUpMenuListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка атрибутов")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        PlayerInfo playerInfo = PlayerGameClass.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень")){

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getStrengthLvl()) * 100));

            playerInfo.addStrengthLvl(1);

            player.closeInventory();
        }
    }
}
