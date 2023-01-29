package feudal.auction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionMenu {

    Player player;

    public void openAuctionMenu(int page) {

        Inventory openAuctionMenu = Bukkit.createInventory(player, 54, "Аукцион");

        int minIndex = page == 1 ? 1 : page * 54 + page - 55;
        int maxIndex = page * 54 + page - 1;

        for (int i = minIndex; i < maxIndex; i++)
            if (Auction.goods.size() > i)
                openAuctionMenu.addItem(Auction.goods.get(i));

        player.openInventory(openAuctionMenu);
    }
}
