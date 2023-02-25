package feudal.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import feudal.Feudal;
import feudal.data.Auction;
import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.PlayerDBHandler;
import feudal.listeners.general.PlayerL;
import feudal.utils.wrappers.ItemStackWrapper;
import feudal.utils.wrappers.PlacedBlockWrapper;
import feudal.visual.ScoreBoardGeneralInfo;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.*;

import static feudal.data.database.KingdomDBHandler.*;

@UtilityClass
public class LoadAndSaveDataUtils {

    public void loadKingdom(Player player) {

        if (!playerInKingdom(player)) return;

        String kingdomName = getPlayerKingdom(player);

        if (CacheFeudalKingdoms.getKingdomInfo().get(kingdomName) != null) return;

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);
        feudalKingdom.setKingdomName(kingdomName)
                .setKing(UUID.fromString(getStringField(kingdomName, "king")))
                .setMembers(CollectionUtils.stringListToSUUIDList(getList(kingdomName, "members")))
                .setBarons(CollectionUtils.stringListToSUUIDList(getList(kingdomName, "barons")))
                .setReputation(getIntegerField(kingdomName, "reputation"))
                .setBalance(getIntegerField(kingdomName, "balance"))
                .setMaxNumberMembers(getIntegerField(kingdomName, "maxNumberMembers"))
                .setTerritory(getList(kingdomName, "territory"))
                .setFlagGson(getStringField(kingdomName, "flag"));

