package feudal.utils.enums.gcEnums;

import feudal.utils.CreateItemUtils;
import feudal.utils.enums.EnchantmentE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum FishermanLootTableE {

    BOW(1, new ItemStack(Material.BOW)),
    ENCHANTED_BOOK(2, CreateItemUtils.createItem(Material.ENCHANTED_BOOK, EnchantmentE.getRandomEnchantment(), 1, 1)),
    FISHING_ROD(3, new ItemStack(Material.FISHING_ROD)),
    NAME_TAG(4, new ItemStack(Material.NAME_TAG)),
    SADDLE(5, new ItemStack(Material.SADDLE));

    int id;
    ItemStack item;

    public static ItemStack getItemByID(int id) {

        for (FishermanLootTableE fishermanLootTableE : values())
            if (fishermanLootTableE.getId() == id)
                return fishermanLootTableE.getItem();

        return new ItemStack(Material.STICK);

    }
}
