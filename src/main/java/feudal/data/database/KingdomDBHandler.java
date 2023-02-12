package feudal.data.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import feudal.utils.FeudalValuesUtils;
import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ChunkWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KingdomDBHandler {

    static MongoClient mongoClient = FeudalValuesUtils.mongoClient;
    static MongoDatabase database = FeudalValuesUtils.database;
    static MongoCollection<Document> collection = FeudalValuesUtils.kingdomsCollection;


    public static void createNewKingdom(@NotNull String kingdomName, Player king, List<Player> members, List<Chunk> territory, List<Chunk> privateTerritory, List<Player> barons) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("_id", kingdomName))
                    .iterator()
                    .hasNext()) return;

            List<String> membersUUID = new ArrayList<>();
            members.forEach(member -> membersUUID.add(member.getUniqueId().toString()));

            List<String> baronsUUID = new ArrayList<>();
            barons.forEach(baron -> baronsUUID.add(baron.getUniqueId().toString()));

            List<String> territoryWrappers = new ArrayList<>();
            territory.forEach(chunk -> territoryWrappers.add(GsonUtils.chunkToJson(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()))));

            List<String> privateTerritoryWrappers = new ArrayList<>();
            privateTerritory.forEach(privateChunk -> privateTerritoryWrappers.add(GsonUtils.chunkToJson(new ChunkWrapper(privateChunk.getWorld().getName(), privateChunk.getX(), privateChunk.getZ()))));

            collection.insertOne(new Document("_id", kingdomName)
                    .append("king", king.getUniqueId().toString())
                    .append("members", membersUUID)
                    .append("maxNumberMembers", 5)
                    .append("territory", territoryWrappers)
                    .append("privateTerritory", privateTerritoryWrappers)
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

    public static boolean chunkInKingdom(@NotNull String chunk) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("territory", chunk))
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
