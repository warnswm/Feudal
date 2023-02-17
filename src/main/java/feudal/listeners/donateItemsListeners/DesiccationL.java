package feudal.listeners.donateItemsListeners;

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

public class DesiccationL implements Listener {

    @EventHandler
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag() == null) return;

        if (!Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getBoolean("desiccation") ||
                MathUtils.getRandomInt(1, 101) > Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("desiccationLvl") * FeudalValuesUtils.getDesiccationPercentagePerLvl())
            return;


        LivingEntity entity = (LivingEntity) event.getEntity();
        int desiccation = FeudalValuesUtils.getDesiccationTime();

        entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (int) (desiccation + desiccation / 100 * FeudalValuesUtils.getDesiccationTimePercentagePerLvl()), 1, true, true));

    }

}
