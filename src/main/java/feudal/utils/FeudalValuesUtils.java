package feudal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bson.Document;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalValuesUtils {
    static MongoClient mongoClient;
    static MongoDatabase database;
    static MongoCollection<Document> playersCollection;
    static MongoCollection<Document> kingdomsCollection;

    static int vampirismMaxLvl;
    static double vampirismPercentagePerLvl;

    static int doubleDamageMaxLvl;
    static double doubleDamagePercentagePerLvl;

    static int blindnessMaxLvl;
    static double blindnessPercentagePerLvl;
    static int blindnessTime;
    static double blindnessTimePercentagePerLvl;

    static int slowdownMaxLvl;
    static double slowdownPercentagePerLvl;
    static int slowdownTime;
    static double slowdownTimePercentagePerLvl;

    static int desiccationMaxLvl;
    static double desiccationPercentagePerLvl;
    static int desiccationTime;
    static double desiccationTimePercentagePerLvl;

    static int swordStunMaxLvl;
    static double swordStunPercentagePerLvl;
    static int swordStunTime;
    static double swordStunTimePercentagePerLvl;

    static int levitationMaxLvl;
    static double levitationPercentagePerLvl;
    static int levitationTime;
    static double levitationTimePercentagePerLvl;

    static int poisoningMaxLvl;
    static double poisoningPercentagePerLvl;
    static int poisoningTime;
    static double poisoningTimePercentagePerLvl;

    static int nauseaMaxLvl;
    static double nauseaPercentagePerLvl;
    static int nauseaTime;
    static double nauseaTimePercentagePerLvl;

    static int hookMaxLvl;
    static double hookPercentagePerLvl;

    static int multi_shootingMaxLvl;
    static double multi_shootingPercentagePerLvl;

    static int bowStunMaxLvl;
    static double bowStunPercentagePerLvl;

    static int greedMaxLvl;
    static double greedPercentagePerLvl;


    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static MongoCollection<Document> getPlayersCollection() {
        return playersCollection;
    }

    public static MongoCollection<Document> getKingdomsCollection() {
        return kingdomsCollection;
    }

    public static int getVampirismMaxLvl() {
        return vampirismMaxLvl;
    }

    public static double getVampirismPercentagePerLvl() {
        return vampirismPercentagePerLvl;
    }

    public static int getDoubleDamageMaxLvl() {
        return doubleDamageMaxLvl;
    }

    public static double getDoubleDamagePercentagePerLvl() {
        return doubleDamagePercentagePerLvl;
    }

    public static int getBlindnessMaxLvl() {
        return blindnessMaxLvl;
    }

    public static double getBlindnessPercentagePerLvl() {
        return blindnessPercentagePerLvl;
    }

    public static int getBlindnessTime() {
        return blindnessTime;
    }

    public static double getBlindnessTimePercentagePerLvl() {
        return blindnessTimePercentagePerLvl;
    }

    public static int getSlowdownMaxLvl() {
        return slowdownMaxLvl;
    }

    public static double getSlowdownPercentagePerLvl() {
        return slowdownPercentagePerLvl;
    }

    public static int getSlowdownTime() {
        return slowdownTime;
    }

    public static double getSlowdownTimePercentagePerLvl() {
        return slowdownTimePercentagePerLvl;
    }

    public static int getDesiccationMaxLvl() {
        return desiccationMaxLvl;
    }

    public static double getDesiccationPercentagePerLvl() {
        return desiccationPercentagePerLvl;
    }

    public static int getDesiccationTime() {
        return desiccationTime;
    }

    public static double getDesiccationTimePercentagePerLvl() {
        return desiccationTimePercentagePerLvl;
    }

    public static int getSwordStunMaxLvl() {
        return swordStunMaxLvl;
    }

    public static double getSwordStunPercentagePerLvl() {
        return swordStunPercentagePerLvl;
    }

    public static int getSwordStunTime() {
        return swordStunTime;
    }

    public static double getSwordStunTimePercentagePerLvl() {
        return swordStunTimePercentagePerLvl;
    }

    public static int getLevitationMaxLvl() {
        return levitationMaxLvl;
    }

    public static double getLevitationPercentagePerLvl() {
        return levitationPercentagePerLvl;
    }

    public static int getLevitationTime() {
        return levitationTime;
    }

    public static double getLevitationTimePercentagePerLvl() {
        return levitationTimePercentagePerLvl;
    }

    public static int getPoisoningMaxLvl() {
        return poisoningMaxLvl;
    }

    public static double getPoisoningPercentagePerLvl() {
        return poisoningPercentagePerLvl;
    }

    public static int getPoisoningTime() {
        return poisoningTime;
    }

    public static double getPoisoningTimePercentagePerLvl() {
        return poisoningTimePercentagePerLvl;
    }

    public static int getNauseaMaxLvl() {
        return nauseaMaxLvl;
    }

    public static double getNauseaPercentagePerLvl() {
        return nauseaPercentagePerLvl;
    }

    public static int getNauseaTime() {
        return nauseaTime;
    }

    public static double getNauseaTimePercentagePerLvl() {
        return nauseaTimePercentagePerLvl;
    }

    public static int getHookMaxLvl() {
        return hookMaxLvl;
    }

    public static double getHookPercentagePerLvl() {
        return hookPercentagePerLvl;
    }

    public static int getMulti_shootingMaxLvl() {
        return multi_shootingMaxLvl;
    }

    public static double getMulti_shootingPercentagePerLvl() {
        return multi_shootingPercentagePerLvl;
    }

    public static int getBowStunMaxLvl() {
        return bowStunMaxLvl;
    }

    public static double getBowStunPercentagePerLvl() {
        return bowStunPercentagePerLvl;
    }

    public static int getGreedMaxLvl() {
        return greedMaxLvl;
    }

    public static double getGreedPercentagePerLvl() {
        return greedPercentagePerLvl;
    }
}
