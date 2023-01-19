package feudal.view.menu;

import feudal.utils.CreateItemUtil;
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

        inventory.setItem(0, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Строитель"));
        inventory.setItem(1, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Повар"));
        inventory.setItem(2, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Фермер"));
        inventory.setItem(3, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Рыболов"));
        inventory.setItem(4, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Охотник"));
        inventory.setItem(5, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Шахтёр"));
        inventory.setItem(6, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Пастух"));
        inventory.setItem(7, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Торговец"));
        inventory.setItem(8, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Дровосек"));

        player.openInventory(inventory);

    }
}
