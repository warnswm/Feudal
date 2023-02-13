package feudal.possessions;

import feudal.data.builder.FeudalKingdom;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.database.KingdomDBHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddStartTerritory {

    public static boolean createStartTerritory(String kingdomName, @NotNull List<Chunk> startTerritory) {

        for (Chunk chunk : startTerritory) {

            if (KingdomDBHandler.chunkInKingdom(chunk.hashCode()))
                return false;

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

            feudalKingdom.addTerritory(chunk);
            feudalKingdom.addPrivateTerritory(chunk);

        }

        return true;
    }
}
