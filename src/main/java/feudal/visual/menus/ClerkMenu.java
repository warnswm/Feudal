package feudal.visual.menus;

import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ClerkMenu {

    public static void openClerkMenu(@NotNull Player player) {

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getEnchantments().isEmpty()) {

            player.sendMessage("Предмет в основной руке не имеет зачарования!");
            return;

        }

        Inventory clerkMenu = Bukkit.createInventory(player, 54, "Выберите зачарование для снятия");

        int i = 10;

        for (Enchantment enchantment : item.getEnchantments().keySet()) {

            clerkMenu.setItem(i, CreateItemUtils.createItem(Material.ENCHANTED_BOOK, enchantment, item.getEnchantments().get(enchantment), 1));
            i++;

        }


        player.openInventory(clerkMenu);

    }
}
