package feudal.gameClassesListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.CropState;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class FarmerExpListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();
        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(event.getPlayer());

        if (block.getData() != CropState.RIPE.getData() || feudalPlayer.getAClassID() != GameClassesIDEnum.FARMER.getId())
            return;

        feudalPlayer.addExperience(1);
        feudalPlayer.addGameClassExperience(3);

    }
}
