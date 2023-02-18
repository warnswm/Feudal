package feudal.listeners.interactListeners;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.FeudalValuesUtils;
import feudal.visual.Menus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class ProfessionUpMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (GeneralMenuL.isaBoolean(event)) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        Menus menus = new Menus(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":

                menus.attributesUpMenu(feudalPlayer.getStrengthLvl(), "силы", FeudalValuesUtils.getStrengthPercentageCost());

                break;

            case "Выносливость":

                menus.attributesUpMenu(feudalPlayer.getStaminaLvl(), "выносливости", FeudalValuesUtils.getStaminaPercentageCost());

                break;

            case "Удача":

                menus.attributesUpMenu(feudalPlayer.getLuckLvl(), "удачи", FeudalValuesUtils.getLuckPercentageCost());

                break;

            case "Живучесть":

                menus.attributesUpMenu(feudalPlayer.getSurvivabilityLvl(), "живучести", FeudalValuesUtils.getSurvivabilityPercentageCost());

                break;

            case "Скорость":

                menus.attributesUpMenu(feudalPlayer.getSpeedLvl(), "скорости", FeudalValuesUtils.getSpeedPercentageCost());

                break;

            case "Смена класса":

                menus.switchingProfession();

                break;

        }
    }
}
