package feudal.listeners.inventoryListeners;

import feudal.info.PlayerInfo;
import feudal.utils.PlayerGameClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractGameClassChangeMenuListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Выберите класс") || event.getCurrentItem().getType() == null) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        PlayerInfo playerInfo = PlayerGameClass.getPlayerInfo().get(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Строитель":
                playerInfo.setaClassID(1);
                player.closeInventory();
                break;
            case "Повар":
                playerInfo.setaClassID(2);
                player.closeInventory();
                break;
            case "Фермер":
                playerInfo.setaClassID(3);
                player.closeInventory();
                break;
            case "Рыболов":
                playerInfo.setaClassID(4);
                player.closeInventory();
                break;
            case "Охотник":
                playerInfo.setaClassID(5);
                player.closeInventory();
                break;
            case "Шахтёр":
                playerInfo.setaClassID(6);
                player.closeInventory();
                break;
            case "Пастух":
                playerInfo.setaClassID(7);
                player.closeInventory();
                break;
            case "Торговец":
                playerInfo.setaClassID(8);
                player.closeInventory();
                break;
            case "Дровосек":
                playerInfo.setaClassID(9);
                player.closeInventory();
                break;
        }
    }
}
