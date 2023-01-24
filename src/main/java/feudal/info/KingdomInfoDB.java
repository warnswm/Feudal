package feudal.info;

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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KingdomInfoDB {
    MongoClient mongoClient;
    MongoCollection<Document> collection;

    public KingdomInfoDB(String mongoClientName, String databaseName, String collectionName) {

        this.mongoClient = MongoClients.create("mongodb://" + mongoClientName);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);

    }

    public void createNewKingdom(@NotNull String kingdomName, Player king, List<String> members, List<Chunk> territory, List<String> barons, ItemStack banner) {

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
                    .append("banner", banner.toString())
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

    public void resetAllClanMembers(String kingdomName) {

        ClientSession session = mongoClient.startSession();
        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;


            Document document = collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .next();



            if (document.get("members") == null) return;

            PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
            Player[] members = (Player[]) document.get("members");

            Arrays.asList(members).forEach(playerInfoDB::resetAPlayer);

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

    }
    public boolean playerInKingdom(Player player) {

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
    public String getPlayerKingdom(Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("members", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext()) return collection.find(new BasicDBObject("members", player.getUniqueId().toString())).iterator().next().get("_id").toString();

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

        return "notInTheKingdom";
    }
}
