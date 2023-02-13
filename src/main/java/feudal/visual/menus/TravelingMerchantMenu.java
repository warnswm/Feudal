package feudal.visual.menus;

import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class TravelingMerchantMenu {

    public static void openTravelingMerchantMenu(@NotNull Player player) {

        Inventory travelingMerchantMenu = Bukkit.createInventory(player, 54, "Странствующий торговец");

        int numberPurchaseSlots = 0;

        for (int i = 10; i <= 39; i++) {

            travelingMerchantMenu.setItem(i, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Покупка"));

            numberPurchaseSlots++;

            if (numberPurchaseSlots == 3) {

                i += 6;
                numberPurchaseSlots = 0;

            }

        }

        int numberSaleSlots = 0;

        for (int i = 14; i <= 43; i++) {

            travelingMerchantMenu.setItem(i, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Продажа"));

            numberSaleSlots++;

            if (numberSaleSlots == 3) {

                i += 6;
                numberSaleSlots = 0;

            }

        }

        player.openInventory(travelingMerchantMenu);

    }
}
