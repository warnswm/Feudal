package feudal.listeners;

import feudal.Feudal;
import feudal.info.CachePlayerInfo;
import feudal.utils.CachePlayers;
import feudal.utils.gameClassesEnums.AnimalsForHunted;
import feudal.utils.gameClassesEnums.AnimalsForShepherd;
import feudal.utils.gameClassesEnums.BlocksForMiner;
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

            cachePlayerInfo.addExperience(BlocksForMiner.getByMaterial(event.getBlock().getType()));
            cachePlayerInfo.addGameClassExperience(BlocksForMiner.getByMaterial(event.getBlock().getType()) * 4);

        }
        else if (cachePlayerInfo.getAClassID() == 9)
            if (event.getBlock().getType().equals(LOG)) {
                cachePlayerInfo.addExperience(1);
                cachePlayerInfo.addGameClassExperience(4);
            }

        else if (cachePlayerInfo.getAClassID() == 3) {

            //Farmer logics

        }
    }
    @EventHandler
    public void playerFishing(PlayerFishEvent event) {

        Player player = event.getPlayer();
        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

        if (cachePlayerInfo.getAClassID() != 4) return;

        if (event.getCaught() != null) {

            cachePlayerInfo.addExperience(30);
            cachePlayerInfo.addGameClassExperience(120);

        }

    }
    @EventHandler
    public void entityBreed(EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();
        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

        if (cachePlayerInfo.getAClassID() != 7) return;

        cachePlayerInfo.addExperience(AnimalsForShepherd.getByEntity(event.getEntityType()));
        cachePlayerInfo.addGameClassExperience(AnimalsForShepherd.getByEntity(event.getEntityType()) * 4);

    }

    @EventHandler
    public void playerHunted(EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        if (player == null) return;

        CachePlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

        if (cachePlayerInfo.getAClassID() != 5) return;

        cachePlayerInfo.addExperience(AnimalsForHunted.getByEntity(event.getEntityType()));
        cachePlayerInfo.addGameClassExperience(AnimalsForHunted.getByEntity(event.getEntityType()) * 4);

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
