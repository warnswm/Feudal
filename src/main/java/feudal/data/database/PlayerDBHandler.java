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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerDBHandler {

    static MongoClient mongoClient = FeudalValuesUtils.mongoClient;
    static MongoDatabase database = FeudalValuesUtils.database;
    static MongoCollection<Document> collection = FeudalValuesUtils.playersCollection;

    public static boolean createNewPlayer(@NotNull Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext()) return true;

            collection.insertOne(new Document("_id", player.getUniqueId().toString())
                    .append("classID", 0)
                    .append("experience", 0)
                    .append("gameClassLvl", 0)
                    .append("gameClassExperience", 0)
                    .append("balance", 1000)
                    .append("deaths", 0)
                    .append("kills", 0)
                    .append("luckLvl", 0)
                    .append("speedLvl", 0)
                    .append("staminaLvl", 0)
                    .append("strengthLvl", 0)
                    .append("survivabilityLvl", 0)
                    .append("kingdomName", ""));

            session.commitTransaction();

            return true;

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

        return false;
    }

    public static boolean hasPlayer(@NotNull Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext())
                return collection.find(new BasicDBObject("_id", player.getUniqueId().toString())).iterator().hasNext();

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }
        return false;
    }

    public static Object getField(@NotNull Player player, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext()) return null;

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

        return "NoObject";
    }

    public static void setField(@NotNull Player player, String fieldName, Object value) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext()) return;

            collection.findOneAndUpdate(Filters.eq("_id", player.getUniqueId().toString()), Updates.set(fieldName, value));

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }
    }

    public static void resetAPlayer(@NotNull Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();


            String uuid = player.getUniqueId().toString();

            if (!collection.find(new BasicDBObject("_id", uuid))
                    .iterator()
                    .hasNext()) return;

            collection.findOneAndReplace(Filters.eq("_id", uuid),
                    new Document("_id", uuid)
                            .append("classID", 0)
                            .append("experience", 0)
                            .append("gameClassLvl", 0)
                            .append("gameClassExperience", 0)
                            .append("balance", 1000)
                            .append("deaths", 0)
                            .append("kills", 0)
                            .append("luckLvl", 0)
                            .append("speedLvl", 0)
                            .append("staminaLvl", 0)
                            .append("strengthLvl", 0)
                            .append("survivabilityLvl", 0)
                            .append("kingdomName", ""));

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

    }
}
