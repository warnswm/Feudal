package feudal.statistics;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KingdomStatistics {
    MongoClient mongoClient;
    MongoCollection<Document> collection;

    public KingdomStatistics(String mongoClientName, String databaseName, String collectionName) {

        this.mongoClient = MongoClients.create(mongoClientName);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);

    }

    public String createNewKingdom(String kingdomName, Player king, Player[] members, Chunk[] chunks, Player[] barons) {

        if (kingdomName.equalsIgnoreCase("notInTheKingdom") || collection.find(new BasicDBObject("_id", kingdomName))
                .iterator()
                .hasNext()) return "Нельзя создать королевство с таким именем или оно уже существует";

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            collection.insertOne(new Document("_id", kingdomName)
                    .append("king", king)
                    .append("members", members)
                    .append("territory", chunks)
                    .append("barons", barons));

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

        return "Королевство создано успешно";
    }
    public Object getField(String kingdomName, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

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

        if (!collection.find(new BasicDBObject("_id", kingdomName))
                .iterator()
                .hasNext()) return;

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            collection.drop();

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

    }
    public void resetAllClanMembers(String kingdomName) {

        ClientSession session = mongoClient.startSession();
        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();

        try {

            session.startTransaction();

            Document document = collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .next();

            if (document.get("members") == null)
                return;

            PlayerStatistics playerStatistics = new PlayerStatistics(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
            Player[] members = (Player[]) document.get("members");

            Arrays.asList(members).forEach(playerStatistics::resetAPlayer);

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

    }
}
