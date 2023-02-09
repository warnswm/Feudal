package feudal.interactListeners.menuListeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class ClerkMenuListener implements Listener {

    @EventHandler
    public void playerInteractClerkMenu(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Выберите зачарование для снятия")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem() == null)
            return;


        for (Enchantment enchantment : event.getCurrentItem().getEnchantments().keySet()) {

            if (!player.getInventory().getItemInMainHand().getEnchantments().containsKey(enchantment)) return;

            player.getInventory().getItemInMainHand().removeEnchantment(enchantment);

            player.closeInventory();

        }

    }
}
