package feudal.view.menu;

import feudal.info.CachePlayerInfo;
import feudal.utils.CachePlayers;
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
public class AttributesUpMenu {

    Player player;
    int attributeLvl;
    String attributeName;

    public void attributesUpMenu() {

        Inventory strengthPumpingMenuInv = Bukkit.createInventory(player, 9, "Прокачка атрибутов");

        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

        if (cachePlayerInfo.getExperience() >= Math.pow(1 + 0.05, attributeLvl) * 100)
            strengthPumpingMenuInv.setItem(4, CreateItemUtil.createItem(Material.GREEN_SHULKER_BOX, 1, "Прокачать уровень " + attributeName));
        else
            strengthPumpingMenuInv.setItem(4, CreateItemUtil.createItem(Material.GRAY_SHULKER_BOX, 1, "Не достаточно опыта, нужно ещё " + (Math.pow(1 + 0.05, attributeLvl) * 100 - cachePlayerInfo.getExperience())));


        player.openInventory(strengthPumpingMenuInv);
    }
}
