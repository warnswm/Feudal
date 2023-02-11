package feudal.utils;

import feudal.Feudal;
import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.data.database.PlayerDBHandler;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.visual.scoreboards.ScoreBoardInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadAndSaveDataUtils {

    public static void loadKingdom(Player player) {

        if (!KingdomDBHandler.playerInKingdom(player)) return;

        String kingdomName = KingdomDBHandler.getPlayerKingdom(player);
        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player)
                .setMembers((List<Player>) KingdomDBHandler.getField(kingdomName, "members"))
                .setBarons((List<Player>) KingdomDBHandler.getField(kingdomName, "barons"))
                .setReputation(KingdomDBHandler.getIntegerField(kingdomName, "reputation"))
                .setBalance(KingdomDBHandler.getIntegerField(kingdomName, "balance"))
                .setTerritory((List<Chunk>) KingdomDBHandler.getField(kingdomName, "territory"))
                .setPrivateTerritory((List<Chunk>) KingdomDBHandler.getField(kingdomName, "privateTerritory"));

        CacheFeudalKingdoms.getKingdomInfo().put(kingdomName, feudalKingdom);

    }

    public static void saveAllKingdoms() {

        new Thread(() -> {

            for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

                FeudalKingdom cacheFeudalKingdom = kingdom.getValue();
                String kingdomName = cacheFeudalKingdom.getKingdomName();

                List<String> members = new ArrayList<>();
                cacheFeudalKingdom.getMembers().forEach(member -> members.add(member.getUniqueId().toString()));

                List<String> barons = new ArrayList<>();
                cacheFeudalKingdom.getBarons().forEach(baron -> barons.add(baron.getUniqueId().toString()));

                List<String> territory = new ArrayList<>();
                cacheFeudalKingdom.getTerritory().forEach(chunk -> territory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

                List<String> privateTerritory = new ArrayList<>();
                cacheFeudalKingdom.getPrivateTerritory().forEach(chunk -> privateTerritory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));


                KingdomDBHandler.setField(kingdomName, "king", cacheFeudalKingdom.getKing().getUniqueId().toString());
                KingdomDBHandler.setField(kingdomName, "members", members);
                KingdomDBHandler.setField(kingdomName, "barons", barons);
                KingdomDBHandler.setField(kingdomName, "territory", territory);
                KingdomDBHandler.setField(kingdomName, "privateTerritory", privateTerritory);
                KingdomDBHandler.setField(kingdomName, "balance", cacheFeudalKingdom.getBalance());
                KingdomDBHandler.setField(kingdomName, "reputation", cacheFeudalKingdom.getReputation());

                CacheFeudalKingdoms.getKingdomInfo().remove(kingdomName);

            }
        }).start();

        System.gc();

    }

    public static void saveKingdom(Player player) {

        new Thread(() -> {

            String kingdomName = KingdomDBHandler.getPlayerKingdom(player);

            if (kingdomName.equals("")) return;

            FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

            List<String> members = new ArrayList<>();
            feudalKingdom.getMembers().forEach(member -> members.add(member.getUniqueId().toString()));

            List<String> barons = new ArrayList<>();
            feudalKingdom.getBarons().forEach(baron -> barons.add(baron.getUniqueId().toString()));

            List<String> territory = new ArrayList<>();
            feudalKingdom.getTerritory().forEach(chunk -> territory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            List<String> privateTerritory = new ArrayList<>();
            feudalKingdom.getPrivateTerritory().forEach(chunk -> privateTerritory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            KingdomDBHandler.setField(kingdomName, "king", feudalKingdom.getKing());
            KingdomDBHandler.setField(kingdomName, "members", members);
            KingdomDBHandler.setField(kingdomName, "barons", barons);
            KingdomDBHandler.setField(kingdomName, "territory", territory);
            KingdomDBHandler.setField(kingdomName, "privateTerritory", privateTerritory);
            KingdomDBHandler.setField(kingdomName, "reputation", feudalKingdom.getReputation());
            KingdomDBHandler.setField(kingdomName, "balance", feudalKingdom.getBalance());

        }).start();

        System.gc();

    }

    public static void saveAllPlayers() {

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

            PlayerDBHandler.setField(player, "classID", feudalPlayer.getAClassID());
            PlayerDBHandler.setField(player, "experience", feudalPlayer.getExperience());
            PlayerDBHandler.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            PlayerDBHandler.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            PlayerDBHandler.setField(player, "balance", feudalPlayer.getBalance());
            PlayerDBHandler.setField(player, "deaths", feudalPlayer.getDeaths());
            PlayerDBHandler.setField(player, "kills", feudalPlayer.getKills());
            PlayerDBHandler.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            PlayerDBHandler.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            PlayerDBHandler.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            PlayerDBHandler.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            PlayerDBHandler.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            PlayerDBHandler.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CacheFeudalPlayers.getFeudalPlayerInfo().remove(player);

        }).start());

        System.gc();

    }

    public static void loadPlayer(@NotNull Player player) {

        FeudalPlayer feudalPlayer;

        player.sendMessage("Подождите, ваши данные загружаются!");

        if (!PlayerDBHandler.createNewPlayer(player)) {

            player.kickPlayer("Произошла проблема! Ваши данные не загружены или повреждены, попробуйте зайти позже.");
            return;

        }

        player.sendMessage("Ваши данные успешно загружены! Удачной игры");

        if (!PlayerDBHandler.hasPlayer(player)) {

            feudalPlayer = new FeudalPlayer(player);
            feudalPlayer.setaClassID(0).setExperience(0).setGameClassExperience(0)
                    .setBalance(1000).setDeaths(0).setKills(0)
                    .setLuckLvl(0).setSpeedLvl(0).setStaminaLvl(0)
                    .setStrengthLvl(0).setKingdomName("").setSurvivabilityLvl(0)
                    .setGameClassLvl(0);

            return;
        }

        feudalPlayer = new FeudalPlayer(player);
        feudalPlayer.setaClassID(PlayerDBHandler.getIntegerField(player, "classID"))
                .setExperience(PlayerDBHandler.getIntegerField(player, "experience"))
                .setGameClassLvl(PlayerDBHandler.getIntegerField(player, "gameClassLvl"))
                .setGameClassExperience(PlayerDBHandler.getIntegerField(player, "gameClassExperience"))
                .setBalance(PlayerDBHandler.getIntegerField(player, "balance"))
                .setDeaths(PlayerDBHandler.getIntegerField(player, "deaths"))
                .setKills(PlayerDBHandler.getIntegerField(player, "kills"))
                .setLuckLvl(PlayerDBHandler.getIntegerField(player, "luckLvl"))
                .setSpeedLvl(PlayerDBHandler.getIntegerField(player, "speedLvl"))
                .setStaminaLvl(PlayerDBHandler.getIntegerField(player, "staminaLvl"))
                .setStrengthLvl(PlayerDBHandler.getIntegerField(player, "strengthLvl"))
                .setKingdomName(PlayerDBHandler.getStringField(player, "kingdomName"))
                .setSurvivabilityLvl(PlayerDBHandler.getIntegerField(player, "survivabilityLvl"));

        CacheFeudalPlayers.getFeudalPlayerInfo().put(player, feudalPlayer);

    }

    public static void loadPlayerAttributes(@NotNull Player player) {

        FeudalPlayer feudalPlayer = new FeudalPlayer(player);

        float health = feudalPlayer.getSurvivabilityLvl(), speed = feudalPlayer.getSpeedLvl();

        player.setMaxHealth(20 * (health / 100.0F) + 20);
        player.setWalkSpeed(0.2f * (speed / 100) + 0.2f);
        ScoreBoardInfo.createScoreBoardInfo(player);


        if (!KingdomDBHandler.getPlayerKingdom(player).equalsIgnoreCase(""))
            player.setDisplayName(player.getDisplayName() + " [" + KingdomDBHandler.getPlayerKingdom(player) + "]");

    }

    public static void savePlayer(Player player) {

        new Thread(() -> {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

            PlayerDBHandler.setField(player, "classID", feudalPlayer.getAClassID());
            PlayerDBHandler.setField(player, "experience", feudalPlayer.getExperience());
            PlayerDBHandler.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            PlayerDBHandler.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            PlayerDBHandler.setField(player, "balance", feudalPlayer.getBalance());
            PlayerDBHandler.setField(player, "deaths", feudalPlayer.getDeaths());
            PlayerDBHandler.setField(player, "kills", feudalPlayer.getKills());
            PlayerDBHandler.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            PlayerDBHandler.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            PlayerDBHandler.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            PlayerDBHandler.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            PlayerDBHandler.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            PlayerDBHandler.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CacheFeudalPlayers.getFeudalPlayerInfo().remove(player);

        }).start();

        System.gc();

    }

    public static void loadAllConfigs() {

        Feudal.getPlugin().getConfig().options().copyDefaults(true);
        Feudal.getPlugin().saveDefaultConfig();

        ConfigUtils.readDatabaseConfig();
        ConfigUtils.readEnchantmentsConfig();

    }

    public static void saveAllConfigs() {

        ConfigUtils.saveDatabaseConfig();
        ConfigUtils.saveEnchantmentsConfig();

    }
}
