package feudal.visual.menus;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AttributesUpMenu {

    public static void attributesUpMenu(Player player, int attributeLvl, String attributeName, double percent) {

        Inventory attributesPumpingMenuInv = Bukkit.createInventory(player, 9, "Прокачка атрибутов");
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        double percentAtt = Math.pow(1 + (percent / 100), attributeLvl) * 100;

        if (attributeLvl == 100) {

            attributesPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GRAY_SHULKER_BOX, 1, "Атрибут " + attributeName + " имеет максимальный уровень!"));
            return;

        }

        if (feudalPlayer.getExperience() >= percentAtt)
            attributesPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GREEN_SHULKER_BOX, 1, "Прокачать уровень " + attributeName));

        else
            attributesPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GRAY_SHULKER_BOX, 1, "Не достаточно опыта, нужно ещё " + (int) (percentAtt - feudalPlayer.getExperience())));


        player.openInventory(attributesPumpingMenuInv);

    }
}
