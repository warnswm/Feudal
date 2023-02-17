package feudal.visual.menus;

import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class ProfessionSelectionMenu {

    public static void openProfessionSelection(@NotNull Player player) {

        Inventory professionSelection = Bukkit.createInventory(player, 54, "Выберите класс");

        professionSelection.setItem(10, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Строитель"));
        professionSelection.setItem(12, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Повар"));
        professionSelection.setItem(14, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Фермер"));
        professionSelection.setItem(16, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Рыболов"));
        professionSelection.setItem(28, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Охотник"));
        professionSelection.setItem(30, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Шахтёр"));
        professionSelection.setItem(32, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Пастух"));
        professionSelection.setItem(34, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Торговец"));
        professionSelection.setItem(49, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Дровосек"));

        player.openInventory(professionSelection);

    }
}
