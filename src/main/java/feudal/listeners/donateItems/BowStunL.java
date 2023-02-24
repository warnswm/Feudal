package feudal.listeners.donateItems;

import feudal.data.DonatEnchantment;
import feudal.data.cache.CacheFeudalValues;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class BowStunL extends Enchantment implements Listener {

    private final DonatEnchantment donatEnchantment = CacheFeudalValues.getDonatEnchantment().get("bowStun");

    public BowStunL(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return donatEnchantment.getName();
    }

    @Override
    public int getMaxLevel() {
        return donatEnchantment.getMaxLvl();
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return donatEnchantment.getEnchantmentTarget();
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }

    @EventHandler
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Arrow)) return;

        Arrow arrow = (Arrow) event.getDamager();
        Player player = (Player) arrow.getShooter();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null ||
                !item.containsEnchantment(new BowStunL(73)) ||
                ThreadLocalRandom.current().nextInt(1, 101) > item.getEnchantmentLevel(new BowStunL(73)) * donatEnchantment.getPercentagePerLvl())
            return;


        LivingEntity entity = (LivingEntity) event.getEntity();
        int effectTime = donatEnchantment.getTime();

        entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (effectTime + effectTime / 100 * donatEnchantment.getTimePercentagePerLvl()), 1, true, true));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) (effectTime + effectTime / 100 * donatEnchantment.getTimePercentagePerLvl()), 255, true, true));

    }

}
