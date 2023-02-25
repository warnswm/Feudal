package feudal.data.cache;

import feudal.data.FeudalKingdom;
import feudal.utils.wrappers.ChunkWrapper;
import lombok.experimental.UtilityClass;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class CacheFeudalKingdoms {
    private final HashMap<String, FeudalKingdom> feudalKingdomCache = new HashMap<>();

    public Map<String, FeudalKingdom> getKingdomInfo() {
        return feudalKingdomCache;
    }

    public FeudalKingdom getFeudalKingdom(@NotNull String kingdomName) {
        return feudalKingdomCache.get(kingdomName);
    }

    public boolean checkPrivate(Chunk chunk, @NotNull Player player) {

        for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

            if (kingdom.getValue().chunkInKingdomCache(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()).hashCode()))
                return !kingdom.getValue().getMembersUUID().contains(player.getUniqueId());

        }

        return false;

    }

    public boolean exitsKingdom(@NotNull String kingdomName) {
        return kingdomName.equals("");
    }

}
