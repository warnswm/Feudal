package feudal.donateItemsListeners;

import feudal.utils.MathUtils;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class VampirismListener implements Listener {

    @EventHandler
    public void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag() == null ||
                !CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag().getString("donateEnchantment").equals("vampirism") ||
                MathUtils.getRandomInt(1, 5) > 1) return;

        if (player.getMaxHealth() < player.getHealth() + event.getDamage() / 100 * CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag().getByte("donateEnchantmentLvl")) {

            player.setHealth(player.getMaxHealth());
            player.spawnParticle(Particle.REDSTONE, player.getLocation(), 0, 1, 0, 0);

            return;

        }

        player.setHealth(player.getHealth() + event.getDamage() / 100 * CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag().getByte("donateEnchantmentLvl"));
        player.spawnParticle(Particle.REDSTONE, player.getLocation(), 0, 1, 0, 0);

    }

}
