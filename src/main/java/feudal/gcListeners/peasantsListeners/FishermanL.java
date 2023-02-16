package feudal.gcListeners.peasantsListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.MathUtils;
import feudal.utils.enums.gcEnums.FishermanLootTableE;
import feudal.utils.enums.gcEnums.GameClassesIDE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

public class FishermanL implements Listener {

    @EventHandler
    public final void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getAClassID() != GameClassesIDE.FISHERMAN.getId() ||
                feudalPlayer.getGameClassLvl() < 25 ||
                event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        int item = MathUtils.getRandomInt(1, 6), random = MathUtils.getRandomInt(1, 8);
        if (random != 1 && random != 2) return;

        event.getPlayer().getInventory().addItem(FishermanLootTableE.getItemByID(item));

    }
}
