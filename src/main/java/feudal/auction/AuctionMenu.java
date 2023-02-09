package feudal.auction;

import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionMenu {

    public static void openAuctionMenu(@NotNull Player player, int page) {

        Inventory openAuctionMenu = Bukkit.createInventory(player, 54, "Аукцион");

        int minIndex = page == 1 ? 10 : page * 28 + page - 27;
        int maxIndex = page * 34 + page + 8;

        for (int i = minIndex; i < maxIndex; i++)
            if (Auction.products.size() >= i)
                openAuctionMenu.addItem(ItemStackWrapper.itemStackWrapperToItemStack(Auction.products.get(i)));

        player.openInventory(openAuctionMenu);
    }
}
