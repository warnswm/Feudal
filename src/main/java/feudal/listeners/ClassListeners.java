package feudal.listeners;

import feudal.info.PlayerInfo;
import feudal.utils.PlayerGameClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ClassListeners implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        PlayerInfo playerInfo = PlayerGameClass.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getLuckLvl() == 100 ||
                playerInfo.getStrengthLvl() == 100 ||
                playerInfo.getAClassID() != 6) return;

        switch (event.getBlock().getType()) {

            case COAL_ORE:
                playerInfo.addExperience(3);
                break;
            case IRON_ORE:
                playerInfo.addExperience(5);
                break;
            case GOLD_ORE:
                playerInfo.addExperience(8);
                break;
            case DIAMOND_ORE:
                playerInfo.addExperience(15);
                break;
            case EMERALD_ORE:
                playerInfo.addExperience(25);
                break;

        }

    }
}
