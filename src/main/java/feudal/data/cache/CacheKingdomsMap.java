package feudal.data.cache;

import feudal.data.database.KingdomInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdomsMap {
    static Map<String, KingdomInfo> kingdomInfoHashMap = new HashMap<>();

    public static Map<String, KingdomInfo> getKingdomInfo() {
        return kingdomInfoHashMap;
    }

    public static boolean chunkInKingdomCache(@NotNull String chunk) {

        for (Map.Entry<String, KingdomInfo> kingdom : CacheKingdomsMap.getKingdomInfo().entrySet()) {

            return kingdom.getValue().chunkInKingdomCache(chunk);

        }

        return false;

    }

}
