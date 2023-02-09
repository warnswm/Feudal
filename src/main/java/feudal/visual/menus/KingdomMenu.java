package feudal.visual.menus;

import feudal.data.cache.CacheKingdomsMap;
import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class KingdomMenu {

    public static void openKingdomMenu(@NotNull Player player) {

        Inventory kingdomMenu = Bukkit.createInventory(player, 54, "Меню королевства");

        kingdomMenu.setItem(22, CreateItemUtils.createItem(Material.NAME_TAG, 1, "⌦ Переименовать королевство", "⌦ Текущее имя королевства: " + CacheKingdomsMap.playerInKingdomCache(player)));

        player.openInventory(kingdomMenu);

    }
}
