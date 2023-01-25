package feudal.info;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdoms {
    static Map<String, KingdomInfo> kingdomInfoHashMap = new HashMap<>();

    public static Map<String, KingdomInfo> getKingdomInfo() {
        return kingdomInfoHashMap;
    }

}
