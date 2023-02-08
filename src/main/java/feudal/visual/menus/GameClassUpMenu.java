package feudal.visual.menus;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.CreateItemUtils;
import feudal.utils.enums.gameClassesEnums.AttributeForGameClassesEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameClassUpMenu {

    Player player;

    public void upgradeGameClass() {

        Inventory upgradeGameClassInv = Bukkit.createInventory(player, 9, "Прокачка класса");
        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

        upgradeGameClassInv.setItem(2, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForGameClassesEnum.getOneAttributeNameByID(feudalPlayer.getAClassID())));
        upgradeGameClassInv.setItem(6, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForGameClassesEnum.getSecondAttributeNameByID(feudalPlayer.getAClassID())));

        player.openInventory(upgradeGameClassInv);
    }
}
