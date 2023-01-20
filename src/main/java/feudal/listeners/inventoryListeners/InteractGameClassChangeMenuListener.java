package feudal.listeners.inventoryListeners;

import feudal.info.PlayerInfoCache;
import feudal.utils.PlayerGameClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractGameClassChangeMenuListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Выберите класс")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        PlayerInfoCache playerInfoCache = PlayerGameClass.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Строитель":
                playerInfoCache.setaClassID(1);
                player.closeInventory();
                break;
            case "Повар":
                playerInfoCache.setaClassID(2);
                player.closeInventory();
                break;
            case "Фермер":
                playerInfoCache.setaClassID(3);
                player.closeInventory();
                break;
            case "Рыболов":
                playerInfoCache.setaClassID(4);
                player.closeInventory();
                break;
            case "Охотник":
                playerInfoCache.setaClassID(5);
                player.closeInventory();
                break;
            case "Шахтёр":
                playerInfoCache.setaClassID(6);
                player.closeInventory();
                break;
            case "Пастух":
                playerInfoCache.setaClassID(7);
                player.closeInventory();
                break;
            case "Торговец":
                playerInfoCache.setaClassID(8);
                player.closeInventory();
                break;
            case "Дровосек":
                playerInfoCache.setaClassID(9);
                player.closeInventory();
                break;
        }
    }
}
