package feudal.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItemUtil {

    public static ItemStack createItem(Material material, int materialAmount, String displayName) {

        ItemStack item = new ItemStack(material, materialAmount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack createItem(Material material, int materialAmount) {
        return new ItemStack(material, materialAmount);
    }

    public static ItemStack createItem(Material material, Enchantment enchantment, int enchantmentLvl, int materialAmount) {

        ItemStack item = new ItemStack(material, materialAmount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addEnchant(enchantment, enchantmentLvl, true);

        item.setItemMeta(itemMeta);


        return item;
    }
}
