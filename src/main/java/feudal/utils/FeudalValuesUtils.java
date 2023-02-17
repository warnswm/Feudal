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
    private static int bowStunTime;
    private static double bowStunTimePercentagePerLvl;

    private static int greedMaxLvl;
    private static double greedPercentagePerLvl;

    private static int landTax;
    private static int taxOnResidents;
    private static int landRemovingReputation;
    private static int residentsRemovingReputation;
    private static double taxTreasuryPercent;
    private static int timeTaxCollection;

    private static int timeRestart;

    private static int moneyForBat;
    private static int moneyForChicken;
    private static int moneyForCow;
    private static int moneyForMushroomCow;
    private static int moneyForHorse;
    private static int moneyForOcelot;
    private static int moneyForParrot;
    private static int moneyForPolarBear;
    private static int moneyForPig;
    private static int moneyForRabbit;
    private static int moneyForSheep;
    private static int moneyForSnowMan;
    private static int moneyForSquiq;
    private static int moneyForVillager;
    private static int moneyForCaveSpider;
    private static int moneyForEnderman;
    private static int moneyForIronGolem;
    private static int moneyForLlama;
    private static int moneyForSpider;
    private static int moneyForWolf;
    private static int moneyForBlaze;
    private static int moneyForCreeper;
    private static int moneyForElderGuardian;
    private static int moneyForEndermite;
    private static int moneyForEvolker;
    private static int moneyForGhast;
    private static int moneyForGuardian;
    private static int moneyForHask;
    private static int moneyForMagmaCube;
    private static int moneyForShulker;
    private static int moneyForSilverfish;
    private static int moneyForSkeleton;
    private static int moneyForSlime;
    private static int moneyForStray;
    private static int moneyForVex;
    private static int moneyForVindicator;
    private static int moneyForWitch;
    private static int moneyForWitherSkeleton;
    private static int moneyForZombie;
    private static int moneyForZombieVillager;
    private static int moneyForEnderDragon;
    private static int moneyForWither;

    private static double strengthPercentageCost;
    private static double staminaPercentageCost;
    private static double luckPercentageCost;
    private static double survivabilityPercentageCost;
    private static double speedPercentageCost;

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

    public static int getBowStunTime() {
        return bowStunTime;
    }

    public static void setBowStunTime(int bowStunTime) {
        FeudalValuesUtils.bowStunTime = bowStunTime;
    }

    public static double getBowStunTimePercentagePerLvl() {
        return bowStunTimePercentagePerLvl;
    }

    public static void setBowStunTimePercentagePerLvl(double bowStunTimePercentagePerLvl) {
        FeudalValuesUtils.bowStunTimePercentagePerLvl = bowStunTimePercentagePerLvl;
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

    public static double getTaxTreasuryPercent() {
        return taxTreasuryPercent;
    }

    public static void setTaxTreasuryPercent(double taxTreasuryPercent) {
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

    public static int getMoneyForBat() {
        return moneyForBat;
    }

    public static void setMoneyForBat(int moneyForBat) {
        FeudalValuesUtils.moneyForBat = moneyForBat;
    }

    public static int getMoneyForChicken() {
        return moneyForChicken;
    }

    public static void setMoneyForChicken(int moneyForChicken) {
        FeudalValuesUtils.moneyForChicken = moneyForChicken;
    }

    public static int getMoneyForCow() {
        return moneyForCow;
    }

    public static void setMoneyForCow(int moneyForCow) {
        FeudalValuesUtils.moneyForCow = moneyForCow;
    }

    public static int getMoneyForMushroomCow() {
        return moneyForMushroomCow;
    }

    public static void setMoneyForMushroomCow(int moneyForMushroomCow) {
        FeudalValuesUtils.moneyForMushroomCow = moneyForMushroomCow;
    }

    public static int getMoneyForHorse() {
        return moneyForHorse;
    }

    public static void setMoneyForHorse(int moneyForHorse) {
        FeudalValuesUtils.moneyForHorse = moneyForHorse;
    }

    public static int getMoneyForOcelot() {
        return moneyForOcelot;
    }

    public static void setMoneyForOcelot(int moneyForOcelot) {
        FeudalValuesUtils.moneyForOcelot = moneyForOcelot;
    }

    public static int getMoneyForParrot() {
        return moneyForParrot;
    }

    public static void setMoneyForParrot(int moneyForParrot) {
        FeudalValuesUtils.moneyForParrot = moneyForParrot;
    }

    public static int getMoneyForPolarBear() {
        return moneyForPolarBear;
    }

    public static void setMoneyForPolarBear(int moneyForPolarBear) {
        FeudalValuesUtils.moneyForPolarBear = moneyForPolarBear;
    }

    public static int getMoneyForPig() {
        return moneyForPig;
    }

    public static void setMoneyForPig(int moneyForPig) {
        FeudalValuesUtils.moneyForPig = moneyForPig;
    }

    public static int getMoneyForRabbit() {
        return moneyForRabbit;
    }

    public static void setMoneyForRabbit(int moneyForRabbit) {
        FeudalValuesUtils.moneyForRabbit = moneyForRabbit;
    }

    public static int getMoneyForSheep() {
        return moneyForSheep;
    }

    public static void setMoneyForSheep(int moneyForSheep) {
        FeudalValuesUtils.moneyForSheep = moneyForSheep;
    }

    public static int getMoneyForSnowMan() {
        return moneyForSnowMan;
    }

    public static void setMoneyForSnowMan(int moneyForSnowMan) {
        FeudalValuesUtils.moneyForSnowMan = moneyForSnowMan;
    }

    public static int getMoneyForSquiq() {
        return moneyForSquiq;
    }

    public static void setMoneyForSquiq(int moneyForSquiq) {
        FeudalValuesUtils.moneyForSquiq = moneyForSquiq;
    }

    public static int getMoneyForVillager() {
        return moneyForVillager;
    }

    public static void setMoneyForVillager(int moneyForVillager) {
        FeudalValuesUtils.moneyForVillager = moneyForVillager;
    }

    public static int getMoneyForCaveSpider() {
        return moneyForCaveSpider;
    }

    public static void setMoneyForCaveSpider(int moneyForCaveSpider) {
        FeudalValuesUtils.moneyForCaveSpider = moneyForCaveSpider;
    }

    public static int getMoneyForEnderman() {
        return moneyForEnderman;
    }

    public static void setMoneyForEnderman(int moneyForEnderman) {
        FeudalValuesUtils.moneyForEnderman = moneyForEnderman;
    }

    public static int getMoneyForIronGolem() {
        return moneyForIronGolem;
    }

    public static void setMoneyForIronGolem(int moneyForIronGolem) {
        FeudalValuesUtils.moneyForIronGolem = moneyForIronGolem;
    }

    public static int getMoneyForLlama() {
        return moneyForLlama;
    }

    public static void setMoneyForLlama(int moneyForLlama) {
        FeudalValuesUtils.moneyForLlama = moneyForLlama;
    }

    public static int getMoneyForSpider() {
        return moneyForSpider;
    }

    public static void setMoneyForSpider(int moneyForSpider) {
        FeudalValuesUtils.moneyForSpider = moneyForSpider;
    }

    public static int getMoneyForWolf() {
        return moneyForWolf;
    }

    public static void setMoneyForWolf(int moneyForWolf) {
        FeudalValuesUtils.moneyForWolf = moneyForWolf;
    }

    public static int getMoneyForBlaze() {
        return moneyForBlaze;
    }

    public static void setMoneyForBlaze(int moneyForBlaze) {
        FeudalValuesUtils.moneyForBlaze = moneyForBlaze;
    }

    public static int getMoneyForCreeper() {
        return moneyForCreeper;
    }

    public static void setMoneyForCreeper(int moneyForCreeper) {
        FeudalValuesUtils.moneyForCreeper = moneyForCreeper;
    }

    public static int getMoneyForElderGuardian() {
        return moneyForElderGuardian;
    }

    public static void setMoneyForElderGuardian(int moneyForElderGuardian) {
        FeudalValuesUtils.moneyForElderGuardian = moneyForElderGuardian;
    }

    public static int getMoneyForEndermite() {
        return moneyForEndermite;
    }

    public static void setMoneyForEndermite(int moneyForEndermite) {
        FeudalValuesUtils.moneyForEndermite = moneyForEndermite;
    }

    public static int getMoneyForEvolker() {
        return moneyForEvolker;
    }

    public static void setMoneyForEvolker(int moneyForEvolker) {
        FeudalValuesUtils.moneyForEvolker = moneyForEvolker;
    }

    public static int getMoneyForGhast() {
        return moneyForGhast;
    }

    public static void setMoneyForGhast(int moneyForGhast) {
        FeudalValuesUtils.moneyForGhast = moneyForGhast;
    }

    public static int getMoneyForGuardian() {
        return moneyForGuardian;
    }

    public static void setMoneyForGuardian(int moneyForGuardian) {
        FeudalValuesUtils.moneyForGuardian = moneyForGuardian;
    }

    public static int getMoneyForHask() {
        return moneyForHask;
    }

    public static void setMoneyForHask(int moneyForHask) {
        FeudalValuesUtils.moneyForHask = moneyForHask;
    }

    public static int getMoneyForMagmaCube() {
        return moneyForMagmaCube;
    }

    public static void setMoneyForMagmaCube(int moneyForMagmaCube) {
        FeudalValuesUtils.moneyForMagmaCube = moneyForMagmaCube;
    }

    public static int getMoneyForShulker() {
        return moneyForShulker;
    }

    public static void setMoneyForShulker(int moneyForShulker) {
        FeudalValuesUtils.moneyForShulker = moneyForShulker;
    }

    public static int getMoneyForSilverfish() {
        return moneyForSilverfish;
    }

    public static void setMoneyForSilverfish(int moneyForSilverfish) {
        FeudalValuesUtils.moneyForSilverfish = moneyForSilverfish;
    }

    public static int getMoneyForSkeleton() {
        return moneyForSkeleton;
    }

    public static void setMoneyForSkeleton(int moneyForSkeleton) {
        FeudalValuesUtils.moneyForSkeleton = moneyForSkeleton;
    }

    public static int getMoneyForSlime() {
        return moneyForSlime;
    }

    public static void setMoneyForSlime(int moneyForSlime) {
        FeudalValuesUtils.moneyForSlime = moneyForSlime;
    }

    public static int getMoneyForStray() {
        return moneyForStray;
    }

    public static void setMoneyForStray(int moneyForStray) {
        FeudalValuesUtils.moneyForStray = moneyForStray;
    }

    public static int getMoneyForVex() {
        return moneyForVex;
    }

    public static void setMoneyForVex(int moneyForVex) {
        FeudalValuesUtils.moneyForVex = moneyForVex;
    }

    public static int getMoneyForVindicator() {
        return moneyForVindicator;
    }

    public static void setMoneyForVindicator(int moneyForVindicator) {
        FeudalValuesUtils.moneyForVindicator = moneyForVindicator;
    }

    public static int getMoneyForWitch() {
        return moneyForWitch;
    }

    public static void setMoneyForWitch(int moneyForWitch) {
        FeudalValuesUtils.moneyForWitch = moneyForWitch;
    }

    public static int getMoneyForWitherSkeleton() {
        return moneyForWitherSkeleton;
    }

    public static void setMoneyForWitherSkeleton(int moneyForWitherSkeleton) {
        FeudalValuesUtils.moneyForWitherSkeleton = moneyForWitherSkeleton;
    }

    public static int getMoneyForZombie() {
        return moneyForZombie;
    }

    public static void setMoneyForZombie(int moneyForZombie) {
        FeudalValuesUtils.moneyForZombie = moneyForZombie;
    }

    public static int getMoneyForZombieVillager() {
        return moneyForZombieVillager;
    }

    public static void setMoneyForZombieVillager(int moneyForZombieVillager) {
        FeudalValuesUtils.moneyForZombieVillager = moneyForZombieVillager;
    }

    public static int getMoneyForEnderDragon() {
        return moneyForEnderDragon;
    }

    public static void setMoneyForEnderDragon(int moneyForEnderDragon) {
        FeudalValuesUtils.moneyForEnderDragon = moneyForEnderDragon;
    }

    public static int getMoneyForWither() {
        return moneyForWither;
    }

    public static void setMoneyForWither(int moneyForWither) {
        FeudalValuesUtils.moneyForWither = moneyForWither;
    }

    public static double getStrengthPercentageCost() {
        return strengthPercentageCost;
    }

    public static void setStrengthPercentageCost(double strengthPercentageCost) {
        FeudalValuesUtils.strengthPercentageCost = strengthPercentageCost;
    }

    public static double getStaminaPercentageCost() {
        return staminaPercentageCost;
    }

    public static void setStaminaPercentageCost(double staminaPercentageCost) {
        FeudalValuesUtils.staminaPercentageCost = staminaPercentageCost;
    }

    public static double getLuckPercentageCost() {
        return luckPercentageCost;
    }

    public static void setLuckPercentageCost(double luckPercentageCost) {
        FeudalValuesUtils.luckPercentageCost = luckPercentageCost;
    }

    public static double getSurvivabilityPercentageCost() {
        return survivabilityPercentageCost;
    }

    public static void setSurvivabilityPercentageCost(double survivabilityPercentageCost) {
        FeudalValuesUtils.survivabilityPercentageCost = survivabilityPercentageCost;
    }

    public static double getSpeedPercentageCost() {
        return speedPercentageCost;
    }

    public static void setSpeedPercentageCost(double speedPercentageCost) {
        FeudalValuesUtils.speedPercentageCost = speedPercentageCost;
    }
}
