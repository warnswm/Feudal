package feudal.databaseAndCache;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KingdomInfo {
    final MongoClient mongoClient;
    final MongoCollection<Document> collection;
    String kingdomName;
    String king;
    List<String> members;
    List<Chunk> territory;
    List<String> barons;
    int balance;
    int reputation;

    public KingdomInfo(String mongoClientName, String databaseName, String collectionName) {

        this.mongoClient = MongoClients.create("mongodb://" + mongoClientName);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);

    }

    public void createNewKingdom(@NotNull String kingdomName, Player king, List<String> members, List<Chunk> territory, List<String> barons) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();


            if (kingdomName.equalsIgnoreCase("notInTheKingdom") || collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

            collection.insertOne(new Document("_id", kingdomName)
                    .append("king", king.getUniqueId().toString())
                    .append("members", members)
                    .append("territory", territory)
                    .append("reputation", 1000)
                    .append("balance", 10000)
                    .append("barons", barons));

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }
    }

    public Object getField(String kingdomName, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return null;

            Document document = collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .next();

            if (document.get(fieldName) != null)
                return document.get(fieldName);

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

        return null;
    }

    public void setField(String kingdomName, String fieldName, Object value) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

            Bson filter = Filters.eq("_id", kingdomName);
            Bson updates = Updates.set(fieldName, value);

            collection.findOneAndUpdate(filter, updates);

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }
    }

    public void resetTheKingdom(String kingdomName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

//            collection.drop();

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

    }

//    public void resetAllClanMembers(String kingdomName) {
//
//        ClientSession session = mongoClient.startSession();
//        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
//
//        try {
//
//            session.startTransaction();
//
//            if (!collection.find(new BasicDBObject("_id", kingdomName))
//                    .iterator()
//                    .hasNext()) return;
//
//
//            Document document = collection.find(new BasicDBObject("_id", kingdomName))
//                    .iterator()
//                    .next();
//
//
//            if (document.get("members") == null) return;
//
//            PlayerInfo playerInfo = new PlayerInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
//            Player[] members = (Player[]) document.get("members");
//
//            Arrays.asList(members).forEach(playerInfo::resetAPlayer);
//
//            session.commitTransaction();
//
//        } catch (MongoCommandException e) {
//            session.abortTransaction();
//        } finally {
//            session.close();
//        }
//
//    }

    public boolean playerInKingdom(@NotNull Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("members", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext()) return true;

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

        return false;
    }

    public String getPlayerKingdom(@NotNull Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("members", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext())
                return collection.find(new BasicDBObject("members", player.getUniqueId().toString())).iterator().next().get("_id").toString();

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

        return "notInTheKingdom";
    }

    public KingdomInfo setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public KingdomInfo setKing(String king) {
        this.king = king;
        return this;
    }

    public KingdomInfo setMembers(List<String> members) {
        this.members = members;
        return this;
    }

    public KingdomInfo setTerritory(List<Chunk> territory) {
        this.territory = territory;
        return this;
    }
    public KingdomInfo takeTerritory(Chunk territory) {
        this.territory.remove(territory);
        return this;
    }
    public KingdomInfo takeAllTerritory() {
        this.territory.clear();
        return this;
    }
    public KingdomInfo addTerritory(Chunk territory) {
        this.territory.add(territory);
        return this;
    }

    public KingdomInfo setBarons(List<String> barons) {
        this.barons = barons;
        return this;
    }
    public KingdomInfo setBalance(int balance) {
        this.balance = balance;
        return this;
    }
    public KingdomInfo takeBalance(int balance) {
        this.balance -= balance;
        return this;
    }
    public KingdomInfo addBalance(int balance) {
        this.balance += balance;
        return this;
    }
    public KingdomInfo setReputation(int reputation) {
        this.reputation = reputation;
        return this;
    }
    public KingdomInfo takeReputation(int reputation) {
        this.reputation -= reputation;
        return this;
    }
    public KingdomInfo addReputation(int reputation) {
        this.reputation += reputation;
        return this;
    }
}
