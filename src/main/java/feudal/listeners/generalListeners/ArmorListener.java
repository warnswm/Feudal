package feudal.listeners.generalListeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import feudal.utils.enums.ArmorEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class ArmorListener implements Listener {

    @EventHandler
    public void playerArmorChange(@NotNull PlayerArmorChangeEvent event) {

        if (event.getNewItem() == null ||
                ArmorEnum.getByItemMaterial(event.getNewItem().getType()) == 0) return;

        Player player = event.getPlayer();

        player.setWalkSpeed(player.getWalkSpeed() - (player.getWalkSpeed() / 100 * ArmorEnum.getByItemMaterial(event.getNewItem().getType())));
    }
}
