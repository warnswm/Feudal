package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdomInfoBuilder {
    String kingdomName;
    String king;
    List<String> members;
    List<Chunk> territory;
    List<String> barons;
    String banner;

    public CacheKingdomInfoBuilder setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public CacheKingdomInfoBuilder setKing(String king) {
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

    public CacheKingdomInfoBuilder setBanner(String banner) {
        this.banner = banner;
        return this;
    }
}
