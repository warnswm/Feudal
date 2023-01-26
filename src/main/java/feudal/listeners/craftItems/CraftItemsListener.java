package feudal.listeners.craftItems;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItemsListener implements Listener {

    @EventHandler
    public void playerCrafting(PrepareItemCraftEvent event) {

        if (event.getRecipe() == null || !event.getRecipe().getResult().getType().equals(Material.ARMOR_STAND)) return;

        event.getInventory().setResult(new ItemStack(Material.AIR));

    }
}
