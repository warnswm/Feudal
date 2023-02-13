package feudal.data.cache;

import feudal.data.builder.FeudalKingdom;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheFeudalKingdoms {
    static Map<String, FeudalKingdom> feudalKingdomCache = new HashMap<>();

    public static Map<String, FeudalKingdom> getKingdomInfo() {
        return feudalKingdomCache;
    }

    public static boolean checkPrivate(int chunkHashCode, @NotNull Player player) {

        for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

            if (kingdom.getValue().chunkInKingdomCache(chunkHashCode))
                return !kingdom.getValue().getMembersUUID().contains(player.getUniqueId().toString());

        }

        return false;

    }

    public static String playerInKingdomCache(@NotNull Player player) {
        return CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();
    }

}
