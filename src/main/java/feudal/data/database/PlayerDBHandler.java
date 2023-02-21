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
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerDBHandler {
    private static final MongoClient mongoClient = FeudalValuesUtils.getMongoClient();
    private static final MongoCollection<Document> collection = FeudalValuesUtils.getPlayersCollection();

    public static boolean checkPlayer(@NotNull Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("_id", player.getUniqueId().hashCode()))
                    .iterator()
                    .hasNext()) return true;

            collection.insertOne(new Document("_id", player.getUniqueId().hashCode())
                    .append("professionID", 0)
                    .append("experience", 0)
                    .append("professionLvl", 0)
                    .append("professionExperience", 0)
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

            if (collection.find(new BasicDBObject("_id", player.getUniqueId().hashCode()))
                    .iterator()
                    .hasNext())
                return true;

            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

        return false;

    }

    public static int getIntegerField(@NotNull Player player, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", player.getUniqueId().hashCode()))
                    .iterator()
                    .hasNext()) return 0;

            Document document = collection.find(new BasicDBObject("_id", player.getUniqueId().hashCode()))
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

    public static String getStringField(@NotNull Player player, String fieldName) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", player.getUniqueId().hashCode()))
                    .iterator()
                    .hasNext()) return "";

            Document document = collection.find(new BasicDBObject("_id", player.getUniqueId().hashCode()))
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

    public static void setField(@NotNull Player player, String fieldName, Object value) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", player.getUniqueId().hashCode()))
                    .iterator()
                    .hasNext()) return;

            collection.findOneAndUpdate(Filters.eq("_id", player.getUniqueId().hashCode()), Updates.set(fieldName, value));

            session.commitTransaction();

        } catch (MongoCommandException e) {

            session.abortTransaction();

        } finally {

            session.close();

        }

    }

    public static void addField(@NotNull Player player, String fieldName, Object value) {

        ClientSession session = mongoClient.startSession();

        try {

            int uuid = player.getUniqueId().hashCode();

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", uuid))
                    .iterator()
                    .hasNext()) return;

            collection.replaceOne(Filters.eq("_id", uuid), collection.find(new BasicDBObject("_id", uuid))
                    .iterator()
                    .next()
                    .append(fieldName, value));

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

            int uuid = player.getUniqueId().hashCode();

            if (!collection.find(new BasicDBObject("_id", uuid))
                    .iterator()
                    .hasNext()) {

                checkPlayer(player);
                return;

            }

            collection.findOneAndReplace(Filters.eq("_id", uuid),
                    new Document("_id", uuid)
                            .append("professionID", 0)
                            .append("experience", 0)
                            .append("professionLvl", 0)
                            .append("professionExperience", 0)
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
