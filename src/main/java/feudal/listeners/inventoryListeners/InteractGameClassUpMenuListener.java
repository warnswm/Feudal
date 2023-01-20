package feudal.listeners.inventoryListeners;

import feudal.info.PlayerInfoCache;
import feudal.utils.PlayerGameClass;
import feudal.view.menu.AttributesUpMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractGameClassUpMenuListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка класса")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        AttributesUpMenu attributesUpMenu;
        PlayerInfoCache playerInfoCache = PlayerGameClass.getPlayerInfo().get(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":
                attributesUpMenu = new AttributesUpMenu(player, playerInfoCache.getStrengthLvl(), "силы");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Выносливость":
                attributesUpMenu = new AttributesUpMenu(player, playerInfoCache.getStaminaLvl(), "выносливости");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Удача":
                attributesUpMenu = new AttributesUpMenu(player, playerInfoCache.getLuckLvl(), "удачи");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Живучесть":
                attributesUpMenu = new AttributesUpMenu(player, playerInfoCache.getSurvivabilityLvl(), "живучести");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Скорость":
                attributesUpMenu = new AttributesUpMenu(player, playerInfoCache.getSpeedLvl(), "скорости");
                attributesUpMenu.attributesUpMenu();
                break;
        }
    }
}
