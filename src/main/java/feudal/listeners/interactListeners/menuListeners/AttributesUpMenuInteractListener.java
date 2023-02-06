package feudal.listeners.interactListeners.menuListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.view.ScoreBoardInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class AttributesUpMenuInteractListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка атрибутов")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень силы")) {

            feudalPlayer.setExperience((int) (feudalPlayer.getExperience() - Math.pow(1 + 0.05, feudalPlayer.getStrengthLvl()) * 100));
            feudalPlayer.addStrengthLvl(1);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень выносливости")) {

            feudalPlayer.setExperience((int) (feudalPlayer.getExperience() - Math.pow(1 + 0.05, feudalPlayer.getStaminaLvl()) * 100));
            feudalPlayer.addStaminaLvl(1);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень удачи")) {

            feudalPlayer.setExperience((int) (feudalPlayer.getExperience() - Math.pow(1 + 0.05, feudalPlayer.getLuckLvl()) * 100));
            feudalPlayer.addLuckLvl(1);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень живучести")) {

            feudalPlayer.setExperience((int) (feudalPlayer.getExperience() - Math.pow(1 + 0.05, feudalPlayer.getSurvivabilityLvl()) * 100));
            feudalPlayer.addSurvivabilityLvl(1);

            float tmpHealth = feudalPlayer.getSurvivabilityLvl();
            player.setMaxHealth(20 * (tmpHealth / 100) + 20);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень скорости")) {

            feudalPlayer.setExperience((int) (feudalPlayer.getExperience() - Math.pow(1 + 0.05, feudalPlayer.getSpeedLvl()) * 100));
            feudalPlayer.addSpeedLvl(1);

            float tmpSpeed = feudalPlayer.getSpeedLvl();
            player.setWalkSpeed(0.2f * (tmpSpeed / 100) + 0.2f);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        }
    }
}
