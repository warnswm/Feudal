package feudal.gameClassesListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.BlocksForMinerEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class MinerExpListener implements Listener {

    @EventHandler
    public void playerBlockBreak(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();

        if (block.hasMetadata("PLACED")) return;

        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.MINER.getId()) return;

        int exp = BlocksForMinerEnum.getByMaterial(block.getType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addGameClassExperience(exp * 4);

    }
}
