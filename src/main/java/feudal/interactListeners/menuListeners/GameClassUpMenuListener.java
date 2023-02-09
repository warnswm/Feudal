package feudal.interactListeners.menuListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class GameClassUpMenuListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Прокачка класса")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

            case "Сила":

                attributesUpMenu(player, feudalPlayer.getStrengthLvl(), "силы", 5);

                break;
            case "Выносливость":

                attributesUpMenu(player, feudalPlayer.getStaminaLvl(), "выносливости", 5);

                break;
            case "Удача":

                attributesUpMenu(player, feudalPlayer.getLuckLvl(), "удачи", 7);

                break;
            case "Живучесть":

                attributesUpMenu(player, feudalPlayer.getSurvivabilityLvl(), "живучести", 5);

                break;
            case "Скорость":

                attributesUpMenu(player, feudalPlayer.getSpeedLvl(), "скорости", 5);

                break;
        }
    }

    public void attributesUpMenu(Player player, int attributeLvl, String attributeName, float percent) {

        Inventory strengthPumpingMenuInv = Bukkit.createInventory(player, 9, "Прокачка атрибутов");

        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

        if (feudalPlayer.getExperience() >= Math.pow(1 + (percent / 100), attributeLvl) * 100)
            strengthPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GREEN_SHULKER_BOX, 1, "Прокачать уровень " + attributeName));
        else
            strengthPumpingMenuInv.setItem(4, CreateItemUtils.createItem(Material.GRAY_SHULKER_BOX, 1, "Не достаточно опыта, нужно ещё " + (Math.pow(1 + 0.05, attributeLvl) * 100 - feudalPlayer.getExperience())));


        player.openInventory(strengthPumpingMenuInv);
    }
}
