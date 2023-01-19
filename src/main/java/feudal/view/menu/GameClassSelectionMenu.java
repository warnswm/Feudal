package feudal.view.menu;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameClassSelectionMenu {

    Player player;

    public void openClassSelection() {

        Inventory inventory = Bukkit.createInventory(player, 9, "Выберите класс");

        inventory.setItem(2, CreateItem.createItem(Material.BLACK_SHULKER_BOX, 1, "Фермер"));
        inventory.setItem(3, CreateItem.createItem(Material.WHITE_SHULKER_BOX, 1, "Шахтёр"));
        inventory.setItem(4, CreateItem.createItem(Material.RED_SHULKER_BOX, 1, "Рыболов"));
        inventory.setItem(5, CreateItem.createItem(Material.YELLOW_SHULKER_BOX, 1, "Охотник"));
        inventory.setItem(6, CreateItem.createItem(Material.BLUE_SHULKER_BOX, 1, "Дровосек"));

        player.openInventory(inventory);

    }
}
