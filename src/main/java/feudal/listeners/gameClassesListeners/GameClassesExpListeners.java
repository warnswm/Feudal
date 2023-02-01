package feudal.listeners.gameClassesListeners;

import feudal.Feudal;
import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.enums.AnimalsForHuntedEnum;
import feudal.utils.enums.AnimalsForShepherdEnum;
import feudal.utils.enums.BlocksForMinerEnum;
import feudal.utils.enums.ClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.LOG;

public class GameClassesExpListeners implements Listener {

    @EventHandler
    public void blockPlaced(@NotNull BlockPlaceEvent event) {

        event.getBlock().setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));

    }

    @EventHandler
    public void playerBlockBreak(@NotNull BlockBreakEvent event) {

        if (event.getBlock().hasMetadata("PLACED")) return;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getAClassID() == ClassesIDEnum.MINER.getId()) {

            playerInfo.addExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()));
            playerInfo.addGameClassExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()) * 4);

        } else if (playerInfo.getAClassID() == ClassesIDEnum.WOODCUTTER.getId())
            if (event.getBlock().getType().equals(LOG)) {

                int colum = 0;

                for (int i = event.getBlock().getY(); i < event.getBlock().getWorld().getHighestBlockYAt(event.getBlock().getX(), event.getBlock().getZ());)
                    colum++;

                playerInfo.addExperience(colum);
                playerInfo.addGameClassExperience(colum * 4);
            }

        else if (playerInfo.getAClassID() == ClassesIDEnum.FARMER.getId()) {

            //Farmer logics

        }
    }

    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getAClassID() != ClassesIDEnum.FISHERMAN.getId() || event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        playerInfo.addExperience(30);
        playerInfo.addGameClassExperience(120);
    }

    @EventHandler
    public void playerItemEnchant(@NotNull EnchantItemEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && playerInfo.getAClassID() != ClassesIDEnum.CLERK.getId())
            event.setCancelled(true);

        if (event.getExpLevelCost() > 2) {

            playerInfo.addExperience(5);
            playerInfo.addGameClassExperience(20);

        } else if (event.getExpLevelCost() > 10) {

            playerInfo.addExperience(10);
            playerInfo.addGameClassExperience(40);

        } else if (event.getExpLevelCost() > 20) {

            playerInfo.addExperience(17);
            playerInfo.addGameClassExperience(68);

        }
    }

    @EventHandler
    public void playerBreed(@NotNull EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != ClassesIDEnum.SHEPHERD.getId()) return;

        playerInfo.addExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()));
        playerInfo.addGameClassExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()) * 4);

    }

    @EventHandler
    public void playerHunted(@NotNull EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        if (player == null) return;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != ClassesIDEnum.HUNTER.getId()) return;

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
    public void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        float tmp = CachePlayers.getPlayerInfo().get(player).getStrengthLvl();

        event.setDamage(event.getDamage() * (tmp / 200) + event.getDamage());

        if (!(event.getEntity() instanceof Player)) return;

        event.setDamage(event.getDamage() - event.getDamage() / 100 * (CachePlayers.getPlayerInfo().get(event.getEntity()).getStaminaLvl() * 0.2));
    }

    @EventHandler
    public void playerOnFoodChange(@NotNull FoodLevelChangeEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        float tmp = CachePlayers.getPlayerInfo().get(player).getStaminaLvl();

        event.setFoodLevel((int) (player.getFoodLevel() * (tmp / 300) + player.getFoodLevel()));
    }
}
