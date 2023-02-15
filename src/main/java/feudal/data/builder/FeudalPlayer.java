package feudal.data.builder;

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
    String playerUUID, kingdomName;
    List<String> letters = new ArrayList<>();
    int aClassID, experience, balance, deaths, kills, strengthLvl, survivabilityLvl, speedLvl, staminaLvl, luckLvl, gameClassLvl, gameClassExperience;

    public FeudalPlayer(@NotNull Player player) {
        playerUUID = player.getUniqueId().toString();
    }

    public final FeudalPlayer setPlayer(@NotNull Player player) {
        playerUUID = player.getUniqueId().toString();
        return this;
    }

    public final FeudalPlayer setaClassID(int value) {
        aClassID = value;
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

    public final FeudalPlayer setGameClassLvl(int gameClassLvl) {
        this.gameClassLvl = gameClassLvl;
        return this;
    }

    public final FeudalPlayer setGameClassExperience(int value) {
        gameClassExperience = value;
        return this;
    }

    public final FeudalPlayer setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }


    public final FeudalPlayer addExperience(int value) {

        experience += value;

        Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " опыта атрибутов");

        return this;

    }

    public final FeudalPlayer addBalance(int value) {

        balance += value;

        Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " денег");

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

    public final FeudalPlayer addGameClassLvl(int value) {
        gameClassLvl += value;
        return this;
    }

    public final FeudalPlayer addGameClassExperience(int value) {

        gameClassExperience += value;

        Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        if (gameClassLvl <= 100 && gameClassExperience >= (int) (Math.pow(1 + 0.05, gameClassLvl) * 100)) {

            addGameClassLvl(1);
            takeGameClassExperience((int) (Math.pow(1 + 0.05, gameClassLvl) * 100));

            player.sendMessage("+1 уровень класса");

        }

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " опыта класса");

        return this;

    }


    public final FeudalPlayer takeExperience(int value) {

        experience -= value;

        Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " опыта атрибутов");

        return this;

    }

    public final FeudalPlayer takeBalance(int value) {

        balance -= value;

        Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " денег");

        return this;
    }

    public final FeudalPlayer takeDeaths(int value) {
        deaths -= value;
        return this;
    }

    public final FeudalPlayer takeKills(int value) {
        kills -= value;
        return this;
    }

    public final FeudalPlayer takeStrengthLvl(int value) {
        strengthLvl -= value;
        return this;
    }

    public final FeudalPlayer takeSurvivabilityLvl(int value) {
        survivabilityLvl -= value;
        return this;
    }

    public final FeudalPlayer takeSpeedLvl(int value) {
        speedLvl -= value;
        return this;
    }

    public final FeudalPlayer takeStaminaLvl(int value) {
        staminaLvl -= value;
        return this;
    }

    public final FeudalPlayer takeLuckLvl(int value) {
        luckLvl -= value;
        return this;
    }

    public final FeudalPlayer takeGameClassLvl(int value) {
        gameClassLvl -= value;
        return this;
    }

    public final FeudalPlayer takeGameClassExperience(int value) {

        gameClassExperience -= value;

        Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " опыта класса");

        return this;

    }

    public final FeudalPlayer addInvitations(String kingdomName) {
        invitations.add(kingdomName);
        return this;
    }

    public final FeudalPlayer clearInvitations() {
        invitations.clear();
        return this;
    }

    public final FeudalPlayer deleteInvitations(String kingdomName) {
        invitations.remove(kingdomName);
        return this;
    }

    public final FeudalPlayer setLetters(List<String> letters) {
        this.letters = letters;
        return this;
    }

    public final FeudalPlayer addLetter(@NotNull Player sender, String letter) {

        letters.add(letter);

        String shortLetter = letter.length() < 10 ? letter : letter.substring(0, 10);

        CraftPlayer player = (CraftPlayer) Bukkit.getPlayer(UUID.fromString(playerUUID));
        player.getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "§lНовое письмо от игрока " + sender.getName() + " - §n" + shortLetter + "..." + "\"}"), ChatMessageType.GAME_INFO));

        return this;

    }

    public final FeudalPlayer clearLetters() {
        letters.clear();
        return this;
    }

    public final FeudalPlayer deleteLetter(String letter) {
        letters.remove(kingdomName);
        return this;
    }

}
