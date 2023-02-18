package feudal.listeners.professionListeners.expListeners;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

public class FishermanExpL implements Listener {

    @EventHandler
    public final void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getProfessionID() != ProfessionIDE.FISHERMAN.getId() || event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
            return;

        feudalPlayer.addExperience(20);
        feudalPlayer.addProfessionExperience(80);

    }
}
