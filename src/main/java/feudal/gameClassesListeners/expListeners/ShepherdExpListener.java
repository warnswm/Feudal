package feudal.gameClassesListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.AnimalsForShepherdEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.jetbrains.annotations.NotNull;

public class ShepherdExpListener implements Listener {

    @EventHandler
    public void playerBreed(@NotNull EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();
        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.SHEPHERD.getId()) return;

        feudalPlayer.addExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()));
        feudalPlayer.addGameClassExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()) * 4);

    }
}
