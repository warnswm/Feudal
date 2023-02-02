package feudal.utils;

import feudal.Feudal;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.database.KingdomInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class PlannedActivities {

    public static void taxCollection() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {

            if (CacheKingdomsMap.getKingdomInfo().isEmpty()) return;

            for (Map.Entry<String, KingdomInfo> kingdom : CacheKingdomsMap.getKingdomInfo().entrySet()) {

                KingdomInfo kingdomInfo = kingdom.getValue();
                int reputation = kingdom.getValue().getReputation();

                if (reputation <= 0) {

                    kingdomInfo.takeAllTerritory();
                    kingdomInfo.takeAllPrivateTerritory();
                    return;

                }

                int balance = kingdom.getValue().getBalance();

                int landTax = reputation == 1000 ? 1500 : 1500 * (1000 - reputation) / 1000 + 1500;
                int taxOnResidents = reputation == 1000 ? 300 : 300 * (1000 - reputation) / 1000 + 300;


                kingdomInfo.takeBalance(balance / 100 * 3);

                for (String chunk : kingdom.getValue().getTerritory()) {

                    if (balance < landTax) {

                        kingdomInfo.takeTerritory(chunk);
                        kingdomInfo.takePrivateTerritory(chunk);
                        continue;

                    }

                    kingdomInfo.takeBalance(landTax);
                }

                for (String ignored : kingdom.getValue().getMembers()) {

                    if (balance < taxOnResidents) {

                        kingdomInfo.takeReputation(30);
                        continue;

                    }

                    kingdomInfo.takeBalance(taxOnResidents);

                }

            }

        }, 0L), 432000L);

        System.gc();

    }

    public static void restart() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {
            //restart
        }, 0L), 434400L);

    }

    private static void scheduleRepeatAtTime(Plugin plugin, Runnable task, long period) {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0L, period);

    }
}
