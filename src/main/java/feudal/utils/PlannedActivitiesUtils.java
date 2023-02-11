package feudal.utils;

import feudal.Feudal;
import feudal.data.builder.FeudalKingdom;
import feudal.data.cache.CacheFeudalKingdoms;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class PlannedActivitiesUtils {

    public static void taxCollection() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {

            if (CacheFeudalKingdoms.getKingdomInfo().isEmpty()) return;

            for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

                FeudalKingdom cacheFeudalKingdom = kingdom.getValue();
                int reputation = kingdom.getValue().getReputation();

                if (reputation <= 0) {

                    cacheFeudalKingdom.takeAllTerritory();
                    cacheFeudalKingdom.takeAllPrivateTerritory();
                    return;

                }

                long balance = kingdom.getValue().getBalance();

                int landTax = reputation == 1000 ? 1500 : 1500 * (1000 - reputation) / 1000 + 1500;
                int taxOnResidents = reputation == 1000 ? 300 : 300 * (1000 - reputation) / 1000 + 300;


                cacheFeudalKingdom.takeBalance(balance / 100 * 3);

                for (Chunk chunk : kingdom.getValue().getTerritory()) {

                    if (balance < landTax) {

                        cacheFeudalKingdom.takeTerritory(chunk);
                        cacheFeudalKingdom.takePrivateTerritory(chunk);
                        continue;

                    }

                    cacheFeudalKingdom.takeBalance(landTax);
                }

                for (Player ignored : kingdom.getValue().getMembers()) {

                    if (balance < taxOnResidents) {

                        cacheFeudalKingdom.takeReputation(30);
                        continue;

                    }

                    cacheFeudalKingdom.takeBalance(taxOnResidents);

                }

            }

        }, 0L), 432000);

        System.gc();

    }

    public static void eventCall() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {


        }, 0L), MathUtils.getRandomInt(72000, 360001));

    }

    public static void changeOfSeason() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {


        }, 0L), 1);

    }


    public static void restart() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {
            //restart
        }, 0L), 576000);

    }

    private static void scheduleRepeatAtTime(Plugin plugin, Runnable task, long period) {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0L, period);

    }
}
