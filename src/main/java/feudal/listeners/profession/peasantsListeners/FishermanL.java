package feudal.listeners.profession.peasantsListeners;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.FishermanLootTableE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class FishermanL implements Listener {

    @EventHandler
    public final void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (feudalPlayer.getProfessionID() != ProfessionIDE.FISHERMAN.getId() ||
                feudalPlayer.getProfessionLvl() < 25 ||
                event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        int item = ThreadLocalRandom.current().nextInt(1, 6), random = ThreadLocalRandom.current().nextInt(1, 8);
        if (random != 1 && random != 2) return;

        event.getPlayer().getInventory().addItem(FishermanLootTableE.getItemByID(item));

    }
}
