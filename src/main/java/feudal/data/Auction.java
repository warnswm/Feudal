package feudal.data;

import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Auction {
    @Getter
    private final static Map<String, List<ItemStackWrapper>> playersProducts = new HashMap<>();
    @Setter
    @Getter
    private static List<ItemStackWrapper> products = new ArrayList<>();
    int quantityProducts = playersProducts.values().size();
    int quantityPlayers = playersProducts.size();

    public void addPlayerProduct(@NotNull Player player, @NotNull ItemStack product, int price) {

        String uuid = player.getUniqueId().toString();
        ItemStackWrapper itemStackWrapper = new ItemStackWrapper(product.getType(), product.getDurability(), product.getAmount(), product.getItemMeta().getDisplayName(), product.getItemMeta().getLore(), product.getEnchantments(), price);

        if (!playersProducts.containsKey(player.getUniqueId().toString())) {

            List<ItemStackWrapper> products = new ArrayList<>();
            products.add(itemStackWrapper);

            playersProducts.put(uuid, products);

            return;

        }

        playersProducts.get(uuid).add(itemStackWrapper);

    }

    public void addProduct(@NotNull ItemStack product, int price) {
        products.add(new ItemStackWrapper(product.getType(), product.getDurability(), product.getAmount(), product.getItemMeta().getDisplayName(), product.getItemMeta().getLore(), product.getEnchantments(), price));
    }

}
