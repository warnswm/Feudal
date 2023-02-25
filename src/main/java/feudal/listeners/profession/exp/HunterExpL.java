package feudal.listeners.profession.exp;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class HunterExpL implements Listener {

    @EventHandler
    public final void playerHunted(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (feudalPlayer.getProfessionID() != ProfessionIDE.HUNTER.getId()) return;

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

        for (AnimalsForHuntedE animalsForHuntedE : values())
            if (animalsForHuntedE.getEntity() == entity)
                return animalsForHuntedE.getAttributeExp();

        return 0;

    }
}
