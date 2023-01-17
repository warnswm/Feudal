package feudal.possessions;

import feudal.info.KingdomInfoDB;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Kingdom {
    String kingdomName;
    Player king;
    List<Chunk> territory;
    List<Player> members;
    List<Player> barons;

    final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    final KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    public void addTerritory(Chunk chunk) {

        territory.add(chunk);

        kingdomInfoDB.setField(kingdomName, "territory", territory);
    }
    public void addMembers(Player player) {

        members.add(player);

        kingdomInfoDB.setField(kingdomName, "members", members);

    }
    public void addBarons(Player player) {

        barons.add(player);

        kingdomInfoDB.setField(kingdomName, "barons", barons);

    }
    public void changeTheKing(Player player) {

        king = player;

        kingdomInfoDB.setField(kingdomName, "king", king);

    }
    public void renameKingdom(String name) {

        kingdomName = name;

        kingdomInfoDB.setField(kingdomName, "_id", kingdomName);

    }
}
