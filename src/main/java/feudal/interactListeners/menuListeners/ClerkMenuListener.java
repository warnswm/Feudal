package feudal.interactListeners.menuListeners;

import feudal.utils.CreateItemUtils;
import feudal.utils.enums.gameClassesEnums.ClerkTakeExpEnum;
import org.bukkit.Material;
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
            player.getInventory().addItem(CreateItemUtils.createItem(Material.ENCHANTED_BOOK, enchantment, event.getCurrentItem().getEnchantments().get(enchantment).byteValue(), 1));

            float exp = player.getExp() - ClerkTakeExpEnum.getLvl(event.getCurrentItem().getEnchantments().get(enchantment).byteValue()) * 7 + 2;

            if (exp <= 0) {

                player.sendMessage("Недостаточно опыта!");
                player.closeInventory();

                return;

            }

            player.setExp((int) player.getExp() - ClerkTakeExpEnum.getLvl(event.getCurrentItem().getEnchantments().get(enchantment).byteValue()) * 7 + 2);

            player.closeInventory();

        }

    }
}
