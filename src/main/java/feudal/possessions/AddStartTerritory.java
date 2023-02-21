package feudal.possessions;

import feudal.data.FeudalKingdom;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.database.KingdomDBHandler;
import feudal.utils.wrappers.ChunkWrapper;
import lombok.experimental.UtilityClass;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@UtilityClass
public class AddStartTerritory {

    public boolean createStartTerritory(String kingdomName, @NotNull List<Chunk> startTerritory) {

        for (Chunk chunk : startTerritory) {

            if (KingdomDBHandler.chunkInKingdom(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()).hashCode()))
                return false;

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);

            feudalKingdom.addTerritory(chunk);
            feudalKingdom.addPrivateTerritory(chunk);

        }

        return true;

    }
}
