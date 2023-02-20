package feudal.utils;

import feudal.Feudal;
import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class PlannedActivitiesUtils {

    public static void taxCollection() {

        new BukkitRunnable() {

            @Override
            public void run() {

                if (CacheFeudalKingdoms.getKingdomInfo().isEmpty()) return;

                for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

                    FeudalKingdom cacheFeudalKingdom = kingdom.getValue();
                    int reputation = kingdom.getValue().getReputation();

                    int oldTerritorySize = cacheFeudalKingdom.getTerritory().size();
                    int oldBalance = cacheFeudalKingdom.getBalance();
                    int oldReputation = cacheFeudalKingdom.getReputation();

                    if (reputation <= 0) {

                        cacheFeudalKingdom.takeAllTerritory();
                        cacheFeudalKingdom.takeAllPrivateTerritory();
                        cacheFeudalKingdom.takeReputation(FeudalValuesUtils.getLandRemovingReputation());

                        return;

                    }

                    int balance = kingdom.getValue().getBalance();
                    int landTaxCfg = FeudalValuesUtils.getLandTax();
                    int residentsTaxCfg = FeudalValuesUtils.getTaxOnResidents();

                    int landTax = reputation >= 1000 ? landTaxCfg : landTaxCfg * (1000 - reputation) / 1000 + landTaxCfg;
                    int taxOnResidents = reputation >= 1000 ? residentsTaxCfg : residentsTaxCfg * (1000 - reputation) / 1000 + residentsTaxCfg;


                    cacheFeudalKingdom.takeBalance((int) (balance / 100 * FeudalValuesUtils.getTaxTreasuryPercent()));

                    for (Integer chunkHashCode : kingdom.getValue().getTerritory()) {

                        if (balance < landTax) {

                            cacheFeudalKingdom.takeTerritory(chunkHashCode);
                            cacheFeudalKingdom.takePrivateTerritory(chunkHashCode);
                            cacheFeudalKingdom.takeReputation(FeudalValuesUtils.getLandRemovingReputation());

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


                    FeudalPlayer feudalKingPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayer(cacheFeudalKingdom.getKingUUID()));
                    feudalKingPlayer.addLetter("Дань", "Собрано дани - " + (oldBalance - cacheFeudalKingdom.getBalance()) + " золота. " +
                            "Отнято земель - " + (oldTerritorySize - cacheFeudalKingdom.getTerritory().size()) +
                            ". Отнято репутации - " + (oldReputation - cacheFeudalKingdom.getReputation()));

                }

            }
        }.runTaskTimer(Feudal.getPlugin(), FeudalValuesUtils.getTimeTaxCollection() * 72000L, FeudalValuesUtils.getTimeTaxCollection() * 72000L);

    }

    public static void restart() {

        new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Рестарт!"));

                LoadAndSaveDataUtils.saveAllKingdoms();
                LoadAndSaveDataUtils.saveAllConfigs();

                CacheFeudalPlayers.getFeudalPlayerInfo().clear();
                CacheFeudalKingdoms.getKingdomInfo().clear();

            }
        }.runTaskTimer(Feudal.getPlugin(), FeudalValuesUtils.getTimeRestart() * 72000L, FeudalValuesUtils.getTimeRestart() * 72000L);

    }

    public static void clearMail() {

        new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Рестарт!"));

                LoadAndSaveDataUtils.saveAllKingdoms();
                LoadAndSaveDataUtils.saveAllConfigs();

                CacheFeudalPlayers.getFeudalPlayerInfo().clear();
                CacheFeudalKingdoms.getKingdomInfo().clear();

            }

        }.runTaskTimer(Feudal.getPlugin(), FeudalValuesUtils.getTimeClearMail() * 1728000L, FeudalValuesUtils.getTimeClearMail() * 1728000L);

    }

    public static void secretOrder() {

        List<Player> onlinePlayers = (List<Player>) Bukkit.getOnlinePlayers();

        if (onlinePlayers.isEmpty()) return;

        new BukkitRunnable() {

            @Override
            public void run() {

                Player player = onlinePlayers.get(ThreadLocalRandom.current().nextInt(0, onlinePlayers.size()));
                FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

                feudalPlayer.addLetter("Таинсвенный незнакомец", "Здаствуй " + player.getName() + ", ты, наверное, не помнишь меня... Но я отлично тебя помню. Могу пожелать тебе удачи, за твою голову назначена большая награда!");

            }

        }.runTaskTimer(Feudal.getPlugin(), 30000, 30000);

    }
}
