package feudal.utils.wrappers;

import lombok.Value;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@Value
public class ItemStackWrapper {

    Material type;
    short durability;
    int amount;
    String name;
    List<String> lore;
    Map<Enchantment, Integer> enchants;
    int price;

    public static @NotNull ItemStack itemStackWrapperToItemStack(@NotNull ItemStackWrapper itemStackWrapper) {

        ItemStack itemStack = new ItemStack(itemStackWrapper.getType(), itemStackWrapper.getAmount(), itemStackWrapper.getDurability());

        itemStack.getItemMeta().setDisplayName(itemStackWrapper.getName());
        itemStack.getItemMeta().setLore(itemStackWrapper.getLore());

        itemStackWrapper.getEnchants().forEach(itemStack::addUnsafeEnchantment);

        return itemStack;

    }

}
