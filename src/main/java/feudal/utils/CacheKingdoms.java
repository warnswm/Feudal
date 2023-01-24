package feudal.utils;

import feudal.info.CacheKingdomInfoBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdoms {
    static Map<String, CacheKingdomInfoBuilder> kingdomInfoHashMap = new HashMap<>();

    public static Map<String, CacheKingdomInfoBuilder> getKingdomInfo() {
        return kingdomInfoHashMap;
    }

}
