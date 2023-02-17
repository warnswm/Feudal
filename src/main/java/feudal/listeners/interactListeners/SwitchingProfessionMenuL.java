package feudal.listeners.interactListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class SwitchingProfessionMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (GeneralMenuL.isaBoolean(event)) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        feudalPlayer.setProfessionID(ProfessionIDE.getIDByString(event.getCurrentItem().getItemMeta().getDisplayName()));
        player.closeInventory();

    }
}
