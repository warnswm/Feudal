package feudal.professionListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.AnimalsForHuntedE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class HunterExpL implements Listener {

    @EventHandler
    public final void playerHunted(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (feudalPlayer.getProfessionID() != ProfessionIDE.HUNTER.getId()) return;

        int exp = AnimalsForHuntedE.getAttributeExpByEntity(event.getEntityType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }
}
