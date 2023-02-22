package feudal.data.cache;

import feudal.data.FeudalKingdom;
import lombok.experimental.UtilityClass;
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

    public boolean checkPrivate(int chunkHashCode, @NotNull Player player) {

        for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

            if (kingdom.getValue().chunkInKingdomCache(chunkHashCode))
                return !kingdom.getValue().getMembersUUID().contains(player.getUniqueId().toString());

        }

        return false;

    }

}
