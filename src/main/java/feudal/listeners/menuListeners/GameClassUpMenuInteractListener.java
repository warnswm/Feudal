package feudal.listeners.menuListeners;

import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
import feudal.utils.CreateItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GameClassUpMenuInteractListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка класса")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":
                attributesUpMenu(player, playerInfo.getStrengthLvl(), "силы", 5);
                break;
            case "Выносливость":
                attributesUpMenu(player, playerInfo.getStaminaLvl(), "выносливости", 5);
                break;
            case "Удача":
                attributesUpMenu(player, playerInfo.getLuckLvl(), "удачи", 7);
                break;
            case "Живучесть":
                attributesUpMenu(player, playerInfo.getSurvivabilityLvl(), "живучести", 5);
                break;
            case "Скорость":
                attributesUpMenu(player, playerInfo.getSpeedLvl(), "скорости", 5);
                break;
        }
    }
    public void attributesUpMenu(Player player, int attributeLvl, String attributeName, float percent) {

        Inventory strengthPumpingMenuInv = Bukkit.createInventory(player, 9, "Прокачка атрибутов");

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getExperience() >= Math.pow(1 + (percent/100), attributeLvl) * 100)
            strengthPumpingMenuInv.setItem(4, CreateItemUtil.createItem(Material.GREEN_SHULKER_BOX, 1, "Прокачать уровень " + attributeName));
        else
            strengthPumpingMenuInv.setItem(4, CreateItemUtil.createItem(Material.GRAY_SHULKER_BOX, 1, "Не достаточно опыта, нужно ещё " + (Math.pow(1 + 0.05, attributeLvl) * 100 - playerInfo.getExperience())));


        player.openInventory(strengthPumpingMenuInv);
    }
}
