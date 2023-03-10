package feudal.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class CreateItemUtils {

    public @NotNull ItemStack createItem(Material material, int materialAmount, String displayName) {

        ItemStack item = new ItemStack(material, materialAmount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        return item;

    }

    public @NotNull ItemStack createItem(Material material, Enchantment enchantment, int enchantmentLvl, int materialAmount) {

        ItemStack item = new ItemStack(material, materialAmount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addEnchant(enchantment, enchantmentLvl, true);
        item.setItemMeta(itemMeta);

        return item;

    }

    public @NotNull ItemStack createItem(Material material, int materialAmount, String displayName, @NotNull String lore) {

        ItemStack item = new ItemStack(material, materialAmount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(displayName);

        String[] loreS = lore.split("\\?!\\.");
        List<String> loreT = new ArrayList<>();

        Collections.addAll(loreT, loreS);
        loreT.add("");
        loreT.add("Нажмите, чтобы взаимодействовать.");

        itemMeta.setLore(loreT);
        item.setItemMeta(itemMeta);

        return item;

    }
}
