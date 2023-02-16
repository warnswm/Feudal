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

public class BlindnessL implements Listener {

    private static final FeudalValuesUtils feudalValuesUtils = new FeudalValuesUtils();

    @EventHandler
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (!Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getBoolean("blindness") ||
                MathUtils.getRandomInt(1, 101) > Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("blindnessLvl") * feudalValuesUtils.getBlindnessPercentagePerLvl())
            return;

        LivingEntity entity = (LivingEntity) event.getEntity();
        int blindness = feudalValuesUtils.getBlindnessTime();

        entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (blindness + blindness / 100 * feudalValuesUtils.getBlindnessTimePercentagePerLvl()), 1, true, true));

    }

}
