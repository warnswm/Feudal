package feudal.listeners.interact;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.cache.CacheFeudalValues;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class AttributesUpMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null ||
                !event.getView().getTitle().equals("Прокачка атрибутов")) return;

        event.setCancelled(true);


        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Прокачать уровень силы":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (CacheFeudalValues.getStrengthPercentageCost() / 100), feudalPlayer.getStrengthLvl()) * 100));
                feudalPlayer.addStrengthLvl(1);

                break;

            case "Прокачать уровень выносливости":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (CacheFeudalValues.getStaminaPercentageCost() / 100), feudalPlayer.getStaminaLvl()) * 100));
                feudalPlayer.addStaminaLvl(1);

                break;

            case "Прокачать уровень удачи":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (CacheFeudalValues.getLuckPercentageCost() / 100), feudalPlayer.getLuckLvl()) * 100));
                feudalPlayer.addLuckLvl(1);

                break;

            case "Прокачать уровень живучести":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (CacheFeudalValues.getSurvivabilityPercentageCost() / 100), feudalPlayer.getSurvivabilityLvl()) * 100));
                feudalPlayer.addSurvivabilityLvl(1);

                float tmpHealth = feudalPlayer.getSurvivabilityLvl();
                player.setMaxHealth(20 * (tmpHealth / 100) + 20);

                break;

            case "Прокачать уровень скорости":

                feudalPlayer.takeExperience((int) (Math.pow(1 + (CacheFeudalValues.getSpeedPercentageCost() / 100), feudalPlayer.getSpeedLvl()) * 100));
                feudalPlayer.addSpeedLvl(1);

                float tmpSpeed = feudalPlayer.getSpeedLvl();
                player.setWalkSpeed(0.2f * (tmpSpeed / 100) + 0.2f);

                break;

        }

        player.closeInventory();

    }
}
