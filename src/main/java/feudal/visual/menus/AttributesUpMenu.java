package feudal.visual.menus;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AttributesUpMenu {

    public static void attributesUpMenu(Player player, int attributeLvl, String attributeName, float percent) {

        Inventory strengthPumpingMenuInv = Bukkit.createInventory(player, 9, "Прокачка атрибутов");

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (feudalPlayer.getExperience() >= Math.pow(1 + (percent / 100), attributeLvl) * 100)
            strengthPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GREEN_SHULKER_BOX, 1, "Прокачать уровень " + attributeName));
        else
            strengthPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GRAY_SHULKER_BOX, 1, "Не достаточно опыта, нужно ещё " + (int) (Math.pow(1 + 0.05, attributeLvl) * 100 - feudalPlayer.getExperience())));


        player.openInventory(strengthPumpingMenuInv);
    }
}
