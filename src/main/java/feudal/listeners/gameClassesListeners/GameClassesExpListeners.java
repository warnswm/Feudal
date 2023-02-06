package feudal.listeners.gameClassesListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
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

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getPlayer());

        if (feudalPlayer.getAClassID() == GameClassesIDEnum.MINER.getId()) {

            feudalPlayer.addExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()));
            feudalPlayer.addGameClassExperience(BlocksForMinerEnum.getByMaterial(event.getBlock().getType()) * 4);

        } else if (feudalPlayer.getAClassID() == GameClassesIDEnum.WOODCUTTER.getId())
            if (event.getBlock().getType().equals(LOG)) {

                int colum = 0;
                for (int i = event.getBlock().getY(); i < event.getBlock().getWorld().getHighestBlockYAt(event.getBlock().getX(), event.getBlock().getZ()); )
                    colum++;

                feudalPlayer.addExperience(colum);
                feudalPlayer.addGameClassExperience(colum * 4);

            } else if (feudalPlayer.getAClassID() == GameClassesIDEnum.FARMER.getId()) {

                //Farmer logics

            }
    }

    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getPlayer());

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.FISHERMAN.getId() || event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
            return;

        feudalPlayer.addExperience(30);
        feudalPlayer.addGameClassExperience(120);
    }

    @EventHandler
    public void playerItemEnchant(@NotNull EnchantItemEvent event) {

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && feudalPlayer.getAClassID() != GameClassesIDEnum.CLERK.getId())
            event.setCancelled(true);

        if (event.getExpLevelCost() > 2) {

            feudalPlayer.addExperience(5);
            feudalPlayer.addGameClassExperience(20);

        } else if (event.getExpLevelCost() > 10) {

            feudalPlayer.addExperience(10);
            feudalPlayer.addGameClassExperience(40);

        } else if (event.getExpLevelCost() > 20) {

            feudalPlayer.addExperience(17);
            feudalPlayer.addGameClassExperience(68);

        }
    }

    @EventHandler
    public void playerBreed(@NotNull EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();
        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.SHEPHERD.getId()) return;

        feudalPlayer.addExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()));
        feudalPlayer.addGameClassExperience(AnimalsForShepherdEnum.getByEntity(event.getEntityType()) * 4);

    }

    @EventHandler
    public void playerHunted(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.HUNTER.getId()) return;

        feudalPlayer.addExperience(AnimalsForHuntedEnum.getByEntity(event.getEntityType()));
        feudalPlayer.addGameClassExperience(AnimalsForHuntedEnum.getByEntity(event.getEntityType()) * 4);

    }
}
