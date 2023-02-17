package feudal.listeners.generalListeners;

import feudal.utils.enums.MobsAttributesE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class MobL implements Listener {

    @EventHandler
    public final void mobSpawn(@NotNull CreatureSpawnEvent event) {

        if (MobsAttributesE.getHPByEntity(event.getEntityType()) == 0) return;

        event.getEntity().setMaxHealth(MobsAttributesE.getHPByEntity(event.getEntityType()));

    }

    @EventHandler
    public final void mobAttack(@NotNull EntityDamageByEntityEvent event) {

        if (MobsAttributesE.getStrengthByEntity(event.getDamager().getType()) == 0) return;

        event.setDamage(MobsAttributesE.getHPByEntity(event.getDamager().getType()));

    }
}
