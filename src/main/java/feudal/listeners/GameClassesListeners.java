package feudal.listeners;

import feudal.info.CachePlayerInfo;
import feudal.utils.CachePlayersHashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GameClassesListeners implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        CachePlayerInfo cachePlayerInfo = CachePlayersHashMap.getPlayerInfo().get(event.getPlayer());

        if (cachePlayerInfo.getLuckLvl() == 100 ||
                cachePlayerInfo.getStrengthLvl() == 100 ||
                cachePlayerInfo.getAClassID() != 6) return;

        switch (event.getBlock().getType()) {

            case COAL_ORE:
                cachePlayerInfo.addExperience(3);
                break;
            case IRON_ORE:
                cachePlayerInfo.addExperience(5);
                break;
            case GOLD_ORE:
                cachePlayerInfo.addExperience(8);
                break;
            case DIAMOND_ORE:
                cachePlayerInfo.addExperience(15);
                break;
            case EMERALD_ORE:
                cachePlayerInfo.addExperience(25);
                break;
        }

    }
    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        CachePlayerInfo cachePlayerInfo = CachePlayersHashMap.getPlayerInfo().get(player);

        event.setDamage(event.getDamage() + cachePlayerInfo.getStrengthLvl());

    }
    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        CachePlayerInfo cachePlayerInfo = CachePlayersHashMap.getPlayerInfo().get(player);

        player.setWalkSpeed(player.getWalkSpeed() + cachePlayerInfo.getSpeedLvl());

    }
}
