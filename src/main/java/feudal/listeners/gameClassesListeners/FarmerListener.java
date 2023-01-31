package feudal.listeners.gameClassesListeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class FarmerListener implements Listener {

    @EventHandler
    public void playerUsedBoneMeal(@NotNull PlayerInteractEvent event) {

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
                !event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.INK_SACK) ||
                !event.getClickedBlock().getType().equals(Material.CROPS)) return;

//        event.getClickedBlock().setData((byte) 7);

    }
}
