package feudal.data;

import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Auction {
    @Getter
    private static final List<ItemStackWrapper> products = new ArrayList<>();
    int quantityProducts = products.size();

    public void addProduct(@NotNull ItemStack product, int price) {
        products.add(new ItemStackWrapper(product.getType(), product.getDurability(), product.getAmount(), product.getItemMeta().getDisplayName(), product.getItemMeta().getLore(), product.getEnchantments(), price));
    }

}
