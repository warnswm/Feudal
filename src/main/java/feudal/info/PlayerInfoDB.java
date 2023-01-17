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
import org.bukkit.entity.Player;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerInfoDB {
    MongoClient mongoClient;
    MongoCollection<Document> collection;

    public PlayerInfoDB(String mongoClientName, String databaseName, String collectionName) {
        
        this.mongoClient = MongoClients.create("mongodb://" + mongoClientName);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);
        
    }
    
    public void createNewPlayer(Player player) {

        if (collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                .iterator()
                .hasNext()) return;

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            collection.insertOne(new Document("_id", player.getUniqueId().toString())
                    .append("classID", 0)
                    .append("balance", 0)
                    .append("deaths", 0)
                    .append("kills", 0)
                    .append("lvl", 0)
                    .append("gain", 0.0)
                    .append("experience", 0)
                    .append("kingdomName", "notInTheKingdom"));

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

    }
    public boolean hasPlayer(Player player) {

        return collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                .iterator()
                .hasNext();

    }
    public Object getField(Player player, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            Document document = collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
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
    public void setField(Player player, String fieldName, Object value) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            Bson filter = Filters.eq("_id", player.getUniqueId().toString());
            Bson updates = Updates.set(fieldName, value);

            collection.findOneAndUpdate(filter, updates);

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }
    }
    public void resetAPlayer(Player player) {

        if (!collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
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
}
