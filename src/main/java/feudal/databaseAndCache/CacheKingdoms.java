package feudal.databaseAndCache;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdoms {
    static Map<String, KingdomInfo> kingdomInfoHashMap = new HashMap<>();

    public static Map<String, KingdomInfo> getKingdomInfo() {
        return kingdomInfoHashMap;
    }

    public static boolean playerInKingdom(Player player) {
        return getKingdomInfo().get(player.getUniqueId().toString()) == null;
    }

}
