package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.enums.ClassesIDEnum;
import lombok.val;
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

        val drops = event.getDrops();

        if (drops.contains(new ItemStack(Material.RAW_CHICKEN))) {

            drops.remove(new ItemStack(Material.RAW_CHICKEN));
            drops.add(new ItemStack(Material.COOKED_CHICKEN));

        } else if (drops.contains(new ItemStack(Material.RAW_BEEF))) {

            drops.remove(new ItemStack(Material.RAW_BEEF));
            drops.add(new ItemStack(Material.COOKED_BEEF));

        } else if (drops.contains(new ItemStack(Material.RABBIT))) {

            drops.remove(new ItemStack(Material.RABBIT));
            drops.add(new ItemStack(Material.COOKED_RABBIT));

        } else if (drops.contains(new ItemStack(Material.MUTTON))) {

            drops.remove(new ItemStack(Material.MUTTON));
            drops.add(new ItemStack(Material.COOKED_MUTTON));

        } else if (drops.contains(new ItemStack(Material.PORK))) {

            drops.remove(new ItemStack(Material.PORK));
            drops.add(new ItemStack(Material.GRILLED_PORK));

        } else if (drops.contains(new ItemStack(Material.RAW_FISH))) {

            drops.remove(new ItemStack(Material.RAW_FISH));
            drops.add(new ItemStack(Material.COOKED_FISH));

        }

    }
}
