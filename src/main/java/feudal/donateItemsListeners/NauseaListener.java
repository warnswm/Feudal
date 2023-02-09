package feudal.donateItemsListeners;

import feudal.utils.FeudalValuesUtils;
import feudal.utils.MathUtils;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NauseaListener implements Listener {

    @EventHandler
    public void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (!Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getBoolean("nausea") ||
                MathUtils.getRandomInt(1, 101) >
                        Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("nauseaLvl") *
                                FeudalValuesUtils.nauseaPercentagePerLvl) return;

        LivingEntity entity = (LivingEntity) event.getEntity();
        int nausea = FeudalValuesUtils.nauseaTime;

        entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (nausea + nausea / 100 * FeudalValuesUtils.nauseaTimePercentagePerLvl), 1, true, true));

    }

}
