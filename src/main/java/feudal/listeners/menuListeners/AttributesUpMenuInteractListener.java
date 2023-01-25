package feudal.listeners.menuListeners;

import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
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

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень силы")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getStrengthLvl()) * 100));
            playerInfo.addStrengthLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень выносливости")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getStaminaLvl()) * 100));
            playerInfo.addStaminaLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень удачи")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getLuckLvl()) * 100));
            playerInfo.addLuckLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень живучести")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getSurvivabilityLvl()) * 100));
            playerInfo.addSurvivabilityLvl(1);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Прокачать уровень скорости")) {

            playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getSpeedLvl()) * 100));
            playerInfo.addSpeedLvl(1);

            player.closeInventory();

        }
    }
//    private void upStrength(Player player, String methodName) {
//
//        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);
//
//        Method method;
//        try {
//            method = Class.forName(PlayerInfo.class.getName()).getDeclaredMethod(methodName);
//        } catch (NoSuchMethodException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        method.invoke(PlayerInfo)
//
//        playerInfo.setExperience((int) (playerInfo.getExperience() - Math.pow(1 + 0.05, playerInfo.getStaminaLvl()) * 100));
//        playerInfo.addStaminaLvl(1);
//
//        player.closeInventory();
//    }
}
