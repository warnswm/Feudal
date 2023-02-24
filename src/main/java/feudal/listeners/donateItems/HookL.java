package feudal.listeners.donateItems;

import feudal.data.DonatEnchantment;
import feudal.data.cache.CacheFeudalValues;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class HookL extends Enchantment implements Listener {

    private final DonatEnchantment donatEnchantment = CacheFeudalValues.getDonatEnchantment().get("hook");

    public HookL(int id) {
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
                !item.containsEnchantment(new HookL(76)) ||
                ThreadLocalRandom.current().nextInt(1, 101) > item.getEnchantmentLevel(new HookL(76)) * donatEnchantment.getPercentagePerLvl())
            return;

        event.getEntity().teleport(player);

    }

}
