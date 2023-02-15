package feudal.gameClassesListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gameClassesEnums.BlocksForMinerEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class MinerExpListener implements Listener {

    @EventHandler
    public final void playerBlockBreak(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();
        if (block.hasMetadata("PLACED")) return;

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.MINER.getId()) return;

        int exp = BlocksForMinerEnum.getAttributeExpByMaterial(block.getType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addGameClassExperience(4 * exp);

    }
}
