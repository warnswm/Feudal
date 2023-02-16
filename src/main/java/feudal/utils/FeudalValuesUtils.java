package feudal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bson.Document;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalValuesUtils {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> playersCollection;
    MongoCollection<Document> kingdomsCollection;

    int vampirismMaxLvl;
    double vampirismPercentagePerLvl;

    int doubleDamageMaxLvl;
    double doubleDamagePercentagePerLvl;

    int blindnessMaxLvl;
    double blindnessPercentagePerLvl;
    int blindnessTime;
    double blindnessTimePercentagePerLvl;

    int slowdownMaxLvl;
    double slowdownPercentagePerLvl;
    int slowdownTime;
    double slowdownTimePercentagePerLvl;

    int desiccationMaxLvl;
    double desiccationPercentagePerLvl;
    int desiccationTime;
    double desiccationTimePercentagePerLvl;

    int swordStunMaxLvl;
    double swordStunPercentagePerLvl;
    int swordStunTime;
    double swordStunTimePercentagePerLvl;

    int levitationMaxLvl;
    double levitationPercentagePerLvl;
    int levitationTime;
    double levitationTimePercentagePerLvl;

    int poisoningMaxLvl;
    double poisoningPercentagePerLvl;
    int poisoningTime;
    double poisoningTimePercentagePerLvl;

    int nauseaMaxLvl;
    double nauseaPercentagePerLvl;
    int nauseaTime;
    double nauseaTimePercentagePerLvl;

    int hookMaxLvl;
    double hookPercentagePerLvl;

    int multi_shootingMaxLvl;
    double multi_shootingPercentagePerLvl;

    int bowStunMaxLvl;
    double bowStunPercentagePerLvl;

    int greedMaxLvl;
    double greedPercentagePerLvl;

    int landTax;
    int taxOnResidents;
    int landRemovingReputation;
    int residentsRemovingReputation;
    float taxTreasuryPercent;
    int timeTaxCollection;

    int timeRestart;
}
