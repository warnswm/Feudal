package feudal.listeners.profession;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HunterL implements Listener {

    @EventHandler
    public final void playerHunted(@NotNull EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        if (player == null) return;

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        if (feudalPlayer.getProfessionID() != ProfessionIDE.HUNTER.getId() ||
                feudalPlayer.getProfessionLvl() < 25) return;

        List<ItemStack> drops = event.getDrops();

        if (drops.contains(new ItemStack(Material.RAW_CHICKEN))) {

            drops.remove(new ItemStack(Material.RAW_CHICKEN));
            drops.add(new ItemStack(Material.COOKED_CHICKEN));

        } else if (drops.contains(new ItemStack(Material.RAW_BEEF))) {

            drops.remove(new ItemStack(Material.RAW_BEEF));
            drops.add(new ItemStack(Material.COOKED_BEEF));

        } else if (drops.contains(new ItemStack(Material.RABBIT))) {

            drops.remove(new ItemStack(Material.RABBIT));
            drops.add(new ItemStack(Material.COOKED_RABBIT));

        } else if (drops.contains(new ItemStack(Material.MUTTON))) {

            drops.remove(new ItemStack(Material.MUTTON));
            drops.add(new ItemStack(Material.COOKED_MUTTON));

        } else if (drops.contains(new ItemStack(Material.PORK))) {

            drops.remove(new ItemStack(Material.PORK));
            drops.add(new ItemStack(Material.GRILLED_PORK));

        } else if (drops.contains(new ItemStack(Material.RAW_FISH))) {

            drops.remove(new ItemStack(Material.RAW_FISH));
            drops.add(new ItemStack(Material.COOKED_FISH));

        }


        int exp = AnimalsForHuntedE.getAttributeExpByEntity(event.getEntityType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }
}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum AnimalsForHuntedE {
    SHEEP(EntityType.SHEEP, 5), COW(EntityType.COW, 5), MUSHROOM_COW(EntityType.MUSHROOM_COW, 5),
    OCELOT(EntityType.OCELOT, 5), RABBIT(EntityType.RABBIT, 5), PIG(EntityType.PIG, 6),
    HORSE(EntityType.HORSE, 15), DONKEY(EntityType.DONKEY, 15), CHICKEN(EntityType.CHICKEN, 3),
    WOLF(EntityType.WOLF, 12), LLAMA(EntityType.LLAMA, 12);

    EntityType entity;
    int attributeExp;

    public static int getAttributeExpByEntity(EntityType entity) {

        if (entity == null) return 0;

        for (AnimalsForHuntedE animalsForHuntedE : values())
            if (animalsForHuntedE.getEntity() == entity)
                return animalsForHuntedE.getAttributeExp();

        return 0;

    }
}

