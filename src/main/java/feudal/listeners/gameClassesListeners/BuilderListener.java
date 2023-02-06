package feudal.listeners.gameClassesListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BuilderListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getPlayer());
        Block block = event.getBlock();

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.BUILDER.getId() &&
                !block.getType().equals(Material.MOB_SPAWNER)) {
        }


//      block.getWorld().dropItemNaturally(block.getLocation(), block);

    }
}
