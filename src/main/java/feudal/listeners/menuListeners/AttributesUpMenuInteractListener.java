package feudal.listeners.menuListeners;

import feudal.info.CachePlayerInfo;
import feudal.utils.CachePlayersHashMap;
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

        CachePlayerInfo cachePlayerInfo = CachePlayersHashMap.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень силы")) {

            cachePlayerInfo.setExperience((int) (cachePlayerInfo.getExperience() - Math.pow(1 + 0.05, cachePlayerInfo.getStrengthLvl()) * 100));
            cachePlayerInfo.addStrengthLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень выносливости")) {

            cachePlayerInfo.setExperience((int) (cachePlayerInfo.getExperience() - Math.pow(1 + 0.05, cachePlayerInfo.getStaminaLvl()) * 100));
            cachePlayerInfo.addStaminaLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень удачи")) {

            cachePlayerInfo.setExperience((int) (cachePlayerInfo.getExperience() - Math.pow(1 + 0.05, cachePlayerInfo.getLuckLvl()) * 100));
            cachePlayerInfo.addLuckLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень живучести")) {

            cachePlayerInfo.setExperience((int) (cachePlayerInfo.getExperience() - Math.pow(1 + 0.05, cachePlayerInfo.getSurvivabilityLvl()) * 100));
            cachePlayerInfo.addSurvivabilityLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень скорости")) {

            cachePlayerInfo.setExperience((int) (cachePlayerInfo.getExperience() - Math.pow(1 + 0.05, cachePlayerInfo.getSpeedLvl()) * 100));
            cachePlayerInfo.addSpeedLvl(1);

            player.closeInventory();

        }
    }
}