        CacheFeudalKingdoms.getKingdomInfo().put(kingdomName, feudalKingdom);

    }

    public void saveAllKingdoms() {

        new Thread(() -> {

            for (Map.Entry<String, FeudalKingdom> kingdom : CacheFeudalKingdoms.getKingdomInfo().entrySet()) {

                FeudalKingdom feudalKingdom = kingdom.getValue();
                String kingdomName = feudalKingdom.getKingdomName();

                setField(kingdomName, "members", CollectionUtils.uuidListToStringList(feudalKingdom.getMembersUUID()));
                setField(kingdomName, "maxNumberMembers", feudalKingdom.getMaxNumberMembers());
                setField(kingdomName, "barons", CollectionUtils.uuidListToStringList(feudalKingdom.getBaronsUUID()));
                setField(kingdomName, "territory", feudalKingdom.getTerritory());
                setField(kingdomName, "balance", feudalKingdom.getBalance());
                setField(kingdomName, "reputation", feudalKingdom.getReputation());
                setField(kingdomName, "flag", feudalKingdom.getFlagGson());

                CacheFeudalKingdoms.getKingdomInfo().remove(kingdomName);

            }

        }).start();

    }

    public void saveAllPlayers() {

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

            feudalPlayer.clearInvitations();

            PlayerDBHandler.setField(player, "professionID", feudalPlayer.getProfessionID());
            PlayerDBHandler.setField(player, "experience", feudalPlayer.getExperience());
            PlayerDBHandler.setField(player, "professionLvl", feudalPlayer.getProfessionLvl());
            PlayerDBHandler.setField(player, "professionExperience", feudalPlayer.getProfessionExperience());
            PlayerDBHandler.setField(player, "balance", feudalPlayer.getBalance());
            PlayerDBHandler.setField(player, "deaths", feudalPlayer.getDeaths());
            PlayerDBHandler.setField(player, "kills", feudalPlayer.getKills());
            PlayerDBHandler.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            PlayerDBHandler.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            PlayerDBHandler.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            PlayerDBHandler.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            PlayerDBHandler.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            PlayerDBHandler.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CacheFeudalPlayers.getFeudalPlayerInfo().remove(player.getUniqueId());

        }).start());

    }

    public void loadPlayer(@NotNull Player player) {

        FeudalPlayer feudalPlayer;

        if (!PlayerDBHandler.checkPlayer(player)) {

            player.kickPlayer("Произошла проблема! Ваши данные не загружены или повреждены, попробуйте зайти позже.");
            return;

        }

        if (!PlayerDBHandler.hasPlayer(player)) {

            feudalPlayer = new FeudalPlayer(player);
            feudalPlayer.setProfessionID(0).setExperience(0).setProfessionExperience(0)
                    .setBalance(1000).setDeaths(0).setKills(0)
                    .setLuckLvl(0).setSpeedLvl(0).setStaminaLvl(0)
                    .setStrengthLvl(0).setKingdomName("").setSurvivabilityLvl(0)
                    .setProfessionLvl(0).addUpProfession(0);

            return;

        }

        int speedLvl = PlayerDBHandler.getIntegerField(player, "speedLvl"), survivabilityLvl = PlayerDBHandler.getIntegerField(player, "survivabilityLvl");

        feudalPlayer = new FeudalPlayer(player);
        feudalPlayer.setProfessionID(PlayerDBHandler.getIntegerField(player, "professionID"))
                .setExperience(PlayerDBHandler.getIntegerField(player, "experience"))
                .setProfessionLvl(PlayerDBHandler.getIntegerField(player, "professionLvl"))
                .setProfessionExperience(PlayerDBHandler.getIntegerField(player, "professionExperience"))
                .setBalance(PlayerDBHandler.getIntegerField(player, "balance"))
                .setDeaths(PlayerDBHandler.getIntegerField(player, "deaths"))
                .setKills(PlayerDBHandler.getIntegerField(player, "kills"))
                .setLuckLvl(PlayerDBHandler.getIntegerField(player, "luckLvl"))
                .setSpeedLvl(speedLvl)
                .setStaminaLvl(PlayerDBHandler.getIntegerField(player, "staminaLvl"))
                .setStrengthLvl(PlayerDBHandler.getIntegerField(player, "strengthLvl"))
                .setKingdomName(PlayerDBHandler.getStringField(player, "kingdomName"))
                .setSurvivabilityLvl(survivabilityLvl)
                .setUpProfession(PlayerDBHandler.getIntegerField(player, "upProfession"));

        CacheFeudalPlayers.getFeudalPlayerInfo().put(player.getUniqueId(), feudalPlayer);

        loadPlayerAttributes(player, speedLvl, survivabilityLvl);
        loadPlayerMail(player);

    }

    private void loadPlayerAttributes(@NotNull Player player, int speedLvl, int survivabilityLvl) {

        player.setMaxHealth(16 * (survivabilityLvl / 100.0F) + 16);
        player.setWalkSpeed(0.2f * speedLvl / 100 + 0.2f);
        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);


        if (!getPlayerKingdom(player).equals(""))
            player.setDisplayName(player.getDisplayName() + " [" + getPlayerKingdom(player) + "]");

    }

    public void savePlayer(Player player) {

        new Thread(() -> {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

            PlayerDBHandler.setField(player, "professionID", feudalPlayer.getProfessionID());
            PlayerDBHandler.setField(player, "experience", feudalPlayer.getExperience());
            PlayerDBHandler.setField(player, "professionLvl", feudalPlayer.getProfessionLvl());
            PlayerDBHandler.setField(player, "professionExperience", feudalPlayer.getProfessionExperience());
            PlayerDBHandler.setField(player, "balance", feudalPlayer.getBalance());
            PlayerDBHandler.setField(player, "deaths", feudalPlayer.getDeaths());
            PlayerDBHandler.setField(player, "kills", feudalPlayer.getKills());
            PlayerDBHandler.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            PlayerDBHandler.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            PlayerDBHandler.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            PlayerDBHandler.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            PlayerDBHandler.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            PlayerDBHandler.setField(player, "kingdomName", feudalPlayer.getKingdomName());
            PlayerDBHandler.setField(player, "upProfession", feudalPlayer.getUpProfession());

            savePlayerMail(player);

            CacheFeudalPlayers.getFeudalPlayerInfo().remove(player.getUniqueId());

        }).start();

    }

    public void loadAllConfigs() {

        ConfigUtils.readDatabaseConfig();
        ConfigUtils.readEnchantmentsConfig();
        ConfigUtils.readKingdomTaxConfig();
        ConfigUtils.readMoneyForMobsConfig();
        ConfigUtils.readAttributesConfig();
        ConfigUtils.readGeneralConfig();

    }

    public void saveAllConfigs() {

        ConfigUtils.saveDatabaseConfig();
        ConfigUtils.saveEnchantmentsConfig();
        ConfigUtils.saveKingdomTaxConfig();
        ConfigUtils.saveMoneyForMobsConfig();
        ConfigUtils.saveAttributesConfig();
        ConfigUtils.saveGeneralConfig();

    }

    @SneakyThrows
    private void loadPlayerMail(Player player) {

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
    public void savePlayerMail(Player player) {

        File file = new File(Feudal.getPlugin().getDataFolder(), "playerMail.json");

        if (!file.exists())
            file.createNewFile();

        Type mapType = new TypeToken<Map<Integer, List<String>>>() {
        }.getType();
        Map<Integer, List<String>> playerLetters = new Gson().fromJson(new FileReader(file), mapType);

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (playerLetters.containsKey(player.getUniqueId().hashCode()))
            playerLetters.replace(player.getUniqueId().hashCode(), feudalPlayer.getLetters());
        else
            playerLetters.put(player.getUniqueId().hashCode(), feudalPlayer.getLetters());


        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

        fileWriter.write(new Gson().toJson(playerLetters));
        fileWriter.flush();

    }

    @SneakyThrows
    public void saveAllPlayersMail() {

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

    @SneakyThrows
    public void loadPlacedBlocks() {

        File file = new File(Feudal.getPlugin().getDataFolder(), "placedBlocks.json");

        if (!file.exists()) {

            file.createNewFile();
            return;

        }

        Type listType = new TypeToken<List<PlacedBlockWrapper>>() {
        }.getType();
        List<PlacedBlockWrapper> placedBlockWrapperList = new Gson().fromJson(new FileReader(file), listType);

        if (placedBlockWrapperList == null) return;

        placedBlockWrapperList.forEach(placedBlockWrapper -> Bukkit.getWorld(placedBlockWrapper.getWorldName()).getBlockAt(placedBlockWrapper.getX(), placedBlockWrapper.getY(), placedBlockWrapper.getZ()).
                setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true")));

    }

    @SneakyThrows
    public void savePlacedBlocks() {

        if (PlayerL.getPlacedBlocks().isEmpty()) return;

        File file = new File(Feudal.getPlugin().getDataFolder(), "placedBlocks.json");

        if (!file.exists())
            file.createNewFile();

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

        fileWriter.write(new Gson().toJson(PlayerL.getPlacedBlocks()));
        fileWriter.flush();

    }

    @SneakyThrows
    public void loadAuction() {

        File file = new File(Feudal.getPlugin().getDataFolder(), "auctionProducts.json");

        if (!file.exists()) {

            file.createNewFile();
            return;

        }

        Type listType = new TypeToken<List<ItemStackWrapper>>() {
        }.getType();

        if (new Gson().fromJson(new FileReader(file), listType) == null) return;

        List<ItemStackWrapper> list = new Gson().fromJson(new FileReader(file), listType);
        list.forEach(product -> Auction.getProducts().add(product));

    }

    @SneakyThrows
    public void saveAuction() {

        if (Auction.getProducts().isEmpty()) return;

        File file = new File(Feudal.getPlugin().getDataFolder(), "auctionProducts.json");

        if (!file.exists())
            file.createNewFile();

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

        fileWriter.write(new Gson().toJson(Auction.getProducts()));
        fileWriter.flush();

    }

}
