package feudal.view.generalMenu;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.PlayerInfo;
import feudal.utils.CreateItemUtil;
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
        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(player);

        upgradeGameClassInv.setItem(2, CreateItemUtil.createItem(Material.CLAY_BALL, 1, AttributeForGameClassesEnum.getOneAttributeNameByID(playerInfo.getAClassID())));
        upgradeGameClassInv.setItem(6, CreateItemUtil.createItem(Material.CLAY_BALL, 1, AttributeForGameClassesEnum.getSecondAttributeNameByID(playerInfo.getAClassID())));

        player.openInventory(upgradeGameClassInv);
    }
}
