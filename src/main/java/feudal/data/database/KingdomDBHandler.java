package feudal.data.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import feudal.data.cache.CacheFeudalValues;
import feudal.utils.CollectionUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class KingdomDBHandler {

    private static final MongoClient mongoClient = CacheFeudalValues.getMongoClient();
    private static final MongoCollection<Document> collection = CacheFeudalValues.getKingdomsCollection();


    public static void createNewKingdom(@NotNull String kingdomName, Player king, List<UUID> membersUUID, List<Integer> territory, List<UUID> baronsUUID, String flagGson) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

            collection.insertOne(new Document("_id", kingdomName)
                    .append("king", king.getUniqueId().toString())
                    .append("members", CollectionUtils.uuidListToStringList(membersUUID))
                    .append("maxNumberMembers", 5)
                    .append("territory", territory)
                    .append("reputation", 1000)
                    .append("balance", 10000)
                    .append("balance", 10000)
                    .append("barons", CollectionUtils.uuidListToStringList(baronsUUID))
                    .append("flag", flagGson));

            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

    }

    public static void deleteKingdom(@NotNull String kingdomName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

            collection.deleteOne(collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .next());


            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

    }

    public static List getList(String kingdomName, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return Collections.EMPTY_LIST;

            Document document = collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .next();

            if (document.get(fieldName) != null)
                return (List) document.get(fieldName);

            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

        return Collections.EMPTY_LIST;

    }

    public static int getIntegerField(String kingdomName, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return 0;

            Document document = collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .next();

            if (document.get(fieldName) != null)
                return (int) document.get(fieldName);

            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

        return 0;

    }

    public static String getStringField(String kingdomName, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return "";

            Document document = collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .next();

            if (document.get(fieldName) != null)
                return (String) document.get(fieldName);

            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

        return "";
    }

    public static void setField(String kingdomName, String fieldName, Object value) {

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

    public static boolean playerInKingdom(@NotNull Player player) {

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

    public static String getPlayerKingdom(@NotNull Player player) {

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

        return "";

    }
}