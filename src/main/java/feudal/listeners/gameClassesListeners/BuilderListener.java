package feudal.listeners.gameClassesListeners;

import feudal.data.cache.CachePlayers;
import feudal.data.database.PlayerInfo;
import feudal.utils.enums.GameClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BuilderListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());
        Block block = event.getBlock();

        if (playerInfo.getAClassID() != GameClassesIDEnum.BUILDER.getId() &&
                !block.getType().equals(Material.MOB_SPAWNER)) return;

//      block.getWorld().dropItemNaturally(block.getLocation(), block)

    }
}
