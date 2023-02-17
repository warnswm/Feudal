package feudal.listeners.interactListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.FeudalValuesUtils;
import feudal.visual.menus.SwitchingProfessionMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import static feudal.visual.menus.AttributesUpMenu.attributesUpMenu;

public class ProfessionUpMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (GeneralMenuL.isaBoolean(event)) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":

                attributesUpMenu(player, feudalPlayer.getStrengthLvl(), "силы", FeudalValuesUtils.getStrengthPercentageCost());

                break;

            case "Выносливость":

                attributesUpMenu(player, feudalPlayer.getStaminaLvl(), "выносливости", FeudalValuesUtils.getStaminaPercentageCost());

                break;

            case "Удача":

                attributesUpMenu(player, feudalPlayer.getLuckLvl(), "удачи", FeudalValuesUtils.getLuckPercentageCost());

                break;

            case "Живучесть":

                attributesUpMenu(player, feudalPlayer.getSurvivabilityLvl(), "живучести", FeudalValuesUtils.getSurvivabilityPercentageCost());

                break;

            case "Скорость":

                attributesUpMenu(player, feudalPlayer.getSpeedLvl(), "скорости", FeudalValuesUtils.getSpeedPercentageCost());

                break;

            case "Смена класса":

                SwitchingProfessionMenu.switchingProfession(player);

                break;

        }
    }
}
