package feudal.data;

import feudal.visual.ScoreBoardGeneralInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalPlayer {

    final List<String> invitations = new ArrayList<>();
    UUID playerUUID;
    String kingdomName;
    List<String> letters = new ArrayList<>();
    int professionID, experience, balance, deaths, kills, strengthLvl, survivabilityLvl, speedLvl, staminaLvl, luckLvl, professionLvl, professionExperience, upProfession;

    public FeudalPlayer(@NotNull Player player) {
        playerUUID = player.getUniqueId();
    }

    public final FeudalPlayer setPlayer(@NotNull Player player) {
        playerUUID = player.getUniqueId();
        return this;
    }

    public final FeudalPlayer setProfessionID(int value) {
        professionID = value;
        return this;
    }

    public final FeudalPlayer setExperience(int value) {
        experience = value;
        return this;
    }

    public final FeudalPlayer setBalance(int value) {
        balance = value;
        return this;
    }

    public final FeudalPlayer setDeaths(int value) {
        deaths = value;
        return this;
    }

    public final FeudalPlayer setKills(int value) {
        kills = value;
        return this;
    }

    public final FeudalPlayer setStrengthLvl(int value) {
        strengthLvl = value;
        return this;
    }

    public final FeudalPlayer setSurvivabilityLvl(int value) {
        survivabilityLvl = value;
        return this;
    }

    public final FeudalPlayer setSpeedLvl(int value) {
        speedLvl = value;
        return this;
    }

    public final FeudalPlayer setStaminaLvl(int value) {
        staminaLvl = value;
        return this;
    }

    public final FeudalPlayer setLuckLvl(int value) {
        luckLvl = value;
        return this;
    }

    public final FeudalPlayer setProfessionLvl(int value) {
        professionLvl = value;
        return this;
    }

    public final FeudalPlayer setProfessionExperience(int value) {
        professionExperience = value;
        return this;
    }

    public final FeudalPlayer setKingdomName(String name) {
        kingdomName = name;
        return this;
    }


    public final FeudalPlayer addExperience(int value) {

        experience += value;

        Player player = Bukkit.getPlayer(playerUUID);

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " ?????????? ??????????????????");

        return this;

    }

    public final FeudalPlayer addBalance(int value) {

        balance += value;

        Player player = Bukkit.getPlayer(playerUUID);

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " ????????????");

        return this;

    }

    public final FeudalPlayer addDeaths(int value) {
        deaths += value;
        return this;
    }

    public final FeudalPlayer addKills(int value) {
        kills += value;
        return this;
    }

    public final FeudalPlayer addStrengthLvl(int value) {
        strengthLvl += value;
        return this;
    }

    public final FeudalPlayer addSurvivabilityLvl(int value) {
        survivabilityLvl += value;
        return this;
    }

    public final FeudalPlayer addSpeedLvl(int value) {
        speedLvl += value;
        return this;
    }

    public final FeudalPlayer addStaminaLvl(int value) {
        staminaLvl += value;
        return this;
    }

    public final FeudalPlayer addLuckLvl(int value) {
        luckLvl += value;
        return this;
    }

    public final FeudalPlayer addProfessionLvl(int value) {

        professionLvl += value;

        Player player = Bukkit.getPlayer(playerUUID);
        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);

        return this;

    }

    public final FeudalPlayer addProfessionExperience(int value) {

        professionExperience += value;

        Player player = Bukkit.getPlayer(playerUUID);

        if (professionLvl <= 100 && professionExperience >= (int) (Math.pow(1 + 0.05, professionLvl) * 100)) {

            addProfessionLvl(1);
            takeProfessionExperience((int) (Math.pow(1 + 0.05, professionLvl) * 100));

            player.sendMessage("+1 ?????????????? ????????????");

        }

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " ?????????? ????????????");

        return this;

    }


    public final FeudalPlayer takeExperience(int value) {

        experience -= value;

        Player player = Bukkit.getPlayer(playerUUID);

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " ?????????? ??????????????????");

        return this;

    }

    public final FeudalPlayer takeBalance(int value) {

        balance -= value;

        Player player = Bukkit.getPlayer(playerUUID);

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " ????????????");

        return this;
    }

    public final FeudalPlayer takeProfessionExperience(int value) {

        professionExperience -= value;

        Player player = Bukkit.getPlayer(playerUUID);

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " ?????????? ????????????");

        return this;

    }

    public final FeudalPlayer addInvitations(String name) {
        invitations.add(name);
        return this;
    }

    public final FeudalPlayer clearInvitations() {
        invitations.clear();
        return this;
    }

    public final FeudalPlayer deleteInvitations(String name) {
        invitations.remove(name);
        return this;
    }

    public final FeudalPlayer setLetters(List<String> lettersList) {
        letters = lettersList;
        return this;
    }

    public final FeudalPlayer addLetter(@NotNull String sender, String letter) {

        letters.add(letter);

        String shortLetter = letter.length() < 10 ? letter : letter.substring(0, 10);

        CraftPlayer player = (CraftPlayer) Bukkit.getPlayer(playerUUID);
        player.getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "??l?????????? ???????????? ???? ?????????????????????? " + sender + " - ??n" + shortLetter + "..." + "\"}"), ChatMessageType.GAME_INFO));

        return this;

    }

    public final FeudalPlayer clearLetters() {
        letters.clear();
        return this;
    }

    public final FeudalPlayer addUpProfession(int value) {
        upProfession += value;
        return this;
    }

    public final FeudalPlayer setUpProfession(int value) {
        upProfession = value;
        return this;
    }
}
