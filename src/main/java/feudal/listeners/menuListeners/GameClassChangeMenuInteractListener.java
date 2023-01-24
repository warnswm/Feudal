package feudal.listeners.menuListeners;

import feudal.info.CachePlayerInfoBuilder;
import feudal.utils.CachePlayers;
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

        CachePlayerInfoBuilder cachePlayerInfoBuilder = CachePlayers.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Строитель":
                cachePlayerInfoBuilder.setaClassID(1);
                player.closeInventory();
                break;
            case "Повар":
                cachePlayerInfoBuilder.setaClassID(2);
                player.closeInventory();
                break;
            case "Фермер":
                cachePlayerInfoBuilder.setaClassID(3);
                player.closeInventory();
                break;
            case "Рыболов":
                cachePlayerInfoBuilder.setaClassID(4);
                player.closeInventory();
                break;
            case "Охотник":
                cachePlayerInfoBuilder.setaClassID(5);
                player.closeInventory();
                break;
            case "Шахтёр":
                cachePlayerInfoBuilder.setaClassID(6);
                player.closeInventory();
                break;
            case "Пастух":
                cachePlayerInfoBuilder.setaClassID(7);
                player.closeInventory();
                break;
            case "Торговец":
                cachePlayerInfoBuilder.setaClassID(8);
                player.closeInventory();
                break;
            case "Дровосек":
                cachePlayerInfoBuilder.setaClassID(9);
                player.closeInventory();
                break;
        }
    }
}
