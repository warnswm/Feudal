package feudal.view.menu;

import feudal.info.PlayerInfo;
import feudal.utils.PlayerGameClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StrengthPumpingMenu {

    Player player;

    public void openStrengthPumpingMenu() {

        Inventory strengthPumpingMenuInv = Bukkit.createInventory(player, 9, "Прокачка атрибутов");

        PlayerInfo playerInfo = PlayerGameClass.getPlayerInfo().get(player);


        if (playerInfo.getExperience() >= Math.pow(1 + 0.05, playerInfo.getStrengthLvl()) * 100)
            strengthPumpingMenuInv.setItem(4, CreateItem.createItem(Material.GREEN_SHULKER_BOX, 1, "Прокачать уровень силы " + playerInfo.getStrengthLvl() + 1));
        else
            strengthPumpingMenuInv.setItem(4, CreateItem.createItem(Material.GRAY_SHULKER_BOX, 1, "Не достаточно опыта, нужно ещё " + (Math.pow(1 + 0.05, playerInfo.getStrengthLvl()) * 100 - playerInfo.getExperience())));


        player.openInventory(strengthPumpingMenuInv);
    }
}
