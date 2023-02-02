package feudal.listeners.interactListeners.menuListeners;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.PlayerInfo;
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

        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень силы")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getStrengthLvl()) * 100));
            playerInfo.addStrengthLvl(1);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень выносливости")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getStaminaLvl()) * 100));
            playerInfo.addStaminaLvl(1);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень удачи")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getLuckLvl()) * 100));
            playerInfo.addLuckLvl(1);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень живучести")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getSurvivabilityLvl()) * 100));
            playerInfo.addSurvivabilityLvl(1);

            float tmpHealth = playerInfo.getSurvivabilityLvl();
            player.setMaxHealth(20 * (tmpHealth / 100) + 20);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень скорости")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getSpeedLvl()) * 100));
            playerInfo.addSpeedLvl(1);

            float tmpSpeed = playerInfo.getSpeedLvl();
            player.setWalkSpeed(0.2f * (tmpSpeed / 100) + 0.2f);

            player.closeInventory();
            ScoreBoardInfo.createScoreBoardInfo(player);

        }
    }
}
