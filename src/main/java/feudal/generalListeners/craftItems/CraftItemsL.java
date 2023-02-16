package feudal.generalListeners.craftItems;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CraftItemsL implements Listener {

    @EventHandler
    public final void playerCrafting(@NotNull PrepareItemCraftEvent event) {

        if (event.getRecipe() == null ||
                !event.getRecipe().getResult().getType().equals(Material.ARMOR_STAND)) return;

        event.getInventory().setResult(new ItemStack(Material.AIR));

    }
}
