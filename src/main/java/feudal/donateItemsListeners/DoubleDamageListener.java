package feudal.donateItemsListeners;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class DoubleDamageListener implements Listener {

    @EventHandler
    public void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (!CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag().getString("donateEnchantment").equals("doubleDamage")) return;

        player.setHealth(player.getHealth() + CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag().getByte("donateEnchantmentLvl"));

    }
}
