package feudal.listeners.gameClassesListeners;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.PlayerInfo;
import feudal.utils.enums.gameClassesEnums.AnimalsForHuntedEnum;
import feudal.utils.enums.gameClassesEnums.AnimalsForShepherdEnum;
import feudal.utils.enums.gameClassesEnums.BlocksForMinerEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.LOG;

public class GameClassesExpListeners implements Listener {
    
    @EventHandler
    public void playerBlockBreak(@NotNull BlockBreakEvent event) {

        if (event.getBlock().hasMetadata("PLACED")) return;

        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getAClassID() == GameClassesIDEnum.MINER.getId()) {

            playerInfo.addExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()));
            playerInfo.addGameClassExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()) * 4);

        } else if (playerInfo.getAClassID() == GameClassesIDEnum.WOODCUTTER.getId())
            if (event.getBlock().getType().equals(LOG)) {

                int colum = 0;
                for (int i = event.getBlock().getY(); i < event.getBlock().getWorld().getHighestBlockYAt(event.getBlock().getX(), event.getBlock().getZ()); )
                    colum++;

                playerInfo.addExperience(colum);
                playerInfo.addGameClassExperience(colum * 4);

            } else if (playerInfo.getAClassID() == GameClassesIDEnum.FARMER.getId()) {

                //Farmer logics

            }
    }

    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getAClassID() != GameClassesIDEnum.FISHERMAN.getId() || event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
            return;

        playerInfo.addExperience(30);
        playerInfo.addGameClassExperience(120);
    }

    @EventHandler
    public void playerItemEnchant(@NotNull EnchantItemEvent event) {

        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && playerInfo.getAClassID() != GameClassesIDEnum.CLERK.getId())
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
        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != GameClassesIDEnum.SHEPHERD.getId()) return;

        playerInfo.addExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()));
        playerInfo.addGameClassExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()) * 4);

    }

    @EventHandler
    public void playerHunted(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(player);

        if (playerInfo.getAClassID() != GameClassesIDEnum.HUNTER.getId()) return;

        playerInfo.addExperience(AnimalsForHuntedEnum.getByEntity(event.getEntityType()));
        playerInfo.addGameClassExperience(AnimalsForHuntedEnum.getByEntity(event.getEntityType()) * 4);

    }
}
