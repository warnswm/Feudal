package feudal.data.cache;

import feudal.data.builder.FeudalKingdom;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdomsMap {
    static Map<String, FeudalKingdom> feudalKingdomCache = new HashMap<>();

    public static Map<String, FeudalKingdom> getKingdomInfo() {
        return feudalKingdomCache;
    }

    public static boolean checkPrivate(@NotNull String chunk, @NotNull Player player) {

        for (Map.Entry<String, FeudalKingdom> kingdom : CacheKingdomsMap.getKingdomInfo().entrySet()) {

            if (kingdom.getValue().chunkInKingdomCache(chunk))
                return !kingdom.getValue().getMembers().contains(player);

        }

        return false;

    }

    public static String playerInKingdomCache(@NotNull Player player) {

        for (Map.Entry<String, FeudalKingdom> kingdom : CacheKingdomsMap.getKingdomInfo().entrySet())
            if (kingdom.getValue().getMembers().contains(player))
                return kingdom.getValue().getKingdomName();

        return "";
    }

}
