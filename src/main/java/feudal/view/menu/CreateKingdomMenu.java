package feudal.view.menu;

import feudal.utils.CreateItemUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateKingdomMenu {

    Player player;

    final Inventory inventoryKingdom = Bukkit.createInventory(player, 18, "Создание королевства");

    public void openCreateKingdomMenu() {

        inventoryKingdom.setItem(9, CreateItemUtil.createItem(Material.BARRIER, 1, "Закрыть меню"));
        inventoryKingdom.setItem(17, CreateItemUtil.createItem(Material.BARRIER, 1, "Закрыть меню"));
        inventoryKingdom.setItem(7, CreateItemUtil.createItem(Material.GREEN_SHULKER_BOX, 1, "Поддтвердить создание королевства"));

        player.openInventory(inventoryKingdom);

    }
    public void setSlotItem(int slot, ItemStack itemStack) {

        inventoryKingdom.setItem(slot, itemStack);

    }
}
