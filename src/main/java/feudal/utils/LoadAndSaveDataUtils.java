package feudal.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import feudal.Feudal;
import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.PlayerDBHandler;
import feudal.visual.ScoreBoardGeneralInfo;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.*;

import static feudal.data.database.KingdomDBHandler.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadAndSaveDataUtils {

    public static void loadKingdom(Player player) {

        if (!playerInKingdom(player)) return;

        String kingdomName = getPlayerKingdom(player);
        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        List<String> members = getList(kingdomName, "members");
        List<String> barons = getList(kingdomName, "barons");
        List<Integer> territory = getList(kingdomName, "territory");
        List<Integer> privateTerritory = getList(kingdomName, "privateTerritory");

        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player)
                .setMembers(members)
                .setBarons(barons)
                .setReputation(getIntegerField(kingdomName, "reputation"))
                .setBalance(getIntegerField(kingdomName, "balance"))
                .setMaxNumberMembers(getIntegerField(kingdomName, "maxNumberMembers"))
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

                setField(kingdomName, "king", feudalKingdom.getKingUUID());
                setField(kingdomName, "members", feudalKingdom.getMembersUUID());
                setField(kingdomName, "maxNumberMembers", feudalKingdom.getMaxNumberMembers());
                setField(kingdomName, "barons", feudalKingdom.getBaronsUUID());
                setField(kingdomName, "territory", feudalKingdom.getTerritory());
                setField(kingdomName, "privateTerritory", feudalKingdom.getPrivateTerritory());
                setField(kingdomName, "balance", feudalKingdom.getBalance());
                setField(kingdomName, "reputation", feudalKingdom.getReputation());

                CacheFeudalKingdoms.getKingdomInfo().remove(kingdomName);

            }

        }).start();

    }

    public static void saveKingdom(Player player) {

        new Thread(() -> {

            String kingdomName = getPlayerKingdom(player);

            if (kingdomName.equals("")) return;

            FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

            feudalKingdom.clearInvitation();

            setField(kingdomName, "king", feudalKingdom.getKingUUID());
            setField(kingdomName, "members", feudalKingdom.getMembersUUID());
            setField(kingdomName, "barons", feudalKingdom.getBaronsUUID());
            setField(kingdomName, "territory", feudalKingdom.getTerritory());
            setField(kingdomName, "privateTerritory", feudalKingdom.getPrivateTerritory());
            setField(kingdomName, "reputation", feudalKingdom.getReputation());
            setField(kingdomName, "balance", feudalKingdom.getBalance());
            setField(kingdomName, "maxNumberMembers", feudalKingdom.getMaxNumberMembers());

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

        if (!PlayerDBHandler.checkPlayer(player)) {

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

        loadPlayerMail(player);

    }

    public static void loadPlayerAttributes(@NotNull Player player, int speedLvl, int survivabilityLvl) {

        player.setMaxHealth(16 * (survivabilityLvl / 100.0F) + 16);
        player.setWalkSpeed(0.2f * speedLvl / 100 + 0.2f);
        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);


        if (!getPlayerKingdom(player).equalsIgnoreCase(""))
            player.setDisplayName(player.getDisplayName() + " [" + getPlayerKingdom(player) + "]");

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

            savePlayerMail(player);

            CacheFeudalPlayers.getFeudalPlayerInfo().remove(player);

        }).start();

    }

    public static void loadAllConfigs() {

        ConfigUtils.readDatabaseConfig();
        ConfigUtils.readEnchantmentsConfig();
        ConfigUtils.readKingdomTaxConfig();
        ConfigUtils.readMoneyForMobsConfig();
        ConfigUtils.readAttributesConfig();

    }

    public static void saveAllConfigs() {

        ConfigUtils.saveDatabaseConfig();
        ConfigUtils.saveEnchantmentsConfig();
        ConfigUtils.saveKingdomTaxConfig();
        ConfigUtils.saveMoneyForMobsConfig();
        ConfigUtils.saveAttributesConfig();

    }

    @SneakyThrows
    public static void loadPlayerMail(Player player) {

        File file = new File(Feudal.getPlugin().getDataFolder(), "playerMail.json");

        if (!file.exists())
            file.createNewFile();

        Type mapType = new TypeToken<Map<Integer, List<String>>>() {
        }.getType();
        Map<Integer, List<String>> playerLetters = new Gson().fromJson(new FileReader(file), mapType);

        if (playerLetters == null || playerLetters.get(player.getUniqueId().hashCode()) == null) {

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

            Map<Integer, List<String>> mail = new HashMap<>();

            List<String> letters = new ArrayList<>();
            letters.add("Добро пожаловать на режим Feudal!");

            mail.put(player.getUniqueId().hashCode(), letters);
            CacheFeudalPlayers.getFeudalPlayer(player).setLetters(letters);

            fileWriter.write(new Gson().toJson(mail));
            fileWriter.flush();

            return;

        }

        CacheFeudalPlayers.getFeudalPlayer(player).setLetters(playerLetters.get(player.getUniqueId().hashCode()));

    }

    @SneakyThrows
    public static void savePlayerMail(Player player) {

        File file = new File(Feudal.getPlugin().getDataFolder(), "playerMail.json");

        if (!file.exists())
            file.createNewFile();

        Type mapType = new TypeToken<Map<Integer, List<String>>>() {
        }.getType();
        Map<Integer, List<String>> playerLetters = new Gson().fromJson(new FileReader(file), mapType);

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (playerLetters.containsKey(player.getUniqueId().hashCode()))
            playerLetters.replace(player.getUniqueId().hashCode(), feudalPlayer.getLetters());
        else playerLetters.put(player.getUniqueId().hashCode(), feudalPlayer.getLetters());


        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

        fileWriter.write(new Gson().toJson(playerLetters));
        fileWriter.flush();

    }

    @SneakyThrows
    public static void saveAllPlayerMail() {

        File file = new File(Feudal.getPlugin().getDataFolder(), "playerMail.json");

        if (!file.exists())
            file.createNewFile();

        Type mapType = new TypeToken<Map<Integer, List<String>>>() {
        }.getType();
        Map<Integer, List<String>> playerLetters = new Gson().fromJson(new FileReader(file), mapType);

        new Thread(() -> Bukkit.getOnlinePlayers().forEach(player -> {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

            if (playerLetters.containsKey(player.getUniqueId().hashCode()))
                playerLetters.replace(player.getUniqueId().hashCode(), feudalPlayer.getLetters());
            else playerLetters.put(player.getUniqueId().hashCode(), feudalPlayer.getLetters());

        })).start();

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

        fileWriter.write(new Gson().toJson(playerLetters));
        fileWriter.flush();

    }

}
