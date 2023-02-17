package feudal.professionListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.BlocksForMinerE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class MinerExpL implements Listener {

    @EventHandler
    public final void playerBlockBreak(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();
        if (block.hasMetadata("PLACED")) return;

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getProfessionID() != ProfessionIDE.MINER.getId()) return;

        int exp = BlocksForMinerE.getAttributeExpByMaterial(block.getType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }
}
