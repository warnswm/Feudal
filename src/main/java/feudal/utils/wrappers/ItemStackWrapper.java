package feudal.utils.wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class ItemStackWrapper {
    public Material type;
    public short durability;
    public int amount;
    public String name;
    public List<String> lore;
    public Map<Enchantment, Integer> enchants;
    public int price;

    public static @NotNull ItemStackWrapper itemStackToItemStackWrapper(@NotNull ItemStack itemStack, int price) {
        return new ItemStackWrapper(itemStack.getType(),
                itemStack.getDurability(),
                itemStack.getAmount(),
                itemStack.getItemMeta().getDisplayName(),
                itemStack.getItemMeta().getLore(),
                itemStack.getItemMeta().getEnchants(),
                price);
    }

    public static @NotNull @Unmodifiable ItemStack itemStackWrapperToItemStack(@NotNull ItemStackWrapper itemStackWrapper) {

        ItemStack item = new ItemStack(itemStackWrapper.getType(), itemStackWrapper.getAmount());
        item.setDurability(itemStackWrapper.getDurability());

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(itemStackWrapper.getName());
        itemMeta.setLore(itemStackWrapper.getLore());

        for (Map.Entry<Enchantment, Integer> enchant : itemStackWrapper.getEnchants().entrySet())
            itemMeta.addEnchant(enchant.getKey(), enchant.getValue(), true);


        item.setItemMeta(itemMeta);

        return item;
    }

}
