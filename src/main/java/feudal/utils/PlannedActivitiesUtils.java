package feudal.utils;

import feudal.Feudal;
import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.cache.CacheFeudalValues;
import feudal.data.cache.CacheSpyPlayers;
import feudal.entity.TravelingMerchant;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class PlannedActivitiesUtils {

    public void taxCollection() {

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
                        return;

                    }

                    int landTaxCfg = CacheFeudalValues.getLandTax();
                    int residentsTaxCfg = CacheFeudalValues.getTaxOnResidents();

                    int landTax = reputation >= 1000 ? landTaxCfg : landTaxCfg * (1000 - reputation) / 1000 + landTaxCfg;
                    int taxOnResidents = reputation >= 1000 ? residentsTaxCfg : residentsTaxCfg * (1000 - reputation) / 1000 + residentsTaxCfg;


                    cacheFeudalKingdom.takeBalance((int) (oldBalance / 100 * CacheFeudalValues.getTaxTreasuryPercent()));

                    for (Integer chunkHashCode : kingdom.getValue().getTerritory()) {

                        if (oldBalance < landTax) {

                            cacheFeudalKingdom.takeTerritory(chunkHashCode);
                            cacheFeudalKingdom.takeReputation(CacheFeudalValues.getLandRemovingReputation());

                            continue;

                        }

                        cacheFeudalKingdom.takeBalance(landTax);

                    }

                    for (UUID ignored : kingdom.getValue().getMembersUUID()) {

                        if (oldBalance < taxOnResidents) {

                            cacheFeudalKingdom.takeReputation(CacheFeudalValues.getResidentsRemovingReputation());
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
        }.runTaskTimer(Feudal.getPlugin(), CacheFeudalValues.getTimeTaxCollection() * 72000L, CacheFeudalValues.getTimeTaxCollection() * 72000L);

    }

    public void restart() {

        new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Рестарт!"));

                LoadAndSaveDataUtils.saveAllKingdoms();
                LoadAndSaveDataUtils.saveAllConfigs();

                CacheFeudalPlayers.getFeudalPlayerInfo().clear();
                CacheFeudalKingdoms.getKingdomInfo().clear();
                CacheSpyPlayers.getSpyPlayerCache().clear();

            }
        }.runTaskTimer(Feudal.getPlugin(), CacheFeudalValues.getTimeRestart() * 72000L, CacheFeudalValues.getTimeRestart() * 72000L);

    }

    public void clearMail() {

        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getOnlinePlayers().forEach(player -> CacheFeudalPlayers.getFeudalPlayer(player).clearLetters());

            }

        }.runTaskTimer(Feudal.getPlugin(), CacheFeudalValues.getTimeClearMail() * 1728000L, CacheFeudalValues.getTimeClearMail() * 1728000L);

    }

    public void spawnTravelingMerchant() {

        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        new BukkitRunnable() {

            @Override
            public void run() {

                TravelingMerchant.spawnTraveling();

            }

        }.runTaskTimer(Feudal.getPlugin(), 108000, 108000);

    }

    public void secretOrder() {

        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        List<Player> onlinePlayers = (List<Player>) Bukkit.getOnlinePlayers();

        new BukkitRunnable() {

            @Override
            public void run() {

                Player player = onlinePlayers.get(ThreadLocalRandom.current().nextInt(0, onlinePlayers.size()));
                FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

                feudalPlayer.addLetter("Таинсвенный незнакомец", "Здаствуй " + player.getName() + ", ты, наверное, не помнишь меня... Но я отлично тебя помню. Могу пожелать тебе удачи, за твою голову назначена большая награда!");

            }

        }.runTaskTimer(Feudal.getPlugin(), 20, 20);

    }
}
