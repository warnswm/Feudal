package feudal.generalListeners;

import feudal.utils.enums.MobsAttributesEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class MobListener implements Listener {

    @EventHandler
    public void mobSpawn(@NotNull CreatureSpawnEvent event) {

        if (MobsAttributesEnum.getHPByEntity(event.getEntityType()) == 0) return;

        event.getEntity().setMaxHealth(MobsAttributesEnum.getHPByEntity(event.getEntityType()));

    }

    @EventHandler
    public void mobAttack(@NotNull EntityDamageByEntityEvent event) {

        if (MobsAttributesEnum.getStrengthByEntity(event.getDamager().getType()) == 0) return;

        event.setDamage(MobsAttributesEnum.getHPByEntity(event.getDamager().getType()));

    }
}
