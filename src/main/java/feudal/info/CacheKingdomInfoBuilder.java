package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdomInfoBuilder {
    String kingdomName;
    UUID king;
    List<String> members;
    List<Chunk> territory;
    List<String> barons;
    ItemStack banner;
    final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    final KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionNameKingdom").toString());

    public CacheKingdomInfoBuilder setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public CacheKingdomInfoBuilder setKing(UUID king) {
        this.king = king;
        return this;
    }

    public CacheKingdomInfoBuilder setMembers(List<String> members) {
        this.members = members;
        return this;
    }

    public CacheKingdomInfoBuilder setTerritory(List<Chunk> territory) {
        this.territory = territory;
        return this;
    }

    public CacheKingdomInfoBuilder setBarons(List<String> barons) {
        this.barons = barons;
        return this;
    }

    public CacheKingdomInfoBuilder setBanner(ItemStack banner) {
        this.banner = banner;
        return this;
    }
}
