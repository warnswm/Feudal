package feudal.listeners.menuListeners;

import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
import feudal.utils.enums.GameClassesIDEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameClassChangeMenuInteractListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Выберите класс")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        playerInfo.setaClassID(GameClassesIDEnum.getByString(event.getCurrentItem().getItemMeta().getDisplayName()));
        player.closeInventory();

    }
}
