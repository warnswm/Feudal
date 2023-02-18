package feudal.listeners.interactListeners;

import feudal.utils.CreateItemUtils;
import feudal.utils.enums.professionEnums.ClerkTakeExpE;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class ClerkMenuL implements Listener {

    @EventHandler
    public final void playerInteractClerkMenu(@NotNull InventoryClickEvent event) {

        if (event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null ||
                !event.getView().getTitle().equals("Выберите зачарование для снятия")) return;

        event.setCancelled(true);


        Player player = (Player) event.getView().getPlayer();

        for (Enchantment enchantment : event.getCurrentItem().getEnchantments().keySet()) {

            if (!player.getInventory().getItemInMainHand().getEnchantments().containsKey(enchantment)) return;

            player.getInventory().getItemInMainHand().removeEnchantment(enchantment);
            player.getInventory().addItem(CreateItemUtils.createItem(Material.ENCHANTED_BOOK, enchantment, event.getCurrentItem().getEnchantments().get(enchantment).byteValue(), 1));

            float exp = player.getExp() - ClerkTakeExpE.getExpByLvl(event.getCurrentItem().getEnchantments().get(enchantment).byteValue()) * 7 + 2;

            if (exp <= 0) {

                player.sendMessage("Недостаточно опыта!");
                player.closeInventory();

                return;

            }

            player.setExp((int) player.getExp() - ClerkTakeExpE.getExpByLvl(event.getCurrentItem().getEnchantments().get(enchantment).byteValue()) * 7 + 2);

            player.closeInventory();

        }

    }
}
