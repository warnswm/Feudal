package feudal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalConfigsValuesUtils {
    static MongoClient mongoClient;
    static MongoDatabase database;
    static MongoCollection playersCollection;
    static MongoCollection kingdomsCollection;
    static int clerkDiscount;
    static int cookFirstGain;
    static int cookTimeFirstGain;
    static int cookSecondGain;
    static int cookTimeSecondGain;
}
