package feudal.gameClassesListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

public class FishermanExpListener implements Listener {

    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getPlayer());

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.FISHERMAN.getId() || event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
            return;

        feudalPlayer.addExperience(30);
        feudalPlayer.addGameClassExperience(120);

    }
}
