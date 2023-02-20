package feudal.data.cache;

import feudal.utils.wrappers.ItemStackWrapper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheAuction {
    private final static Map<String, List<ItemStackWrapper>> playersProducts = new HashMap<>();

    public static Map<String, List<ItemStackWrapper>> getPlayersProduct() {
        return playersProducts;
    }

    public static ItemStackWrapper getPlayerProduct(@NotNull Player player, int index) {
        return playersProducts.get(player.getUniqueId().toString()).get(index);
    }

    public static List<ItemStackWrapper> getPlayerProducts(@NotNull Player player) {
        return playersProducts.get(player.getUniqueId().toString());
    }

}
