package feudal.donateItemsListeners;

import feudal.utils.FeudalValuesUtils;
import feudal.utils.MathUtils;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class VampirismL implements Listener {

    @EventHandler
    public final void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag() == null) return;

        if (CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag() == null ||
                !Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getBoolean("vampirism") ||
                MathUtils.getRandomInt(1, 101) > Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("vampirismLvl") * FeudalValuesUtils.getVampirismMaxLvl())
            return;


        double vampirismLvl = FeudalValuesUtils.getVampirismMaxLvl() * Objects.requireNonNull(CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()).getTag()).getInt("vampirismEnchantmentLvl");

        double health = player.getMaxHealth() < player.getHealth() + event.getDamage() / 100 * vampirismLvl ?
                20 : player.getHealth() + event.getDamage() / 100 * vampirismLvl;

        player.setHealth(health);

    }

}
