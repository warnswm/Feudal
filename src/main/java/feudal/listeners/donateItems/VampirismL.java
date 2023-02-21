package feudal.listeners.donateItems;

import feudal.data.DonatEnchantment;
import feudal.data.cache.CacheFeudalValues;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class VampirismL implements Listener {

    @EventHandler
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        DonatEnchantment donatEnchantment = CacheFeudalValues.getDonatEnchantment().get("vampirism");

        if (CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag() == null ||
                !Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getBoolean("vampirism") ||
                ThreadLocalRandom.current().nextInt(1, 101) > Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("vampirismLvl") * donatEnchantment.getPercentagePerLvl())
            return;


        double vampirismLvl = Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("vampirismEnchantmentLvl");

        double health = player.getMaxHealth() < player.getHealth() + event.getDamage() / 100 * vampirismLvl ?
                20 : player.getHealth() + event.getDamage() / 100 * vampirismLvl;

        player.setHealth(health);

    }

}
