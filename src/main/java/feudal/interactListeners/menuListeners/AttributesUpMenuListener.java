package feudal.interactListeners.menuListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class AttributesUpMenuListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка атрибутов")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Прокачать уровень силы":

                feudalPlayer.takeExperience((int) Math.pow(1 + 0.05, feudalPlayer.getStrengthLvl()) * 100);
                feudalPlayer.addStrengthLvl(1);

                player.closeInventory();

                break;

            case "Прокачать уровень выносливости":

                feudalPlayer.takeExperience((int) Math.pow(1 + 0.05, feudalPlayer.getStaminaLvl()) * 100);
                feudalPlayer.addStaminaLvl(1);

                player.closeInventory();

                break;

            case "Прокачать уровень удачи":

                feudalPlayer.takeExperience((int) Math.pow(1 + 0.05, feudalPlayer.getLuckLvl()) * 100);
                feudalPlayer.addLuckLvl(1);

                player.closeInventory();

                break;

            case "Прокачать уровень живучести":

                feudalPlayer.takeExperience((int) Math.pow(1 + 0.05, feudalPlayer.getSurvivabilityLvl()) * 100);
                feudalPlayer.addSurvivabilityLvl(1);

                float tmpHealth = feudalPlayer.getSurvivabilityLvl();
                player.setMaxHealth(20 * (tmpHealth / 100) + 20);

                player.closeInventory();

                break;

            case "Прокачать уровень скорости":

                feudalPlayer.takeExperience((int) Math.pow(1 + 0.05, feudalPlayer.getSpeedLvl()) * 100);
                feudalPlayer.addSpeedLvl(1);

                float tmpSpeed = feudalPlayer.getSpeedLvl();
                player.setWalkSpeed(0.2f * (tmpSpeed / 100) + 0.2f);

                player.closeInventory();

                break;
        }
    }
}
