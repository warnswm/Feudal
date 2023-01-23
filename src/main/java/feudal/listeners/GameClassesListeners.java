package feudal.listeners;

import feudal.Feudal;
import feudal.info.CachePlayerInfo;
import feudal.utils.CachePlayers;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

import static org.bukkit.Material.LOG;

public class GameClassesListeners implements Listener {

    @EventHandler
    public void blockPlaced(BlockPlaceEvent event) {

        Block block = event.getBlock();

        block.setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        if (event.getBlock().hasMetadata("PLACED")) return;

        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());

        if (cachePlayerInfo.getAClassID() == 6) {

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

        } else if (cachePlayerInfo.getAClassID() == 9) {

            if (event.getBlock().getType().equals(LOG))
                cachePlayerInfo.addExperience(1);

        } else if (cachePlayerInfo.getAClassID() == 3) {

            //Farmer logics

        }
    }
    @EventHandler
    public void playerFishing(PlayerFishEvent event) {

        Player player = event.getPlayer();

        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

        if (cachePlayerInfo.getAClassID() != 4) return;

        if (event.getCaught() != null)
            cachePlayerInfo.addExperience(30);

    }
    @EventHandler
    public void entityBreed(EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();

        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

        if (cachePlayerInfo.getAClassID() != 7) return;

        switch (event.getEntityType()) {

            case SHEEP:
            case COW:
            case MUSHROOM_COW:
            case OCELOT:
            case RABBIT:
                cachePlayerInfo.addExperience(5);
                break;
            case PIG:
                cachePlayerInfo.addExperience(6);
                break;
            case HORSE:
            case DONKEY:
                cachePlayerInfo.addExperience(35);
                break;
            case CHICKEN:
                cachePlayerInfo.addExperience(3);
                break;
            case WOLF:
            case LLAMA:
                cachePlayerInfo.addExperience(12);
                break;
        }
    }

    @EventHandler
    public void playerHunted(EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        if (player == null) return;

        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

        if (cachePlayerInfo.getAClassID() != 5) return;

        switch (event.getEntityType()) {

            case SHEEP:
            case COW:
            case MUSHROOM_COW:
            case OCELOT:
            case RABBIT:
                cachePlayerInfo.addExperience(5);
                break;
            case PIG:
                cachePlayerInfo.addExperience(6);
                break;
            case HORSE:
            case DONKEY:
                cachePlayerInfo.addExperience(15);
                break;
            case CHICKEN:
                cachePlayerInfo.addExperience(3);
                break;
            case WOLF:
            case LLAMA:
                cachePlayerInfo.addExperience(12);
                break;
        }

    }


    @EventHandler
    public void regenerationEvent(EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        float tmp = CachePlayers.getPlayerInfo().get(player).getStaminaLvl();
        float tmpp = CachePlayers.getPlayerInfo().get(player).getSurvivabilityLvl();

        event.setAmount(1 * (tmp / 200 + tmpp / 500) + 1);

    }
    @EventHandler
    public void playerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        float tmp = CachePlayers.getPlayerInfo().get(player).getSpeedLvl();

        player.setWalkSpeed(0.2f * (tmp / 100) + 0.2f);

    }
    @EventHandler
    public void playerAttack(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        float tmp = CachePlayers.getPlayerInfo().get(player).getStrengthLvl();

        event.setDamage(event.getDamage() * (tmp / 200) + event.getDamage());
    }
}
