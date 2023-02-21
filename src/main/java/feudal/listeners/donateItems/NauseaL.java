package feudal.listeners.donateItems;

import feudal.data.DonatEnchantment;
import feudal.data.cache.CacheFeudalValues;
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
import java.util.concurrent.ThreadLocalRandom;

public class NauseaL implements Listener {

    @EventHandler
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        DonatEnchantment donatEnchantment = CacheFeudalValues.getDonatEnchantment().get("nausea");

        if (CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag() == null ||
                !Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getBoolean("nausea") ||
                ThreadLocalRandom.current().nextInt(1, 101) > Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("nauseaLvl") * donatEnchantment.getPercentagePerLvl())
            return;

        LivingEntity entity = (LivingEntity) event.getEntity();
        int nausea = donatEnchantment.getTime();

        entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (nausea + nausea / 100 * donatEnchantment.getTimePercentagePerLvl()), 1, true, true));

    }

}
