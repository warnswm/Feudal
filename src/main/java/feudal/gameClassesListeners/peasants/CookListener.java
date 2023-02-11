package feudal.gameClassesListeners.peasants;

import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.jetbrains.annotations.NotNull;


public class CookListener implements Listener {
    @EventHandler
    public void playerExtractFurnace(@NotNull FurnaceExtractEvent event) {

        if (CacheFeudalPlayers.getFeudalPlayer(event.getPlayer()).getAClassID()
                != GameClassesIDEnum.COOK.getId()) return;

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(event.getPlayer().getItemOnCursor());

        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        tag.setBoolean("cookedByChef", true);
        tag.setByte("chefLvl", (byte) CacheFeudalPlayers.getFeudalPlayer(event.getPlayer()).getGameClassLvl());

        nmsItem.setTag(tag);

        event.getPlayer().setItemOnCursor(CraftItemStack.asBukkitCopy(nmsItem));

    }
}
