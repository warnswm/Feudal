package feudal.gcListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gcEnums.GameClassesIDE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

public class FishermanExpListener implements Listener {

    @EventHandler
    public final void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getAClassID() != GameClassesIDE.FISHERMAN.getId() || event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
            return;

        feudalPlayer.addExperience(20);
        feudalPlayer.addGameClassExperience(80);

    }
}