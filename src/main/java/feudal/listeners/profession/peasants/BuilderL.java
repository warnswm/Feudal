package feudal.listeners.profession.peasants;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.utils.enums.professionEnums.SpawnersForBuilderE;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;

public class BuilderL implements Listener {

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        Block block = event.getBlock();

        if (feudalPlayer.getProfessionID() != ProfessionIDE.BUILDER.getId() ||
                !block.getType().equals(Material.MOB_SPAWNER)) return;


        CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

        if (!SpawnersForBuilderE.canBreak(creatureSpawner.getSpawnedType(), feudalPlayer.getProfessionLvl())) return;

        ItemStack loot = new ItemStack(Material.MOB_SPAWNER);

        BlockStateMeta blockStateMeta = (BlockStateMeta) loot.getItemMeta();
        BlockState blockState = blockStateMeta.getBlockState();
        ((CreatureSpawner) blockState).setSpawnedType(EntityType.valueOf(creatureSpawner.getSpawnedType().getName().toUpperCase()));
        blockStateMeta.setBlockState(blockState);
        loot.setItemMeta(blockStateMeta);

        block.getWorld().dropItemNaturally(block.getLocation(), loot);

    }

}
