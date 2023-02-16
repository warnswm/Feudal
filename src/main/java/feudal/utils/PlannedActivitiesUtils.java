package feudal.utils;

import feudal.Feudal;
import feudal.data.builder.FeudalKingdom;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class PlannedActivitiesUtils {

    private static final FeudalValuesUtils feudalValuesUtils = new FeudalValuesUtils();

    public static void taxCollection() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {

            if (CacheFeudalKingdoms.getKingdomInfo().isEmpty()) return;

            for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

                FeudalKingdom cacheFeudalKingdom = kingdom.getValue();
                int reputation = kingdom.getValue().getReputation();

                if (reputation <= 0) {

                    cacheFeudalKingdom.takeAllTerritory();
                    cacheFeudalKingdom.takeAllPrivateTerritory();
                    cacheFeudalKingdom.takeReputation(feudalValuesUtils.getLandRemovingReputation());

                    return;

                }

                int balance = kingdom.getValue().getBalance();
                int landTaxCfg = feudalValuesUtils.getLandTax();
                int residentsTaxCfg = feudalValuesUtils.getTaxOnResidents();

                int landTax = reputation == 1000 ? landTaxCfg : landTaxCfg * (1000 - reputation) / 1000 + landTaxCfg;
                int taxOnResidents = reputation == 1000 ? residentsTaxCfg : residentsTaxCfg * (1000 - reputation) / 1000 + residentsTaxCfg;


                cacheFeudalKingdom.takeBalance((int) (balance / 100 * feudalValuesUtils.getTaxTreasuryPercent()));

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

                        cacheFeudalKingdom.takeReputation(feudalValuesUtils.getResidentsRemovingReputation());
                        continue;

                    }

                    cacheFeudalKingdom.takeBalance(taxOnResidents);

                }

            }

        }, 0L), feudalValuesUtils.getTimeTaxCollection());

    }

    public static void eventCall() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {


        }, 0L), MathUtils.getRandomInt(72000, 360001));

    }

    public static void restart() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {

            Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Рестарт!"));

            LoadAndSaveDataUtils.saveAllKingdoms();
            LoadAndSaveDataUtils.saveAllConfigs();

            CacheFeudalPlayers.getFeudalPlayerInfo().clear();
            CacheFeudalKingdoms.getKingdomInfo().clear();

        }, 0L), feudalValuesUtils.getTimeRestart());

    }

    public static void scheduleRepeatAtTime(Plugin plugin, Runnable task, long period) {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0L, period);

    }
}
