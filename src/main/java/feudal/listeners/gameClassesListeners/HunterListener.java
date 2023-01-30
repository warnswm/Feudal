package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.enums.ClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class HunterListener implements Listener {


    @EventHandler
    public void playerHunted(@NotNull EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        if (player == null) return;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != ClassesIDEnum.HUNTER.getId() ||
                playerInfo.getGameClassLvl() < 25) return;


        if (event.getDrops().contains(new ItemStack(Material.RAW_CHICKEN))) {

            event.getDrops().remove(new ItemStack(Material.RAW_CHICKEN));
            event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN));

        } else if (event.getDrops().contains(new ItemStack(Material.RAW_BEEF))) {

            event.getDrops().remove(new ItemStack(Material.RAW_BEEF));
            event.getDrops().add(new ItemStack(Material.COOKED_BEEF));

        } else if (event.getDrops().contains(new ItemStack(Material.RABBIT))) {

            event.getDrops().remove(new ItemStack(Material.RABBIT));
            event.getDrops().add(new ItemStack(Material.COOKED_RABBIT));

        } else if (event.getDrops().contains(new ItemStack(Material.MUTTON))) {

            event.getDrops().remove(new ItemStack(Material.MUTTON));
            event.getDrops().add(new ItemStack(Material.COOKED_MUTTON));

        } else if (event.getDrops().contains(new ItemStack(Material.PORK))) {

            event.getDrops().remove(new ItemStack(Material.PORK));
            event.getDrops().add(new ItemStack(Material.GRILLED_PORK));

        } else if (event.getDrops().contains(new ItemStack(Material.RAW_FISH))) {

            event.getDrops().remove(new ItemStack(Material.RAW_FISH));
            event.getDrops().add(new ItemStack(Material.COOKED_FISH));

        }

    }
}
