package feudal.visual.menus;

import feudal.utils.CreateItemUtils;
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

        Inventory inventory = Bukkit.createInventory(player, 54, "Выберите класс");

        inventory.setItem(10, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Строитель"));
        inventory.setItem(12, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Повар"));
        inventory.setItem(14, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Фермер"));
        inventory.setItem(16, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Рыболов"));
        inventory.setItem(28, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Охотник"));
        inventory.setItem(30, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Шахтёр"));
        inventory.setItem(32, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Пастух"));
        inventory.setItem(34, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Торговец"));
        inventory.setItem(49, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Дровосек"));

        player.openInventory(inventory);

    }
}
