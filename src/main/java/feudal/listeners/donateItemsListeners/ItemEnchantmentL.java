package feudal.listeners.donateItemsListeners;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class ItemEnchantmentL implements Listener {

    @EventHandler
    public void playerClick(@NotNull InventoryClickEvent event) {

        if (event.getCurrentItem() == null ||
                event.getCursor() == null) return;

        // ||
        //                !event.getCursor().getType().equals(Material.ENCHANTED_BOOK)

        net.minecraft.server.v1_12_R1.ItemStack nmsCurrentItem = CraftItemStack.asNMSCopy(event.getCurrentItem());
        NBTTagCompound tag = nmsCurrentItem.getTag() != null ? nmsCurrentItem.getTag() : new NBTTagCompound();

//        tag.setBoolean("cookedByChef", true);

        nmsCurrentItem.setTag(tag);
//        event.setCurrentItem();


        System.out.println(CraftItemStack.asBukkitCopy(nmsCurrentItem));

    }
}
