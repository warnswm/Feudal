package feudal.interactListeners.menuListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class GameClassChangeMenuListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Выберите класс")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        feudalPlayer.setaClassID(GameClassesIDEnum.getByString(event.getCurrentItem().getItemMeta().getDisplayName()));
        player.closeInventory();

    }
}
