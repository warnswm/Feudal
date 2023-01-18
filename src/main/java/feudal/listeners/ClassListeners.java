package feudal.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ClassListeners implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

//        if () return;

        Player player = event.getPlayer();


    }
}
