package feudal.data.cache;

import feudal.data.FeudalKingdom;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CacheFeudalKingdoms {
    private static final HashMap<String, FeudalKingdom> feudalKingdomCache = new HashMap<>();

    public static HashMap<String, FeudalKingdom> getKingdomInfo() {
        return feudalKingdomCache;
    }

    public static boolean checkPrivate(int chunkHashCode, @NotNull Player player) {

        for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

            if (kingdom.getValue().chunkInKingdomCache(chunkHashCode))
                return !kingdom.getValue().getMembersUUID().contains(player.getUniqueId().toString());

        }

        return false;

    }
}
