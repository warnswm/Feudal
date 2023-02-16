package feudal.data.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import feudal.utils.FeudalValuesUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class KingdomDBHandler {

    private static final FeudalValuesUtils feudalValuesUtils = new FeudalValuesUtils();

    private static final MongoClient mongoClient = FeudalValuesUtils.getMongoClient();
    private static final MongoCollection<Document> collection = FeudalValuesUtils.getKingdomsCollection();


    public static void createNewKingdom(@NotNull String kingdomName, Player king, List<String> membersUUID, List<Integer> territory, List<Integer> privateTerritory, List<String> baronsUUID) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

            collection.insertOne(new Document("_id", kingdomName)
                    .append("king", king.getUniqueId().toString())
                    .append("members", membersUUID)
                    .append("maxNumberMembers", 5)
                    .append("territory", territory)
                    .append("privateTerritory", privateTerritory)
                    .append("reputation", 1000)
                    .append("balance", 10000)
                    .append("barons", baronsUUID));

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

    public static @Nullable Object getField(String kingdomName, String fieldName) {

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

        return "NoObject";

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
                return Collections.singletonList(document.get(fieldName));

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

    public static void resetTheKingdom(String kingdomName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

            collection.deleteOne(Filters.eq("_id", kingdomName));

            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

    }

    public static void resetAllClanMembers(String kingdomName) {

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

            List<Player> members = (List<Player>) document.get("members");
            members.forEach(PlayerDBHandler::resetAPlayer);

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

    public static boolean chunkInKingdom(int chunkHashCode) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("territory", chunkHashCode))
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
