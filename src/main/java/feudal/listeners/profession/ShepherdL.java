package feudal.listeners.profession;

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
import org.bukkit.event.entity.EntityBreedEvent;
import org.jetbrains.annotations.NotNull;

public class ShepherdL implements Listener {

    @EventHandler
    public final void playerBreed(@NotNull EntityBreedEvent event) {

        if (!(event.getBreeder() instanceof Player)) return;

        Player player = (Player) event.getBreeder();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (feudalPlayer.getProfessionID() != ProfessionIDE.SHEPHERD.getId()) return;

        int exp = AnimalsForShepherdE.getAttributeExpByEntity(event.getEntityType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }
}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum AnimalsForShepherdE {
    SHEEP(EntityType.SHEEP, 5), COW(EntityType.COW, 5), MUSHROOM_COW(EntityType.MUSHROOM_COW, 5),
    OCELOT(EntityType.OCELOT, 5), RABBIT(EntityType.RABBIT, 5), PIG(EntityType.PIG, 6),
    HORSE(EntityType.HORSE, 25), DONKEY(EntityType.DONKEY, 25), CHICKEN(EntityType.CHICKEN, 3),
    WOLF(EntityType.WOLF, 12), LLAMA(EntityType.LLAMA, 12);

    EntityType entity;
    int attributeExp;

    public static int getAttributeExpByEntity(EntityType entity) {

        if (entity == null) return 0;

        for (AnimalsForShepherdE animalsForShepherdE : values())
            if (animalsForShepherdE.getEntity() == entity)
                return animalsForShepherdE.getAttributeExp();

        return 0;

    }
}
