package feudal.gameClassesListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.AnimalsForHuntedEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class HunterExpListener implements Listener {

    @EventHandler
    public void playerHunted(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.HUNTER.getId()) return;

        int exp = AnimalsForHuntedEnum.getByEntity(event.getEntityType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addGameClassExperience(exp * 4);

    }
}
