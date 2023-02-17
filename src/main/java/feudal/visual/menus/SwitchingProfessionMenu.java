package feudal.visual.menus;

import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class SwitchingProfessionMenu {

    public static void switchingProfession(@NotNull Player player) {

        Inventory switchingProfession = Bukkit.createInventory(player, 54, "Смена класса");

        switchingProfession.setItem(10, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Строитель"));
        switchingProfession.setItem(12, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Повар"));
        switchingProfession.setItem(14, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Фермер"));
        switchingProfession.setItem(16, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Рыболов"));
        switchingProfession.setItem(28, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Охотник"));
        switchingProfession.setItem(30, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Шахтёр"));
        switchingProfession.setItem(32, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Пастух"));
        switchingProfession.setItem(34, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Торговец"));
        switchingProfession.setItem(49, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Дровосек"));

        player.openInventory(switchingProfession);

    }

}
