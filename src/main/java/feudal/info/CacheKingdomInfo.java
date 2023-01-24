package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdomInfo {
    String kingdomName;
    Player king;
    Player[] members;
    Chunk[] territory;
    Player[] barons;
    ItemStack banner;
    final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    final KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionNameKingdom").toString());

    public CacheKingdomInfo setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public CacheKingdomInfo setKing(Player king) {
        this.king = king;
        return this;
    }

    public CacheKingdomInfo setMembers(Player[] members) {
        this.members = members;
        return this;
    }

    public CacheKingdomInfo setTerritory(Chunk[] territory) {
        this.territory = territory;
        return this;
    }

    public CacheKingdomInfo setBarons(Player[] barons) {
        this.barons = barons;
        return this;
    }

    public CacheKingdomInfo setBanner(ItemStack banner) {
        this.banner = banner;
        return this;
    }
}
