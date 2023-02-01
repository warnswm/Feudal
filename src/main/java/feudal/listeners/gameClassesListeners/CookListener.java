package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.utils.enums.GameClassesIDEnum;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CookListener implements Listener {

    @EventHandler
    public void playerFriedFood(@NotNull FurnaceSmeltEvent event) {

        if (!Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getSource()).getTag()).getBoolean("cookedByChef"))
            return;

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(event.getResult());
        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        tag.setBoolean("cookedByChef", true);
        nmsItem.setTag(tag);

        event.setResult(CraftItemStack.asBukkitCopy(nmsItem));

    }

    @EventHandler
    public void playerPutFoodFurnace(@NotNull InventoryClickEvent event) {

        if (!event.getInventory().getType().equals(InventoryType.FURNACE) ||
                event.getSlot() != 0 ||
                CachePlayers.getPlayerInfo().get(event.getView().getPlayer()).getAClassID()
                        != GameClassesIDEnum.COOK.getId()) return;

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(event.getCurrentItem());
        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        tag.setBoolean("cookedByChef", true);
        nmsItem.setTag(tag);

        event.getInventory().setItem(0, CraftItemStack.asBukkitCopy(nmsItem));

    }
}
