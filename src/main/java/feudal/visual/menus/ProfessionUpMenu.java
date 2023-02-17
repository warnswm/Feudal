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

    public static void upgradeProfession(@NotNull Player player) {

        Inventory upgradeProfessionInv = Bukkit.createInventory(player, 9, "Прокачка класса");
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (feudalPlayer.getProfessionLvl() >= 50)
            upgradeProfessionInv.setItem(4, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Смена класса"));

        upgradeProfessionInv.setItem(2, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForProfessionE.getOneAttributeNameByID(feudalPlayer.getProfessionID())));
        upgradeProfessionInv.setItem(6, CreateItemUtils.createItem(Material.CLAY_BALL, 1, AttributeForProfessionE.getSecondAttributeNameByID(feudalPlayer.getProfessionID())));

        player.openInventory(upgradeProfessionInv);

    }
}
