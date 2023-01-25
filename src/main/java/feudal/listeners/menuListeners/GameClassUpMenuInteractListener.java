package feudal.listeners.menuListeners;

import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
import feudal.view.menu.AttributesUpMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameClassUpMenuInteractListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка класса")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        AttributesUpMenu attributesUpMenu;
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":
                attributesUpMenu = new AttributesUpMenu(player, playerInfo.getStrengthLvl(), "силы");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Выносливость":
                attributesUpMenu = new AttributesUpMenu(player, playerInfo.getStaminaLvl(), "выносливости");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Удача":
                attributesUpMenu = new AttributesUpMenu(player, playerInfo.getLuckLvl(), "удачи");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Живучесть":
                attributesUpMenu = new AttributesUpMenu(player, playerInfo.getSurvivabilityLvl(), "живучести");
                attributesUpMenu.attributesUpMenu();
                break;
            case "Скорость":
                attributesUpMenu = new AttributesUpMenu(player, playerInfo.getSpeedLvl(), "скорости");
                attributesUpMenu.attributesUpMenu();
                break;
        }
    }
}
