package feudal.listeners.general;

import feudal.utils.enums.MobsAttributesE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;

public class MobL implements Listener {

    @EventHandler
    public final void mobSpawn(@NotNull CreatureSpawnEvent event) {

        if (MobsAttributesE.getHPByEntity(event.getEntityType()) != 0)
            event.getEntity().setMaxHealth(MobsAttributesE.getHPByEntity(event.getEntityType()));

    }

}
