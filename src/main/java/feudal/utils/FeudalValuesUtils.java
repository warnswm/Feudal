package feudal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.bson.Document;

@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalValuesUtils {

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
    private int vampirismMaxLvl;
    @Getter
    @Setter
    private double vampirismPercentagePerLvl;

    @Getter
    @Setter
    private int doubleDamageMaxLvl;
    @Getter
    @Setter
    private double doubleDamagePercentagePerLvl;

    @Getter
    @Setter
    private int blindnessMaxLvl;

    @Getter
    @Setter
    private double blindnessPercentagePerLvl;

    @Getter
    @Setter
    private int blindnessTime;

    @Getter
    @Setter
    private double blindnessTimePercentagePerLvl;

    @Getter
    @Setter
    private int slowdownMaxLvl;
    @Getter
    @Setter
    private double slowdownPercentagePerLvl;
    @Getter
    @Setter
    private int slowdownTime;
    @Getter
    @Setter
    private double slowdownTimePercentagePerLvl;

    @Getter
    @Setter
    private int desiccationMaxLvl;
    @Getter
    @Setter
    private double desiccationPercentagePerLvl;
    @Getter
    @Setter
    private int desiccationTime;
    @Getter
    @Setter
    private double desiccationTimePercentagePerLvl;

    @Getter
    @Setter
    private int swordStunMaxLvl;
    @Getter
    @Setter
    private double swordStunPercentagePerLvl;
    @Getter
    @Setter
    private int swordStunTime;
    @Getter
    @Setter
    private double swordStunTimePercentagePerLvl;

    @Getter
    @Setter
    private int levitationMaxLvl;
    @Getter
    @Setter
    private double levitationPercentagePerLvl;
    @Getter
    @Setter
    private int levitationTime;
    @Getter
    @Setter
    private double levitationTimePercentagePerLvl;

    @Getter
    @Setter
    private int poisoningMaxLvl;
    @Getter
    @Setter
    private double poisoningPercentagePerLvl;
    @Getter
    @Setter
    private int poisoningTime;
    @Getter
    @Setter
    private double poisoningTimePercentagePerLvl;

    @Getter
    @Setter
    private int nauseaMaxLvl;
    @Getter
    @Setter
    private double nauseaPercentagePerLvl;
    @Getter
    @Setter
    private int nauseaTime;
    @Getter
    @Setter
    private double nauseaTimePercentagePerLvl;

    @Getter
    @Setter
    private int hookMaxLvl;
    @Getter
    @Setter
    private double hookPercentagePerLvl;

    @Getter
    @Setter
    private int multi_shootingMaxLvl;
    @Getter
    @Setter
    private double multi_shootingPercentagePerLvl;

    @Getter
    @Setter
    private int bowStunMaxLvl;
    @Getter
    @Setter
    private double bowStunPercentagePerLvl;
    @Getter
    @Setter
    private int bowStunTime;
    @Getter
    @Setter
    private double bowStunTimePercentagePerLvl;

    @Getter
    @Setter
    private int greedMaxLvl;
    @Getter
    @Setter
    private double greedPercentagePerLvl;

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
    private int moneyForBat;
    @Getter
    @Setter
    private int moneyForChicken;
    @Getter
    @Setter
    private int moneyForCow;
    @Getter
    @Setter
    private int moneyForMushroomCow;
    @Getter
    @Setter
    private int moneyForHorse;
    @Getter
    @Setter
    private int moneyForOcelot;
    @Getter
    @Setter
    private int moneyForParrot;
    @Getter
    @Setter
    private int moneyForPolarBear;
    @Getter
    @Setter
    private int moneyForPig;
    @Getter
    @Setter
    private int moneyForRabbit;
    @Getter
    @Setter
    private int moneyForSheep;
    @Getter
    @Setter
    private int moneyForSnowMan;
    @Getter
    @Setter
    private int moneyForSquiq;
    @Getter
    @Setter
    private int moneyForVillager;
    @Getter
    @Setter
    private int moneyForCaveSpider;
    @Getter
    @Setter
    private int moneyForEnderman;
    @Getter
    @Setter
    private int moneyForIronGolem;
    @Getter
    @Setter
    private int moneyForLlama;
    @Getter
    @Setter
    private int moneyForSpider;
    @Getter
    @Setter
    private int moneyForWolf;
    @Getter
    @Setter
    private int moneyForBlaze;
    @Getter
    @Setter
    private int moneyForCreeper;
    @Getter
    @Setter
    private int moneyForElderGuardian;
    @Getter
    @Setter
    private int moneyForEndermite;
    @Getter
    @Setter
    private int moneyForEvolker;
    @Getter
    @Setter
    private int moneyForGhast;
    @Getter
    @Setter
    private int moneyForGuardian;
    @Getter
    @Setter
    private int moneyForHask;
    @Getter
    @Setter
    private int moneyForMagmaCube;
    @Getter
    @Setter
    private int moneyForShulker;
    @Getter
    @Setter
    private int moneyForSilverfish;
    @Getter
    @Setter
    private int moneyForSkeleton;
    @Getter
    @Setter
    private int moneyForSlime;
    @Getter
    @Setter
    private int moneyForStray;
    @Getter
    @Setter
    private int moneyForVex;
    @Getter
    @Setter
    private int moneyForVindicator;
    @Getter
    @Setter
    private int moneyForWitch;
    @Getter
    @Setter
    private int moneyForWitherSkeleton;
    @Getter
    @Setter
    private int moneyForZombie;
    @Getter
    @Setter
    private int moneyForZombieVillager;
    @Getter
    @Setter
    private int moneyForEnderDragon;
    @Getter
    @Setter
    private int moneyForWither;

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
