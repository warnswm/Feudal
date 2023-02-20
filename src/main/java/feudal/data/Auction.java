package feudal.data;

import feudal.data.cache.CacheAuction;
import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auction {

    final Map<String, List<ItemStackWrapper>> playersProducts = CacheAuction.getPlayersProduct();
    final int quantityProducts = playersProducts.values().size();
    final int quantityPlayers = playersProducts.size();


}
