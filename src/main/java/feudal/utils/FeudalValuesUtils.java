package feudal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

@Getter
public class FeudalValuesUtils {
    public static MongoClient mongoClient;
    public static MongoDatabase database;
    public static MongoCollection<Document> playersCollection;
    public static MongoCollection<Document> kingdomsCollection;
    public static int clerkDiscount;
    public static int cookFirstGain;
    public static int cookTimeFirstGain;
    public static int cookSecondGain;
    public static int cookTimeSecondGain;
}
