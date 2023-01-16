package feudal.listeners.inventoryListeners;

import feudal.statistics.PlayerStatistics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractInventoryListener implements Listener {

    @EventHandler
    public void interactInventory(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Выберите класс")) return;

        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        PlayerStatistics playerStatistics = new PlayerStatistics(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        Player player = (Player) event.getView().getPlayer();

        switch (event.getCurrentItem().getType()){

            case BLACK_SHULKER_BOX:
                playerStatistics.createNewPlayer(player, (byte) 6);

            case WHITE_SHULKER_BOX:
                playerStatistics.createNewPlayer(player, (byte) 8);

            case RED_SHULKER_BOX:
                playerStatistics.createNewPlayer(player, (byte) 2);

            case YELLOW_SHULKER_BOX:
                playerStatistics.createNewPlayer(player, (byte) 7);

            case BLUE_SHULKER_BOX:
                playerStatistics.createNewPlayer(player, (byte) 12);

        }
    }
}
