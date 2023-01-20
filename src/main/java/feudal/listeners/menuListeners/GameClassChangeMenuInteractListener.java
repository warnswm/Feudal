package feudal.listeners.menuListeners;

import feudal.info.CachePlayerInfo;
import feudal.utils.CachePlayersHashMap;
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

        CachePlayerInfo cachePlayerInfo = CachePlayersHashMap.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Строитель":
                cachePlayerInfo.setaClassID(1);
                player.closeInventory();
                break;
            case "Повар":
                cachePlayerInfo.setaClassID(2);
                player.closeInventory();
                break;
            case "Фермер":
                cachePlayerInfo.setaClassID(3);
                player.closeInventory();
                break;
            case "Рыболов":
                cachePlayerInfo.setaClassID(4);
                player.closeInventory();
                break;
            case "Охотник":
                cachePlayerInfo.setaClassID(5);
                player.closeInventory();
                break;
            case "Шахтёр":
                cachePlayerInfo.setaClassID(6);
                player.closeInventory();
                break;
            case "Пастух":
                cachePlayerInfo.setaClassID(7);
                player.closeInventory();
                break;
            case "Торговец":
                cachePlayerInfo.setaClassID(8);
                player.closeInventory();
                break;
            case "Дровосек":
                cachePlayerInfo.setaClassID(9);
                player.closeInventory();
                break;
        }
    }
}
