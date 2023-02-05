package feudal.data.cache;

import feudal.data.builder.FeudalKingdom;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdomsMap {
    static Map<String, FeudalKingdom> kingdomCache = new HashMap<>();

    public static Map<String, FeudalKingdom> getKingdomInfo() {
        return kingdomCache;
    }

    public static boolean chunkInKingdomCache(@NotNull String chunk) {

        for (Map.Entry<String, FeudalKingdom> kingdom : CacheKingdomsMap.getKingdomInfo().entrySet())
            return kingdom.getValue().chunkInKingdomCache(chunk);

        return false;

    }

}
