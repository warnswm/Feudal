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
    public static double vampirismPersent;
    public static int doubleDamageMaxLvl;
    public static double doubleDamagePersent;
    public static int blindnessMaxLvl;
    public static double blindnessPersent;
    public static int slowdownMaxLvl;
    public static double slowdownPersent;
    public static int desiccationMaxLvl;
    public static double desiccationPersent;
    public static int swordStunMaxLvl;
    public static double swordStunPersent;
    public static int levitationMaxLvl;
    public static double levitationPersent;
    public static int poisoningMaxLvl;
    public static double poisoningPersent;
    public static int nauseaMaxLvl;
    public static double nauseaPersent;
    public static int hookMaxLvl;
    public static double hookPersent;
    public static int multi_shootingMaxLvl;
    public static double multi_shootingPersent;
    public static int bowStunMaxLvl;
    public static double bowStunPersent;
    public static int greedMaxLvl;
    public static double greedPersent;

}
