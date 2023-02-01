package feudal.listeners.generalListeners;

import feudal.utils.enums.MobsAttributes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class MobListener implements Listener {

    @EventHandler
    public void mobSpawn(@NotNull CreatureSpawnEvent event) {

        if (MobsAttributes.getHPByEntity(event.getEntityType()) == 0) return;

        event.getEntity().setMaxHealth(MobsAttributes.getHPByEntity(event.getEntityType()));

    }

    @EventHandler
    public void mobAttack(@NotNull EntityDamageByEntityEvent event) {

        if (MobsAttributes.getStrengthByEntity(event.getDamager().getType()) == 0) return;

        event.setDamage(MobsAttributes.getHPByEntity(event.getDamager().getType()));

    }
}
