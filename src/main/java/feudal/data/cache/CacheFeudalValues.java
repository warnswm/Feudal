package feudal.data.cache;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import feudal.data.DonatEnchantment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.bson.Document;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheFeudalValues {

    @Getter
    @Setter
    MongoClient mongoClient;
    @Getter
    @Setter
    MongoDatabase database;
    @Getter
    @Setter
    MongoCollection<Document> playersCollection;
    @Getter
    @Setter
    MongoCollection<Document> kingdomsCollection;

    @Getter
    @Setter
    Map<EntityType, Integer> moneyForMobs = new HashMap<>();

    @Getter
    @Setter
    Map<String, DonatEnchantment> donatEnchantment = new HashMap<>();

    @Getter
    @Setter
    int landTax;
    @Getter
    @Setter
    int taxOnResidents;
    @Getter
    @Setter
    int landRemovingReputation;
    @Getter
    @Setter
    int residentsRemovingReputation;
    @Getter
    @Setter
    double taxTreasuryPercent;
    @Getter
    @Setter
    int timeTaxCollection;

    @Getter
    @Setter
    int timeRestart;
    @Getter
    @Setter
    int timeClearMail;

    @Getter
    @Setter
    double strengthPercentageCost;
    @Getter
    @Setter
    double staminaPercentageCost;
    @Getter
    @Setter
    double luckPercentageCost;
    @Getter
    @Setter
    double survivabilityPercentageCost;
    @Getter
    @Setter
    double speedPercentageCost;

}
