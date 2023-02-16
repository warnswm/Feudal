package feudal.gcListeners.peasantsListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gcEnums.GameClassesIDE;
import feudal.utils.enums.gcEnums.SpawnersForBuilderE;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BuilderListener implements Listener {

    private static boolean isBlockEntity(@NotNull BlockPlaceEvent event, @NotNull FeudalPlayer feudalPlayer, Block block) {
        return feudalPlayer.getAClassID() != GameClassesIDE.BUILDER.getId() ||
                !block.getType().equals(Material.MOB_SPAWNER) ||
                CraftItemStack.asNMSCopy(event.getItemInHand()).getTag() == null ||
                Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItemInHand()).getTag()).getString("BlockEntity") == null;
    }

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        Block block = event.getBlock();

        if (feudalPlayer.getAClassID() != GameClassesIDE.BUILDER.getId() ||
                !block.getType().equals(Material.MOB_SPAWNER)) return;

        CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

        if (!SpawnersForBuilderE.canBreak(creatureSpawner.getSpawnedType(), feudalPlayer.getGameClassLvl())) return;


        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(new ItemStack(Material.MOB_SPAWNER, 1));

        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        tag.setString("BlockEntity", creatureSpawner.getSpawnedType().getName().toUpperCase());

        nmsItem.setTag(tag);

        block.getWorld().dropItemNaturally(block.getLocation(), CraftItemStack.asBukkitCopy(nmsItem));

        System.out.println(block.hasMetadata("PLACED"));

    }

    @EventHandler
    public final void playerPlaceBLock(@NotNull BlockPlaceEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        Block block = event.getBlock();

        if (isBlockEntity(event, feudalPlayer, block)) return;

        ((CreatureSpawner) block.getState()).setSpawnedType(EntityType.valueOf(Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItemInHand()).getTag()).getString("BlockEntity")));

    }
}
