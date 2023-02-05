package feudal.data.database;

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
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerInfo {
    final MongoClient mongoClient;
    final MongoCollection<Document> collection;
    Player player;
    int aClassID;
    int experience;
    int balance;
    int deaths;
    int kills;
    int strengthLvl;
    int survivabilityLvl;
    int speedLvl;
    int staminaLvl;
    int luckLvl;
    int gameClassLvl;
    int gameClassExperience;
    String kingdomName;


    public PlayerInfo(String mongoClientName, String databaseName, String collectionName) {

        this.mongoClient = MongoClients.create("mongodb://" + mongoClientName);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);

    }

    public void createNewPlayer(@NotNull Player player) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext()) return;

            collection.insertOne(new Document("_id", player.getUniqueId().toString())
                    .append("classID", 0)
                    .append("experience", 0)
                    .append("gameClassLvl", 0)
                    .append("gameClassExperience", 0)
                    .append("balance", 0)
                    .append("deaths", 0)
                    .append("kills", 0)
                    .append("luckLvl", 0)
                    .append("speedLvl", 0)
                    .append("staminaLvl", 0)
                    .append("strengthLvl", 0)
                    .append("survivabilityLvl", 0)
                    .append("kingdomName", "notInTheKingdom"));

            session.commitTransaction();

        } catch (MongoCommandException e) {
            session.abortTransaction();
        } finally {
            session.close();
        }

    }

    public boolean hasPlayer(@NotNull Player player) {

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

    public Object getField(@NotNull Player player, String fieldName) {

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

        return null;
    }

    public void setField(@NotNull Player player, String fieldName, Object value) {

        ClientSession session = mongoClient.startSession();

        try {

            session.startTransaction();

            if (!collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
                    .iterator()
                    .hasNext()) return;

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

    //    public void resetAPlayer(@NotNull Player player) {
//
//        ClientSession session = mongoClient.startSession();
//
//        try {
//
//            session.startTransaction();
//
//
//            if (!collection.find(new BasicDBObject("_id", player.getUniqueId().toString()))
//                    .iterator()
//                    .hasNext()) return;
//
////            collection.drop();
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
    public void addExperience(int value) {
        experience += value;
    }

    public void addGameClassExperience(int value) {
        gameClassExperience += value;
    }

    public void addGameClassLvl(int value) {
        gameClassLvl += value;
    }

    public void addBalance(int value) {
        balance += value;
    }

    public void takeBalance(int value) {
        balance -= value;
    }

    public void addDeaths(int value) {
        deaths += value;
    }

    public void addKills(int value) {
        kills += value;
    }

    public void addStrengthLvl(int value) {
        strengthLvl += value;
    }

    public void addSurvivabilityLvl(int value) {
        survivabilityLvl += value;
    }

    public void addSpeedLvl(int value) {
        speedLvl += value;
    }

    public void addStaminaLvl(int value) {
        staminaLvl += value;
    }

    public void addLuckLvl(int value) {
        luckLvl += value;
    }

    public PlayerInfo setStrengthLvl(int strengthLvl) {
        this.strengthLvl = strengthLvl;
        return this;
    }

    public PlayerInfo setSurvivabilityLvl(int survivabilityLvl) {
        this.survivabilityLvl = survivabilityLvl;
        return this;
    }

    public PlayerInfo setSpeedLvl(int speedLvl) {
        this.speedLvl = speedLvl;
        return this;
    }

    public PlayerInfo setStaminaLvl(int staminaLvl) {
        this.staminaLvl = staminaLvl;
        return this;
    }

    public PlayerInfo setLuckLvl(int luckLvl) {
        this.luckLvl = luckLvl;
        return this;
    }

    public PlayerInfo setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public PlayerInfo setaClassID(int aClassID) {
        this.aClassID = aClassID;
        return this;
    }

    public PlayerInfo setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public PlayerInfo setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public PlayerInfo setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public PlayerInfo setKills(int kills) {
        this.kills = kills;
        return this;
    }

    public PlayerInfo setGameClassExperience(int gameClassExperience) {
        this.gameClassExperience = gameClassExperience;
        return this;
    }

    public PlayerInfo setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public PlayerInfo setGameClassLvl(int gameClassLvl) {
        this.gameClassLvl = gameClassLvl;
        return this;
    }
}
