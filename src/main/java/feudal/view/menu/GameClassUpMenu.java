package feudal.view.menu;

import feudal.info.CachePlayerInfoBuilder;
import feudal.utils.CachePlayers;
import feudal.utils.CreateItemUtil;
import feudal.utils.gameClassesEnums.AttributeForGameClasses;
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
        CachePlayerInfoBuilder cachePlayerInfoBuilder = CachePlayers.getPlayerInfo().get(player);

        upgradeGameClassInv.setItem(2, CreateItemUtil.createItem(Material.CLAY_BALL, 1, AttributeForGameClasses.getOneAttributeNameByID(cachePlayerInfoBuilder.getAClassID())));
        upgradeGameClassInv.setItem(6, CreateItemUtil.createItem(Material.CLAY_BALL, 1, AttributeForGameClasses.getSecondAttributeNameByID(cachePlayerInfoBuilder.getAClassID())));

        player.openInventory(upgradeGameClassInv);
    }
}
