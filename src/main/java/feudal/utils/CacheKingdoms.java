package feudal.utils;

import feudal.info.CacheKingdomInfo;

import java.util.HashMap;
import java.util.Map;

public class CacheKingdoms {
     public static Map<String, CacheKingdomInfo> kingdomInfoHashMap = new HashMap<>();

    public static Map<String, CacheKingdomInfo> getKingdomInfo() {
        return kingdomInfoHashMap;
    }

}
