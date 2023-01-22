package feudal.listeners;

import feudal.Feudal;
import feudal.info.CachePlayerInfo;
import feudal.utils.CachePlayersHashMap;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class GameClassesListeners implements Listener {

    @EventHandler
    public void blockPlaced(BlockPlaceEvent event) {

        Block block = event.getBlock();

        block.setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        if (event.getBlock().getMetadata("PLACED") != null) return;

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
    public void regenerationEvent(EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        float tmp = CachePlayersHashMap.getPlayerInfo().get(player).getStaminaLvl();
        float tmpp = CachePlayersHashMap.getPlayerInfo().get(player).getSurvivabilityLvl();

        event.setAmount(1 * (tmp / 200 + tmpp / 500) + 1);

    }
    @EventHandler
    public void playerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        float tmp = CachePlayersHashMap.getPlayerInfo().get(player).getSpeedLvl();

        player.setWalkSpeed(0.2f * (tmp / 100) + 0.2f);

    }
    @EventHandler
    public void playerAttack(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        float tmp = CachePlayersHashMap.getPlayerInfo().get(player).getStrengthLvl();

        event.setDamage(event.getDamage() * (tmp / 200) + event.getDamage());
    }
}
