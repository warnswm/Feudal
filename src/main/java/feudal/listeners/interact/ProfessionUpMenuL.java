package feudal.listeners.interact;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.cache.CacheFeudalValues;
import feudal.visual.Menus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class ProfessionUpMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null ||
                !event.getView().getTitle().equals("Прокачка профессии")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        Menus menus = new Menus(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":

                menus.attributesUpMenu(feudalPlayer.getStrengthLvl(), "силы", CacheFeudalValues.getStrengthPercentageCost());

                break;

            case "Выносливость":

                menus.attributesUpMenu(feudalPlayer.getStaminaLvl(), "выносливости", CacheFeudalValues.getStaminaPercentageCost());

                break;

            case "Удача":

                menus.attributesUpMenu(feudalPlayer.getLuckLvl(), "удачи", CacheFeudalValues.getLuckPercentageCost());

                break;

            case "Живучесть":

                menus.attributesUpMenu(feudalPlayer.getSurvivabilityLvl(), "живучести", CacheFeudalValues.getSurvivabilityPercentageCost());

                break;

            case "Скорость":

                menus.attributesUpMenu(feudalPlayer.getSpeedLvl(), "скорости", CacheFeudalValues.getSpeedPercentageCost());

                break;

            case "Смена класса":

                menus.switchingProfessionOneStageMenu();

                break;

        }
    }
}
