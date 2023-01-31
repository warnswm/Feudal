package feudal.view.generalMenu;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.CreateItemUtil;
import feudal.utils.enums.AttributeForGameClassesEnum;
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
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        upgradeGameClassInv.setItem(2, CreateItemUtil.createItem(Material.CLAY_BALL, 1, AttributeForGameClassesEnum.getOneAttributeNameByID(playerInfo.getAClassID())));
        upgradeGameClassInv.setItem(6, CreateItemUtil.createItem(Material.CLAY_BALL, 1, AttributeForGameClassesEnum.getSecondAttributeNameByID(playerInfo.getAClassID())));

        player.openInventory(upgradeGameClassInv);
    }
}