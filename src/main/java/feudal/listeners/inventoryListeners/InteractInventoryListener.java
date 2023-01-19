package feudal.listeners.inventoryListeners;

import feudal.info.PlayerInfoDB;
import feudal.utils.PlayerGameClass;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractInventoryListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Выберите класс") || event.getCurrentItem().getType() == null) return;

        event.setCancelled(true);

        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getType() == null) return;

        switch (event.getCurrentItem().getType()) {

            case BLACK_SHULKER_BOX:
                playerInfoDB.setField(player, "classID", 1);
                PlayerGameClass.getPlayerInfo().get(player).setaClassID(1);
                player.closeInventory();
                break;

            case WHITE_SHULKER_BOX:
                playerInfoDB.setField(player, "classID", 2);
                PlayerGameClass.getPlayerInfo().get(player).setaClassID(2);
                player.closeInventory();
                break;

            case RED_SHULKER_BOX:
                playerInfoDB.setField(player, "classID", 3);
                PlayerGameClass.getPlayerInfo().get(player).setaClassID(3);
                player.closeInventory();
                break;

            case YELLOW_SHULKER_BOX:
                playerInfoDB.setField(player, "classID", 4);
                PlayerGameClass.getPlayerInfo().get(player).setaClassID(4);
                player.closeInventory();
                break;

            case BLUE_SHULKER_BOX:
                playerInfoDB.setField(player, "classID", 5);
                PlayerGameClass.getPlayerInfo().get(player).setaClassID(5);
                player.closeInventory();
                break;
        }
    }
}
