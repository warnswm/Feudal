package feudal.listeners.profession.peasants;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;

public class BuilderL implements Listener {

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        Block block = event.getBlock();

        if (feudalPlayer.getProfessionID() != ProfessionIDE.BUILDER.getId() ||
                !block.getType().equals(Material.MOB_SPAWNER)) return;


        CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

        if (!SpawnersForBuilderE.canBreak(creatureSpawner.getSpawnedType(), feudalPlayer.getProfessionLvl())) return;

        ItemStack loot = new ItemStack(Material.MOB_SPAWNER);

        BlockStateMeta blockStateMeta = (BlockStateMeta) loot.getItemMeta();
        BlockState blockState = blockStateMeta.getBlockState();
        ((CreatureSpawner) blockState).setSpawnedType(EntityType.valueOf(creatureSpawner.getSpawnedType().getName().toUpperCase()));
        blockStateMeta.setBlockState(blockState);
        loot.setItemMeta(blockStateMeta);

        block.getWorld().dropItemNaturally(block.getLocation(), loot);

    }

}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum SpawnersForBuilderE {

    SPIDER(EntityType.SPIDER, 25),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, 25),
    SILVERFISH(EntityType.SILVERFISH, 25),
    ZOMBIE(EntityType.ZOMBIE, 50),
    SKELETON(EntityType.SKELETON, 50),
    BLAZE(EntityType.BLAZE, 100);

    EntityType entity;
    int professionLvl;

    public static boolean canBreak(EntityType entity, int professionLvl) {

        for (SpawnersForBuilderE spawnersForBuilderE : values())
            if (spawnersForBuilderE.getEntity() == entity &&
                    professionLvl >= spawnersForBuilderE.getProfessionLvl())
                return true;

        return false;
    }
}
