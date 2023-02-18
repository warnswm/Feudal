package feudal.listeners.professionListeners.expListeners;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.CropState;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class FarmerExpL implements Listener {

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (block.getData() != CropState.RIPE.getData() || feudalPlayer.getProfessionID() != ProfessionIDE.FARMER.getId())
            return;

        feudalPlayer.addExperience(1);
        feudalPlayer.addProfessionExperience(3);

    }
}
