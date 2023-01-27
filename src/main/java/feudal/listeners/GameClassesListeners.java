package feudal.listeners;

import feudal.Feudal;
import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
import feudal.utils.gameClassesEnums.AnimalsForHuntedEnum;
import feudal.utils.gameClassesEnums.AnimalsForShepherdEnum;
import feudal.utils.gameClassesEnums.BlocksForMinerEnum;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.LOG;

public class GameClassesListeners implements Listener {

    @EventHandler
    public void blockPlaced(@NotNull BlockPlaceEvent event) {

        Block block = event.getBlock();

        block.setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));
    }

    @EventHandler
    public void blockBreak(@NotNull BlockBreakEvent event) {

        if (event.getBlock().hasMetadata("PLACED")) return;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getAClassID() == 6) {

            playerInfo.addExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()));
            playerInfo.addGameClassExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()) * 4);

        } else if (playerInfo.getAClassID() == 9)
            if (event.getBlock().getType().equals(LOG)) {
                playerInfo.addExperience(1);
                playerInfo.addGameClassExperience(4);
            }

        else if (playerInfo.getAClassID() == 3) {

            //Farmer logics

        }
    }

    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        Player player = event.getPlayer();
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != 4) return;

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH)
            playerInfo.addExperience(30);

    }

    @EventHandler
    public void playerBreed(@NotNull EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != 7) return;

        playerInfo.addExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()));
        playerInfo.addGameClassExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()) * 4);

    }

    @EventHandler
    public void playerHunted(@NotNull EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        if (player == null) return;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != 5) return;

        playerInfo.addExperience(AnimalsForHuntedEnum.getByEntity(event.getEntityType()));
        playerInfo.addGameClassExperience(AnimalsForHuntedEnum.getByEntity(event.getEntityType()) * 4);

    }

    @EventHandler
    public void playerRegenerationEvent(@NotNull EntityRegainHealthEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        float tmp = CachePlayers.getPlayerInfo().get(player).getSurvivabilityLvl();

        event.setAmount(1 * (tmp / 200) + 1);

    }

    @EventHandler
    public void playerMove(@NotNull PlayerMoveEvent event) {

        Player player = event.getPlayer();

        float tmp = CachePlayers.getPlayerInfo().get(player).getSpeedLvl();

        player.setWalkSpeed(0.2f * (tmp / 100) + 0.2f);

    }

    @EventHandler
    public void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        float tmp = CachePlayers.getPlayerInfo().get(player).getStrengthLvl();

        event.setDamage(event.getDamage() * (tmp / 200) + event.getDamage());
        event.setDamage(event.getDamage() / 100 * (CachePlayers.getPlayerInfo().get(event.getEntity()).getStaminaLvl() * 0.2));
    }

    @EventHandler
    public void playerOnFoodChange(@NotNull FoodLevelChangeEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        float tmp = CachePlayers.getPlayerInfo().get(player).getStaminaLvl();

        event.setFoodLevel((int) (player.getFoodLevel() * (tmp / 300) + player.getFoodLevel()));
    }
}
