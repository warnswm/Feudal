package feudal.gcListeners.peasantsListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gcEnums.GameClassesIDE;
import feudal.utils.enums.gcEnums.SpawnersForBuilderE;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BuilderListener implements Listener {

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        Block block = event.getBlock();

        if (feudalPlayer.getAClassID() != GameClassesIDE.BUILDER.getId() ||
                !block.getType().equals(Material.MOB_SPAWNER)) return;

        CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

        if (!SpawnersForBuilderE.canBreak(creatureSpawner.getSpawnedType(), feudalPlayer.getGameClassLvl())) {
        }

//        block.getWorld().dropItemNaturally(block.getLocation(), );

    }
}
