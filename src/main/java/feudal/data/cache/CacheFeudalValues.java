package feudal.data.cache;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import feudal.data.DonatEnchantment;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.bson.Document;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class CacheFeudalValues {

    @Getter
    @Setter
    private MongoClient mongoClient;
    @Getter
    @Setter
    private MongoDatabase database;
    @Getter
    @Setter
    private MongoCollection<Document> playersCollection;
    @Getter
    @Setter
    private MongoCollection<Document> kingdomsCollection;

    @Getter
    @Setter
    private Map<EntityType, Integer> moneyForMobs = new HashMap<>();

    @Getter
    @Setter
    private Map<String, DonatEnchantment> donatEnchantment = new HashMap<>();

    @Getter
    @Setter
    private Map<Integer, Float> clerkTakeExp = new HashMap<>();

    @Getter
    @Setter
    private int landTax;
    @Getter
    @Setter
    private int taxOnResidents;
    @Getter
    @Setter
    private int landRemovingReputation;
    @Getter
    @Setter
    private int residentsRemovingReputation;
    @Getter
    @Setter
    private double taxTreasuryPercent;
    @Getter
    @Setter
    private int timeTaxCollection;

    @Getter
    @Setter
    private int timeRestart;
    @Getter
    @Setter
    private int timeClearMail;

    @Getter
    @Setter
    private double strengthPercentageCost;
    @Getter
    @Setter
    private double staminaPercentageCost;
    @Getter
    @Setter
    private double luckPercentageCost;
    @Getter
    @Setter
    private double survivabilityPercentageCost;
    @Getter
    @Setter
    private double speedPercentageCost;

}
