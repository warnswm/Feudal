package feudal.view.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItem {

    public static ItemStack createItem(Material material, int materialAmount, String displayName) {

        ItemStack item = new ItemStack(material, materialAmount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        return item;
    }
}
