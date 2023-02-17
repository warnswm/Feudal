package feudal.listeners.interactListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.FeudalValuesUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class AttributesUpMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (GeneralMenuL.isaBoolean(event)) return;

        event.setCancelled(true);


        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Прокачать уровень силы":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (FeudalValuesUtils.getStrengthPercentageCost() / 100), feudalPlayer.getStrengthLvl()) * 100));
                feudalPlayer.addStrengthLvl(1);

                player.closeInventory();

                break;

            case "Прокачать уровень выносливости":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (FeudalValuesUtils.getStaminaPercentageCost() / 100), feudalPlayer.getStaminaLvl()) * 100));
                feudalPlayer.addStaminaLvl(1);

                player.closeInventory();

                break;

            case "Прокачать уровень удачи":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (FeudalValuesUtils.getLuckPercentageCost() / 100), feudalPlayer.getLuckLvl()) * 100));
                feudalPlayer.addLuckLvl(1);

                player.closeInventory();

                break;

            case "Прокачать уровень живучести":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (FeudalValuesUtils.getSurvivabilityPercentageCost() / 100), feudalPlayer.getSurvivabilityLvl()) * 100));
                feudalPlayer.addSurvivabilityLvl(1);

                float tmpHealth = feudalPlayer.getSurvivabilityLvl();
                player.setMaxHealth(20 * (tmpHealth / 100) + 20);

                player.closeInventory();

                break;

            case "Прокачать уровень скорости":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (FeudalValuesUtils.getSpeedPercentageCost() / 100), feudalPlayer.getSpeedLvl()) * 100));
                feudalPlayer.addSpeedLvl(1);

                float tmpSpeed = feudalPlayer.getSpeedLvl();
                player.setWalkSpeed(0.2f * (tmpSpeed / 100) + 0.2f);

                player.closeInventory();

                break;
        }
    }
}
