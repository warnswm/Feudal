package feudal.utils;

import feudal.Feudal;
import feudal.data.FeudalKingdom;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PlannedActivitiesUtils {

    public static void taxCollection() {

        TasksQueueUtils tasks = new TasksQueueUtils()

                .sleep(FeudalValuesUtils.getTimeTaxCollection(), TimeUnit.HOURS)
                .action(() -> {

                    if (CacheFeudalKingdoms.getKingdomInfo().isEmpty()) return;

                    for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

                        FeudalKingdom cacheFeudalKingdom = kingdom.getValue();
                        int reputation = kingdom.getValue().getReputation();

                        if (reputation <= 0) {

                            cacheFeudalKingdom.takeAllTerritory();
                            cacheFeudalKingdom.takeAllPrivateTerritory();
                            cacheFeudalKingdom.takeReputation(FeudalValuesUtils.getLandRemovingReputation());

                            return;

                        }

                        int balance = kingdom.getValue().getBalance();
                        int landTaxCfg = FeudalValuesUtils.getLandTax();
                        int residentsTaxCfg = FeudalValuesUtils.getTaxOnResidents();

                        int landTax = reputation == 1000 ? landTaxCfg : landTaxCfg * (1000 - reputation) / 1000 + landTaxCfg;
                        int taxOnResidents = reputation == 1000 ? residentsTaxCfg : residentsTaxCfg * (1000 - reputation) / 1000 + residentsTaxCfg;


                        cacheFeudalKingdom.takeBalance((int) (balance / 100 * FeudalValuesUtils.getTaxTreasuryPercent()));

                        for (Integer chunkHashCode : kingdom.getValue().getTerritory()) {

                            if (balance < landTax) {

                                cacheFeudalKingdom.takeTerritory(chunkHashCode);
                                cacheFeudalKingdom.takePrivateTerritory(chunkHashCode);
                                continue;

                            }

                            cacheFeudalKingdom.takeBalance(landTax);

                        }

                        for (String ignored : kingdom.getValue().getMembersUUID()) {

                            if (balance < taxOnResidents) {

                                cacheFeudalKingdom.takeReputation(FeudalValuesUtils.getResidentsRemovingReputation());
                                continue;

                            }

                            cacheFeudalKingdom.takeBalance(taxOnResidents);

                        }

                    }

                });

        tasks.start(Feudal.getPlugin());

    }

    public static void restart() {

        TasksQueueUtils tasks = new TasksQueueUtils()

                .sleep(FeudalValuesUtils.getTimeRestart(), TimeUnit.HOURS)
                .action(() -> {

                    Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Рестарт!"));

                    LoadAndSaveDataUtils.saveAllKingdoms();
                    LoadAndSaveDataUtils.saveAllConfigs();

                    CacheFeudalPlayers.getFeudalPlayerInfo().clear();
                    CacheFeudalKingdoms.getKingdomInfo().clear();

                });

        tasks.start(Feudal.getPlugin());

    }

    public static void clearMail() {

        TasksQueueUtils tasks = new TasksQueueUtils()

                .sleep(FeudalValuesUtils.getTimeClearMail(), TimeUnit.HOURS)
                .action(() -> {

                    Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Рестарт!"));

                    LoadAndSaveDataUtils.saveAllKingdoms();
                    LoadAndSaveDataUtils.saveAllConfigs();

                    CacheFeudalPlayers.getFeudalPlayerInfo().clear();
                    CacheFeudalKingdoms.getKingdomInfo().clear();

                });

        tasks.start(Feudal.getPlugin());

    }
}
