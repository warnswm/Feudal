package feudal.professionListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.LOG;

public class WoodcutterExpL implements Listener {

    @EventHandler
    public final void playerBlockBreak(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (block.hasMetadata("PLACED") ||
                feudalPlayer.getAClassID() != ProfessionIDE.WOODCUTTER.getId() ||
                !block.getType().equals(LOG)) return;

        feudalPlayer.addExperience(2);
        feudalPlayer.addGameClassExperience(8);

    }
}
