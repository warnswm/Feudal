package feudal.listeners.inventoryListeners;

import feudal.info.PlayerInfoCache;
import feudal.utils.PlayerGameClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractAttributesUpMenuListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка атрибутов")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        PlayerInfoCache playerInfoCache = PlayerGameClass.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень силы")) {

            playerInfoCache.setExperience((int) (playerInfoCache.getExperience() - Math.pow(1 + 0.05, playerInfoCache.getStrengthLvl()) * 100));
            playerInfoCache.addStrengthLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень выносливости")) {

            playerInfoCache.setExperience((int) (playerInfoCache.getExperience() - Math.pow(1 + 0.05, playerInfoCache.getStaminaLvl()) * 100));
            playerInfoCache.addStaminaLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень удачи")) {

            playerInfoCache.setExperience((int) (playerInfoCache.getExperience() - Math.pow(1 + 0.05, playerInfoCache.getLuckLvl()) * 100));
            playerInfoCache.addLuckLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень живучести")) {

            playerInfoCache.setExperience((int) (playerInfoCache.getExperience() - Math.pow(1 + 0.05, playerInfoCache.getSurvivabilityLvl()) * 100));
            playerInfoCache.addSurvivabilityLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень скорости")) {

            playerInfoCache.setExperience((int) (playerInfoCache.getExperience() - Math.pow(1 + 0.05, playerInfoCache.getSpeedLvl()) * 100));
            playerInfoCache.addSpeedLvl(1);

            player.closeInventory();

        }
    }
}
