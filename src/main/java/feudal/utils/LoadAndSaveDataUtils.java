package feudal.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import feudal.Feudal;
import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.data.database.PlayerDBHandler;
import feudal.generalListeners.PlayerListener;
import feudal.utils.wrappers.PlacedBlockWrapper;
import feudal.visual.scoreboards.ScoreBoardInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadAndSaveDataUtils {

    public static void loadKingdom(Player player) {

        if (!KingdomDBHandler.playerInKingdom(player)) return;

        String kingdomName = KingdomDBHandler.getPlayerKingdom(player);
        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        List<String> members = (List<String>) KingdomDBHandler.getField(kingdomName, "members");
        List<String> barons = (List<String>) KingdomDBHandler.getField(kingdomName, "barons");
        List<Integer> territory = (List<Integer>) KingdomDBHandler.getField(kingdomName, "territory");
        List<Integer> privateTerritory = (List<Integer>) KingdomDBHandler.getField(kingdomName, "privateTerritory");

        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player)
                .setMembers(members)
                .setBarons(barons)
                .setReputation(KingdomDBHandler.getIntegerField(kingdomName, "reputation"))
                .setBalance(KingdomDBHandler.getIntegerField(kingdomName, "balance"))
                .setMaxNumberMembers(KingdomDBHandler.getIntegerField(kingdomName, "maxNumberMembers"))
                .setTerritory(Objects.requireNonNull(territory))
                .setPrivateTerritory(Objects.requireNonNull(privateTerritory));

        CacheFeudalKingdoms.getKingdomInfo().put(kingdomName, feudalKingdom);

    }

    public static void saveAllKingdoms() {

        new Thread(() -> {

            for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

                FeudalKingdom feudalKingdom = kingdom.getValue();
                String kingdomName = feudalKingdom.getKingdomName();

                feudalKingdom.clearInvitation();

                KingdomDBHandler.setField(kingdomName, "king", feudalKingdom.getKingUUID());
                KingdomDBHandler.setField(kingdomName, "members", feudalKingdom.getMembersUUID());
                KingdomDBHandler.setField(kingdomName, "maxNumberMembers", feudalKingdom.getMaxNumberMembers());
                KingdomDBHandler.setField(kingdomName, "barons", feudalKingdom.getBaronsUUID());
                KingdomDBHandler.setField(kingdomName, "territory", feudalKingdom.getTerritory());
                KingdomDBHandler.setField(kingdomName, "privateTerritory", feudalKingdom.getPrivateTerritory());
                KingdomDBHandler.setField(kingdomName, "balance", feudalKingdom.getBalance());
                KingdomDBHandler.setField(kingdomName, "reputation", feudalKingdom.getReputation());

                CacheFeudalKingdoms.getKingdomInfo().remove(kingdomName);

            }

        }).start();

    }

    public static void saveKingdom(Player player) {

        new Thread(() -> {

            String kingdomName = KingdomDBHandler.getPlayerKingdom(player);

            if (kingdomName.equals("")) return;

            FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

            feudalKingdom.clearInvitation();

            KingdomDBHandler.setField(kingdomName, "king", feudalKingdom.getKingUUID());
            KingdomDBHandler.setField(kingdomName, "members", feudalKingdom.getMembersUUID());
            KingdomDBHandler.setField(kingdomName, "barons", feudalKingdom.getBaronsUUID());
            KingdomDBHandler.setField(kingdomName, "territory", feudalKingdom.getTerritory());
            KingdomDBHandler.setField(kingdomName, "privateTerritory", feudalKingdom.getPrivateTerritory());
            KingdomDBHandler.setField(kingdomName, "reputation", feudalKingdom.getReputation());
            KingdomDBHandler.setField(kingdomName, "balance", feudalKingdom.getBalance());
            KingdomDBHandler.setField(kingdomName, "maxNumberMembers", feudalKingdom.getMaxNumberMembers());

        }).start();

    }

    public static void saveAllPlayers() {

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

            feudalPlayer.clearInvitations();

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

        int speedLvl = PlayerDBHandler.getIntegerField(player, "speedLvl"), survivabilityLvl = PlayerDBHandler.getIntegerField(player, "survivabilityLvl");

        feudalPlayer = new FeudalPlayer(player);
        feudalPlayer.setaClassID(PlayerDBHandler.getIntegerField(player, "classID"))
                .setExperience(PlayerDBHandler.getIntegerField(player, "experience"))
                .setGameClassLvl(PlayerDBHandler.getIntegerField(player, "gameClassLvl"))
                .setGameClassExperience(PlayerDBHandler.getIntegerField(player, "gameClassExperience"))
                .setBalance(PlayerDBHandler.getIntegerField(player, "balance"))
                .setDeaths(PlayerDBHandler.getIntegerField(player, "deaths"))
                .setKills(PlayerDBHandler.getIntegerField(player, "kills"))
                .setLuckLvl(PlayerDBHandler.getIntegerField(player, "luckLvl"))
                .setSpeedLvl(speedLvl)
                .setStaminaLvl(PlayerDBHandler.getIntegerField(player, "staminaLvl"))
                .setStrengthLvl(PlayerDBHandler.getIntegerField(player, "strengthLvl"))
                .setKingdomName(PlayerDBHandler.getStringField(player, "kingdomName"))
                .setSurvivabilityLvl(survivabilityLvl);

        CacheFeudalPlayers.getFeudalPlayerInfo().put(player.getUniqueId().toString(), feudalPlayer);

        loadPlayerAttributes(player, speedLvl, survivabilityLvl);

//        loadPlayerMail(player);

    }

    public static void loadPlayerAttributes(@NotNull Player player, int speedLvl, int survivabilityLvl) {

        player.setMaxHealth(16 * (survivabilityLvl / 100.0F) + 16);
        player.setWalkSpeed(0.2f * (speedLvl / 100) + 0.2f);
        ScoreBoardInfo.updateScoreBoardInfo(player);


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

//            savePlayerMail(player);

            CacheFeudalPlayers.getFeudalPlayerInfo().remove(player);

        }).start();

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

    public static void loadPlacedBlocks() {

        File file = new File(Feudal.getPlugin().getDataFolder(), "placedBlocks.json");

        if (!file.exists()) {

            try {

                file.createNewFile();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }

            return;

        }

        try {

            Type listType = new TypeToken<List<PlacedBlockWrapper>>() {
            }.getType();
            List<PlacedBlockWrapper> placedBlockWrapperList = new Gson().fromJson(new FileReader(file), listType);

            placedBlockWrapperList.forEach(placedBlockWrapper -> PlacedBlockWrapper.placedBlockWrapperToBlock(placedBlockWrapper).setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true")));

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        }

    }

    public static void savePlacedBlocks() {

        File file = new File(Feudal.getPlugin().getDataFolder(), "placedBlocks.json");

        if (!file.exists())
            try {

                file.createNewFile();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }


        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

            fileWriter.write(new Gson().toJson(PlayerListener.placedBlocks));

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    public static void savePlayerMail(Player player) {

        File file = new File(Feudal.getPlugin().getDataFolder(), "playerMail.json");

        if (!file.exists())
            try {

                file.createNewFile();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }


        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

            Type mapType = new TypeToken<Map<Player, List<String>>>() {
            }.getType();

            Map<String, List<String>> playerLetters = new Gson().fromJson(new FileReader(file), mapType);


            for (Map.Entry<String, FeudalPlayer> feudalPlayer : CacheFeudalPlayers.getFeudalPlayerInfo().entrySet()) {

                if (playerLetters.get(player.getUniqueId().toString()) == null) return;

                if (!playerLetters.containsKey(player.getUniqueId().toString()))
                    playerLetters.put(feudalPlayer.getKey(), feudalPlayer.getValue().getPlayerLetters());

                else
                    playerLetters.replace(feudalPlayer.getKey(), feudalPlayer.getValue().getPlayerLetters());

            }


            fileWriter.write(new Gson().toJson(playerLetters));

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    public static void loadPlayerMail(Player player) {

        File file = new File(Feudal.getPlugin().getDataFolder(), "playerMail.json");

        if (!file.exists()) {

            try {

                file.createNewFile();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }

            return;

        }

        try {

            Type mapType = new TypeToken<Map<Player, List<String>>>() {
            }.getType();
            Map<Player, List<String>> playerLetters = new Gson().fromJson(new FileReader(file), mapType);


            for (Map.Entry<Player, List<String>> letters : playerLetters.entrySet()) {

                if (letters.getKey() != player) return;

                CacheFeudalPlayers.getFeudalPlayer(letters.getKey()).setLetters(letters.getValue());

            }


        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        }

    }
}
