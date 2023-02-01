package feudal.utils;

import feudal.Feudal;
import feudal.databaseAndCache.CacheKingdoms;
import feudal.databaseAndCache.KingdomInfo;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;

public class PlannedActivities {

    public static void taxCollection() {

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> {

            if (CacheKingdoms.getKingdomInfo().isEmpty()) return;

            for (Map.Entry<String, KingdomInfo> kingdom : CacheKingdoms.getKingdomInfo().entrySet()) {

                KingdomInfo kingdomInfo = kingdom.getValue();
                int reputation = kingdom.getValue().getReputation();

                if (reputation <= 0) {

                    kingdomInfo.takeAllTerritory();
                    return;

                }


                int balance = kingdom.getValue().getBalance();
                List<Chunk> territory = kingdom.getValue().getTerritory();
                List<String> members = kingdom.getValue().getMembers();

                int landTax = reputation == 1000 ? 1500 : 1500 * (1000 - reputation) / 1000 + 1500;
                int taxOnResidents = reputation == 1000 ? 300 : 300 * (1000 - reputation) / 1000 + 300;


                kingdomInfo.takeBalance(balance / 100 * 3);

                for (Chunk chunk : territory) {

                    if (balance < landTax) {

                        kingdomInfo.takeTerritory(chunk);
                        continue;
                    }

                    kingdomInfo.takeBalance(landTax);
                }

                for (String ignored : members) {

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

        scheduleRepeatAtTime(Feudal.getPlugin(), () -> Bukkit.getScheduler().runTaskLater(Feudal.getPlugin(), () -> Bukkit.spigot().restart(), 0L), 434400L);

    }
    private static void scheduleRepeatAtTime(Plugin plugin, Runnable task, long period) {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0L, period);

    }
}
