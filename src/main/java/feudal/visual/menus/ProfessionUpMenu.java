package feudal.visual.menus;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import feudal.utils.enums.professionEnums.AttributeForProfessionE;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class ProfessionUpMenu {

    public static void upgradeGameClass(@NotNull Player player) {

        Inventory upgradeGameClassInv = Bukkit.createInventory(player, 9, "Прокачка класса");
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        upgradeGameClassInv.setItem(2, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForProfessionE.getOneAttributeNameByID(feudalPlayer.getAClassID())));
        upgradeGameClassInv.setItem(6, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForProfessionE.getSecondAttributeNameByID(feudalPlayer.getAClassID())));

        player.openInventory(upgradeGameClassInv);

    }
}
