package feudal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class FeudalValuesUtils {
    public static MongoClient mongoClient;
    public static MongoDatabase database;
    public static MongoCollection<Document> playersCollection;
    public static MongoCollection<Document> kingdomsCollection;

    public static int vampirismMaxLvl;
    public static double vampirismPercentagePerLvl;

    public static int doubleDamageMaxLvl;
    public static double doubleDamagePercentagePerLvl;

    public static int blindnessMaxLvl;
    public static double blindnessPercentagePerLvl;

    public static int slowdownMaxLvl;
    public static double slowdownPercentagePerLvl;
    public static int slowdownTime;
    public static double slowdownTimePercentagePerLvl;

    public static int desiccationMaxLvl;
    public static double desiccationPercentagePerLvl;
    public static int desiccationTime;
    public static double desiccationTimePercentagePerLvl;

    public static int swordStunMaxLvl;
    public static double swordStunPercentagePerLvl;

    public static int levitationMaxLvl;
    public static double levitationPercentagePerLvl;

    public static int poisoningMaxLvl;
    public static double poisoningPercentagePerLvl;

    public static int nauseaMaxLvl;
    public static double nauseaPercentagePerLvl;

    public static int hookMaxLvl;
    public static double hookPercentagePerLvl;

    public static int multi_shootingMaxLvl;
    public static double multi_shootingPercentagePerLvl;

    public static int bowStunMaxLvl;
    public static double bowStunPercentagePerLvl;

    public static int greedMaxLvl;
    public static double greedPercentagePerLvl;

}
