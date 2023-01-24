package feudal.listeners.menuListeners;

import feudal.info.CachePlayerInfoBuilder;
import feudal.utils.CachePlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AttributesUpMenuInteractListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка атрибутов")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        CachePlayerInfoBuilder cachePlayerInfoBuilder = CachePlayers.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень силы")) {

            cachePlayerInfoBuilder.setExperience((int) (cachePlayerInfoBuilder.getExperience() - Math.pow(1 + 0.05, cachePlayerInfoBuilder.getStrengthLvl()) * 100));
            cachePlayerInfoBuilder.addStrengthLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень выносливости")) {

            cachePlayerInfoBuilder.setExperience((int) (cachePlayerInfoBuilder.getExperience() - Math.pow(1 + 0.05, cachePlayerInfoBuilder.getStaminaLvl()) * 100));
            cachePlayerInfoBuilder.addStaminaLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень удачи")) {

            cachePlayerInfoBuilder.setExperience((int) (cachePlayerInfoBuilder.getExperience() - Math.pow(1 + 0.05, cachePlayerInfoBuilder.getLuckLvl()) * 100));
            cachePlayerInfoBuilder.addLuckLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень живучести")) {

            cachePlayerInfoBuilder.setExperience((int) (cachePlayerInfoBuilder.getExperience() - Math.pow(1 + 0.05, cachePlayerInfoBuilder.getSurvivabilityLvl()) * 100));
            cachePlayerInfoBuilder.addSurvivabilityLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень скорости")) {

            cachePlayerInfoBuilder.setExperience((int) (cachePlayerInfoBuilder.getExperience() - Math.pow(1 + 0.05, cachePlayerInfoBuilder.getSpeedLvl()) * 100));
            cachePlayerInfoBuilder.addSpeedLvl(1);

            player.closeInventory();

        }
    }
}
