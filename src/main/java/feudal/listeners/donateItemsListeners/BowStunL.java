package feudal.listeners.donateItemsListeners;

import feudal.utils.FeudalValuesUtils;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class BowStunL implements Listener {

    @EventHandler
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Arrow)) return;

        Arrow arrow = (Arrow) event.getDamager();
        Player player = (Player) arrow.getShooter();

        if (CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag() == null) return;

        if (!Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getBoolean("bowStun") ||
                ThreadLocalRandom.current().nextInt(1, 101) > Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("bowStunLvl") * FeudalValuesUtils.getBowStunPercentagePerLvl())
            return;

        LivingEntity entity = (LivingEntity) event.getEntity();
        int effectTime = FeudalValuesUtils.getBowStunTime();

        entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (effectTime + effectTime / 100 * FeudalValuesUtils.getBowStunTimePercentagePerLvl()), 1, true, true));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) (effectTime + effectTime / 100 * FeudalValuesUtils.getBowStunTimePercentagePerLvl()), 1, true, true));

    }

}
