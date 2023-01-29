package feudal.databaseAndCache;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionInfo {
    int page;
    int goods;
    List<ItemStack> items = new ArrayList<>();
}
