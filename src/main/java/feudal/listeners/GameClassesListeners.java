package feudal.listeners;

import feudal.info.PlayerInfoCache;
import feudal.utils.PlayerGameClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameClassesListeners implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        PlayerInfoCache playerInfoCache = PlayerGameClass.getPlayerInfo().get(event.getPlayer());

        if (playerInfoCache.getLuckLvl() == 100 ||
                playerInfoCache.getStrengthLvl() == 100 ||
                playerInfoCache.getAClassID() != 6) return;

        switch (event.getBlock().getType()) {

            case COAL_ORE:
                playerInfoCache.addExperience(3);
                break;
            case IRON_ORE:
                playerInfoCache.addExperience(5);
                break;
            case GOLD_ORE:
                playerInfoCache.addExperience(8);
                break;
            case DIAMOND_ORE:
                playerInfoCache.addExperience(15);
                break;
            case EMERALD_ORE:
                playerInfoCache.addExperience(25);
                break;
        }

    }
}
