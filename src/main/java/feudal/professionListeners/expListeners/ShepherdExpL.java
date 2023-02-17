package feudal.professionListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.AnimalsForShepherdE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.jetbrains.annotations.NotNull;

public class ShepherdExpL implements Listener {

    @EventHandler
    public final void playerBreed(@NotNull EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (feudalPlayer.getProfessionID() != ProfessionIDE.SHEPHERD.getId()) return;

        int exp = AnimalsForShepherdE.getAttributeExpByEntity(event.getEntityType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }
}
