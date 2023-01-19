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
public class GameClassUpMenu {

    Player player;

    public void upgradeGameClass() {

        Inventory upgradeGameClassInv = Bukkit.createInventory(player, 9, "Прокачка класса");

        PlayerInfo playerInfo = PlayerGameClass.getPlayerInfo().get(player);

        switch (playerInfo.getAClassID()) {

            case 1:
            case 3:
                upgradeGameClassInv.setItem(2, CreateItem.createItem(Material.CLAY_BALL, 1, "Сила"));
                upgradeGameClassInv.setItem(6, CreateItem.createItem(Material.CLAY_BALL, 1, "Выносливость"));
                break;
            case 2:
            case 8:
                upgradeGameClassInv.setItem(2, CreateItem.createItem(Material.CLAY_BALL, 1, "Выносливость"));
                upgradeGameClassInv.setItem(6, CreateItem.createItem(Material.CLAY_BALL, 1, "Удача"));
                break;
            case 4:
                upgradeGameClassInv.setItem(2, CreateItem.createItem(Material.CLAY_BALL, 1, "Живучесть"));
                upgradeGameClassInv.setItem(6, CreateItem.createItem(Material.CLAY_BALL, 1, "Удача"));
                break;
            case 5:
                upgradeGameClassInv.setItem(2, CreateItem.createItem(Material.CLAY_BALL, 1, "Скорость"));
                upgradeGameClassInv.setItem(6, CreateItem.createItem(Material.CLAY_BALL, 1, "Выносливость"));
                break;
            case 6:
                upgradeGameClassInv.setItem(2, CreateItem.createItem(Material.CLAY_BALL, 1, "Сила"));
                upgradeGameClassInv.setItem(6, CreateItem.createItem(Material.CLAY_BALL, 1, "Удача"));
                break;
            case 7:
                upgradeGameClassInv.setItem(2, CreateItem.createItem(Material.CLAY_BALL, 1, "Выносливость"));
                upgradeGameClassInv.setItem(6, CreateItem.createItem(Material.CLAY_BALL, 1, "Живучесть"));
                break;
            case 9:
                upgradeGameClassInv.setItem(2, CreateItem.createItem(Material.CLAY_BALL, 1, "Сила"));
                upgradeGameClassInv.setItem(6, CreateItem.createItem(Material.CLAY_BALL, 1, "Живучесть"));
                break;

        }

        player.openInventory(upgradeGameClassInv);
    }
}
