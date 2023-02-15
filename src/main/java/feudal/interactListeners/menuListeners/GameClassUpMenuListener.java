package feudal.interactListeners.menuListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import static feudal.visual.menus.AttributesUpMenu.attributesUpMenu;

public class GameClassUpMenuListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (GeneralMenu.isaBoolean(event)) return;

        event.setCancelled(true);


        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":

                attributesUpMenu(player, feudalPlayer.getStrengthLvl(), "силы", 5);

                break;
            case "Выносливость":

                attributesUpMenu(player, feudalPlayer.getStaminaLvl(), "выносливости", 5);

                break;
            case "Удача":

                attributesUpMenu(player, feudalPlayer.getLuckLvl(), "удачи", 7);

                break;
            case "Живучесть":

                attributesUpMenu(player, feudalPlayer.getSurvivabilityLvl(), "живучести", 5);

                break;
            case "Скорость":

                attributesUpMenu(player, feudalPlayer.getSpeedLvl(), "скорости", 5);

                break;
        }
    }
}
