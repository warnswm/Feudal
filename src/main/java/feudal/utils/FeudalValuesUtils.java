package feudal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bson.Document;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalValuesUtils {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> playersCollection;
    private static MongoCollection<Document> kingdomsCollection;

    private static int vampirismMaxLvl;
    private static double vampirismPercentagePerLvl;

    private static int doubleDamageMaxLvl;
    private static double doubleDamagePercentagePerLvl;

    private static int blindnessMaxLvl;
    private static double blindnessPercentagePerLvl;
    private static int blindnessTime;
    private static double blindnessTimePercentagePerLvl;

    private static int slowdownMaxLvl;
    private static double slowdownPercentagePerLvl;
    private static int slowdownTime;
    private static double slowdownTimePercentagePerLvl;

    private static int desiccationMaxLvl;
    private static double desiccationPercentagePerLvl;
    private static int desiccationTime;
    private static double desiccationTimePercentagePerLvl;

    private static int swordStunMaxLvl;
    private static double swordStunPercentagePerLvl;
    private static int swordStunTime;
    private static double swordStunTimePercentagePerLvl;

    private static int levitationMaxLvl;
    private static double levitationPercentagePerLvl;
    private static int levitationTime;
    private static double levitationTimePercentagePerLvl;

    private static int poisoningMaxLvl;
    private static double poisoningPercentagePerLvl;
    private static int poisoningTime;
    private static double poisoningTimePercentagePerLvl;

    private static int nauseaMaxLvl;
    private static double nauseaPercentagePerLvl;
    private static int nauseaTime;
    private static double nauseaTimePercentagePerLvl;

    private static int hookMaxLvl;
    private static double hookPercentagePerLvl;

    private static int multi_shootingMaxLvl;
    private static double multi_shootingPercentagePerLvl;

    private static int bowStunMaxLvl;
    private static double bowStunPercentagePerLvl;

    private static int greedMaxLvl;
    private static double greedPercentagePerLvl;

    private static int landTax;
    private static int taxOnResidents;
    private static int landRemovingReputation;
    private static int residentsRemovingReputation;
    private static float taxTreasuryPercent;
    private static int timeTaxCollection;

    private static int timeRestart;

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void setMongoClient(MongoClient mongoClient) {
        FeudalValuesUtils.mongoClient = mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void setDatabase(MongoDatabase database) {
        FeudalValuesUtils.database = database;
    }

    public static MongoCollection<Document> getPlayersCollection() {
        return playersCollection;
    }

    public static void setPlayersCollection(MongoCollection<Document> playersCollection) {
        FeudalValuesUtils.playersCollection = playersCollection;
    }

    public static MongoCollection<Document> getKingdomsCollection() {
        return kingdomsCollection;
    }

    public static void setKingdomsCollection(MongoCollection<Document> kingdomsCollection) {
        FeudalValuesUtils.kingdomsCollection = kingdomsCollection;
    }

    public static int getVampirismMaxLvl() {
        return vampirismMaxLvl;
    }

    public static void setVampirismMaxLvl(int vampirismMaxLvl) {
        FeudalValuesUtils.vampirismMaxLvl = vampirismMaxLvl;
    }

    public static double getVampirismPercentagePerLvl() {
        return vampirismPercentagePerLvl;
    }

    public static void setVampirismPercentagePerLvl(double vampirismPercentagePerLvl) {
        FeudalValuesUtils.vampirismPercentagePerLvl = vampirismPercentagePerLvl;
    }

    public static int getDoubleDamageMaxLvl() {
        return doubleDamageMaxLvl;
    }

    public static void setDoubleDamageMaxLvl(int doubleDamageMaxLvl) {
        FeudalValuesUtils.doubleDamageMaxLvl = doubleDamageMaxLvl;
    }

    public static double getDoubleDamagePercentagePerLvl() {
        return doubleDamagePercentagePerLvl;
    }

    public static void setDoubleDamagePercentagePerLvl(double doubleDamagePercentagePerLvl) {
        FeudalValuesUtils.doubleDamagePercentagePerLvl = doubleDamagePercentagePerLvl;
    }

    public static int getBlindnessMaxLvl() {
        return blindnessMaxLvl;
    }

    public static void setBlindnessMaxLvl(int blindnessMaxLvl) {
        FeudalValuesUtils.blindnessMaxLvl = blindnessMaxLvl;
    }

    public static double getBlindnessPercentagePerLvl() {
        return blindnessPercentagePerLvl;
    }

    public static void setBlindnessPercentagePerLvl(double blindnessPercentagePerLvl) {
        FeudalValuesUtils.blindnessPercentagePerLvl = blindnessPercentagePerLvl;
    }

    public static int getBlindnessTime() {
        return blindnessTime;
    }

    public static void setBlindnessTime(int blindnessTime) {
        FeudalValuesUtils.blindnessTime = blindnessTime;
    }

    public static double getBlindnessTimePercentagePerLvl() {
        return blindnessTimePercentagePerLvl;
    }

    public static void setBlindnessTimePercentagePerLvl(double blindnessTimePercentagePerLvl) {
        FeudalValuesUtils.blindnessTimePercentagePerLvl = blindnessTimePercentagePerLvl;
    }

    public static int getSlowdownMaxLvl() {
        return slowdownMaxLvl;
    }

    public static void setSlowdownMaxLvl(int slowdownMaxLvl) {
        FeudalValuesUtils.slowdownMaxLvl = slowdownMaxLvl;
    }

    public static double getSlowdownPercentagePerLvl() {
        return slowdownPercentagePerLvl;
    }

    public static void setSlowdownPercentagePerLvl(double slowdownPercentagePerLvl) {
        FeudalValuesUtils.slowdownPercentagePerLvl = slowdownPercentagePerLvl;
    }

    public static int getSlowdownTime() {
        return slowdownTime;
    }

    public static void setSlowdownTime(int slowdownTime) {
        FeudalValuesUtils.slowdownTime = slowdownTime;
    }

    public static double getSlowdownTimePercentagePerLvl() {
        return slowdownTimePercentagePerLvl;
    }

    public static void setSlowdownTimePercentagePerLvl(double slowdownTimePercentagePerLvl) {
        FeudalValuesUtils.slowdownTimePercentagePerLvl = slowdownTimePercentagePerLvl;
    }

    public static int getDesiccationMaxLvl() {
        return desiccationMaxLvl;
    }

    public static void setDesiccationMaxLvl(int desiccationMaxLvl) {
        FeudalValuesUtils.desiccationMaxLvl = desiccationMaxLvl;
    }

    public static double getDesiccationPercentagePerLvl() {
        return desiccationPercentagePerLvl;
    }

    public static void setDesiccationPercentagePerLvl(double desiccationPercentagePerLvl) {
        FeudalValuesUtils.desiccationPercentagePerLvl = desiccationPercentagePerLvl;
    }

    public static int getDesiccationTime() {
        return desiccationTime;
    }

    public static void setDesiccationTime(int desiccationTime) {
        FeudalValuesUtils.desiccationTime = desiccationTime;
    }

    public static double getDesiccationTimePercentagePerLvl() {
        return desiccationTimePercentagePerLvl;
    }

    public static void setDesiccationTimePercentagePerLvl(double desiccationTimePercentagePerLvl) {
        FeudalValuesUtils.desiccationTimePercentagePerLvl = desiccationTimePercentagePerLvl;
    }

    public static int getSwordStunMaxLvl() {
        return swordStunMaxLvl;
    }

    public static void setSwordStunMaxLvl(int swordStunMaxLvl) {
        FeudalValuesUtils.swordStunMaxLvl = swordStunMaxLvl;
    }

    public static double getSwordStunPercentagePerLvl() {
        return swordStunPercentagePerLvl;
    }

    public static void setSwordStunPercentagePerLvl(double swordStunPercentagePerLvl) {
        FeudalValuesUtils.swordStunPercentagePerLvl = swordStunPercentagePerLvl;
    }

    public static int getSwordStunTime() {
        return swordStunTime;
    }

    public static void setSwordStunTime(int swordStunTime) {
        FeudalValuesUtils.swordStunTime = swordStunTime;
    }

    public static double getSwordStunTimePercentagePerLvl() {
        return swordStunTimePercentagePerLvl;
    }

    public static void setSwordStunTimePercentagePerLvl(double swordStunTimePercentagePerLvl) {
        FeudalValuesUtils.swordStunTimePercentagePerLvl = swordStunTimePercentagePerLvl;
    }

    public static int getLevitationMaxLvl() {
        return levitationMaxLvl;
    }

    public static void setLevitationMaxLvl(int levitationMaxLvl) {
        FeudalValuesUtils.levitationMaxLvl = levitationMaxLvl;
    }

    public static double getLevitationPercentagePerLvl() {
        return levitationPercentagePerLvl;
    }

    public static void setLevitationPercentagePerLvl(double levitationPercentagePerLvl) {
        FeudalValuesUtils.levitationPercentagePerLvl = levitationPercentagePerLvl;
    }

    public static int getLevitationTime() {
        return levitationTime;
    }

    public static void setLevitationTime(int levitationTime) {
        FeudalValuesUtils.levitationTime = levitationTime;
    }

    public static double getLevitationTimePercentagePerLvl() {
        return levitationTimePercentagePerLvl;
    }

    public static void setLevitationTimePercentagePerLvl(double levitationTimePercentagePerLvl) {
        FeudalValuesUtils.levitationTimePercentagePerLvl = levitationTimePercentagePerLvl;
    }

    public static int getPoisoningMaxLvl() {
        return poisoningMaxLvl;
    }

    public static void setPoisoningMaxLvl(int poisoningMaxLvl) {
        FeudalValuesUtils.poisoningMaxLvl = poisoningMaxLvl;
    }

    public static double getPoisoningPercentagePerLvl() {
        return poisoningPercentagePerLvl;
    }

    public static void setPoisoningPercentagePerLvl(double poisoningPercentagePerLvl) {
        FeudalValuesUtils.poisoningPercentagePerLvl = poisoningPercentagePerLvl;
    }

    public static int getPoisoningTime() {
        return poisoningTime;
    }

    public static void setPoisoningTime(int poisoningTime) {
        FeudalValuesUtils.poisoningTime = poisoningTime;
    }

    public static double getPoisoningTimePercentagePerLvl() {
        return poisoningTimePercentagePerLvl;
    }

    public static void setPoisoningTimePercentagePerLvl(double poisoningTimePercentagePerLvl) {
        FeudalValuesUtils.poisoningTimePercentagePerLvl = poisoningTimePercentagePerLvl;
    }

    public static int getNauseaMaxLvl() {
        return nauseaMaxLvl;
    }

    public static void setNauseaMaxLvl(int nauseaMaxLvl) {
        FeudalValuesUtils.nauseaMaxLvl = nauseaMaxLvl;
    }

    public static double getNauseaPercentagePerLvl() {
        return nauseaPercentagePerLvl;
    }

    public static void setNauseaPercentagePerLvl(double nauseaPercentagePerLvl) {
        FeudalValuesUtils.nauseaPercentagePerLvl = nauseaPercentagePerLvl;
    }

    public static int getNauseaTime() {
        return nauseaTime;
    }

    public static void setNauseaTime(int nauseaTime) {
        FeudalValuesUtils.nauseaTime = nauseaTime;
    }

    public static double getNauseaTimePercentagePerLvl() {
        return nauseaTimePercentagePerLvl;
    }

    public static void setNauseaTimePercentagePerLvl(double nauseaTimePercentagePerLvl) {
        FeudalValuesUtils.nauseaTimePercentagePerLvl = nauseaTimePercentagePerLvl;
    }

    public static int getHookMaxLvl() {
        return hookMaxLvl;
    }

    public static void setHookMaxLvl(int hookMaxLvl) {
        FeudalValuesUtils.hookMaxLvl = hookMaxLvl;
    }

    public static double getHookPercentagePerLvl() {
        return hookPercentagePerLvl;
    }

    public static void setHookPercentagePerLvl(double hookPercentagePerLvl) {
        FeudalValuesUtils.hookPercentagePerLvl = hookPercentagePerLvl;
    }

    public static int getMulti_shootingMaxLvl() {
        return multi_shootingMaxLvl;
    }

    public static void setMulti_shootingMaxLvl(int multi_shootingMaxLvl) {
        FeudalValuesUtils.multi_shootingMaxLvl = multi_shootingMaxLvl;
    }

    public static double getMulti_shootingPercentagePerLvl() {
        return multi_shootingPercentagePerLvl;
    }

    public static void setMulti_shootingPercentagePerLvl(double multi_shootingPercentagePerLvl) {
        FeudalValuesUtils.multi_shootingPercentagePerLvl = multi_shootingPercentagePerLvl;
    }

    public static int getBowStunMaxLvl() {
        return bowStunMaxLvl;
    }

    public static void setBowStunMaxLvl(int bowStunMaxLvl) {
        FeudalValuesUtils.bowStunMaxLvl = bowStunMaxLvl;
    }

    public static double getBowStunPercentagePerLvl() {
        return bowStunPercentagePerLvl;
    }

    public static void setBowStunPercentagePerLvl(double bowStunPercentagePerLvl) {
        FeudalValuesUtils.bowStunPercentagePerLvl = bowStunPercentagePerLvl;
    }

    public static int getGreedMaxLvl() {
        return greedMaxLvl;
    }

    public static void setGreedMaxLvl(int greedMaxLvl) {
        FeudalValuesUtils.greedMaxLvl = greedMaxLvl;
    }

    public static double getGreedPercentagePerLvl() {
        return greedPercentagePerLvl;
    }

    public static void setGreedPercentagePerLvl(double greedPercentagePerLvl) {
        FeudalValuesUtils.greedPercentagePerLvl = greedPercentagePerLvl;
    }

    public static int getLandTax() {
        return landTax;
    }

    public static void setLandTax(int landTax) {
        FeudalValuesUtils.landTax = landTax;
    }

    public static int getTaxOnResidents() {
        return taxOnResidents;
    }

    public static void setTaxOnResidents(int taxOnResidents) {
        FeudalValuesUtils.taxOnResidents = taxOnResidents;
    }

    public static int getLandRemovingReputation() {
        return landRemovingReputation;
    }

    public static void setLandRemovingReputation(int landRemovingReputation) {
        FeudalValuesUtils.landRemovingReputation = landRemovingReputation;
    }

    public static int getResidentsRemovingReputation() {
        return residentsRemovingReputation;
    }

    public static void setResidentsRemovingReputation(int residentsRemovingReputation) {
        FeudalValuesUtils.residentsRemovingReputation = residentsRemovingReputation;
    }

    public static float getTaxTreasuryPercent() {
        return taxTreasuryPercent;
    }

    public static void setTaxTreasuryPercent(float taxTreasuryPercent) {
        FeudalValuesUtils.taxTreasuryPercent = taxTreasuryPercent;
    }

    public static int getTimeTaxCollection() {
        return timeTaxCollection;
    }

    public static void setTimeTaxCollection(int timeTaxCollection) {
        FeudalValuesUtils.timeTaxCollection = timeTaxCollection;
    }

    public static int getTimeRestart() {
        return timeRestart;
    }

    public static void setTimeRestart(int timeRestart) {
        FeudalValuesUtils.timeRestart = timeRestart;
    }
}
