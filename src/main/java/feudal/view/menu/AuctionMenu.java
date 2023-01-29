package feudal.view.menu;

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
public class AuctionMenu {

    Player player;
    int page;

    public void openAuction(int page) {

        Inventory inventory = Bukkit.createInventory(player, 54, "Аукцион");

        inventory.setItem(10, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Строитель"));

        player.openInventory(inventory);

    }
}
