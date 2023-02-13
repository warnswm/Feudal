package feudal.data.builder;

import feudal.visual.scoreboards.ScoreBoardInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalPlayer {
    Player player;
    int aClassID;
    int experience;
    int balance;
    int deaths;
    int kills;
    int strengthLvl;
    int survivabilityLvl;
    int speedLvl;
    int staminaLvl;
    int luckLvl;
    int gameClassLvl;
    int gameClassExperience;
    String kingdomName;
    final List<String> invitations = new ArrayList<>();
    List<String> playerLetters = new ArrayList<>();

    public FeudalPlayer(Player player) {
        this.player = player;
    }


    public FeudalPlayer setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public FeudalPlayer setaClassID(int value) {
        aClassID = value;
        return this;
    }

    public FeudalPlayer setExperience(int value) {
        experience = value;
        return this;
    }

    public FeudalPlayer setBalance(int value) {
        balance = value;
        return this;
    }

    public FeudalPlayer setDeaths(int value) {
        deaths = value;
        return this;
    }

    public FeudalPlayer setKills(int value) {
        kills = value;
        return this;
    }

    public FeudalPlayer setStrengthLvl(int value) {
        strengthLvl = strengthLvl;
        return this;
    }

    public FeudalPlayer setSurvivabilityLvl(int value) {
        survivabilityLvl = value;
        return this;
    }

    public FeudalPlayer setSpeedLvl(int value) {
        speedLvl = value;
        return this;
    }

    public FeudalPlayer setStaminaLvl(int value) {
        staminaLvl = value;
        return this;
    }

    public FeudalPlayer setLuckLvl(int value) {
        luckLvl = value;
        return this;
    }

    public FeudalPlayer setGameClassLvl(int gameClassLvl) {
        this.gameClassLvl = gameClassLvl;
        return this;
    }

    public FeudalPlayer setGameClassExperience(int value) {
        gameClassExperience = value;
        return this;
    }

    public FeudalPlayer setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }


    public FeudalPlayer addExperience(int value) {

        experience += value;

        ScoreBoardInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " опыта атрибутов");

        return this;
    }

    public FeudalPlayer addBalance(int value) {

        balance += value;

        ScoreBoardInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " денег");

        return this;
    }

    public FeudalPlayer addDeaths(int value) {
        deaths += value;
        return this;
    }

    public FeudalPlayer addKills(int value) {
        kills += value;
        return this;
    }

    public FeudalPlayer addStrengthLvl(int value) {
        strengthLvl += value;
        return this;
    }

    public FeudalPlayer addSurvivabilityLvl(int value) {
        survivabilityLvl += value;
        return this;
    }

    public FeudalPlayer addSpeedLvl(int value) {
        speedLvl += value;
        return this;
    }

    public FeudalPlayer addStaminaLvl(int value) {
        staminaLvl += value;
        return this;
    }

    public FeudalPlayer addLuckLvl(int value) {
        luckLvl += value;
        return this;
    }

    public FeudalPlayer addGameClassLvl(int value) {
        gameClassLvl += value;
        return this;
    }

    public FeudalPlayer addGameClassExperience(int value) {

        gameClassExperience += value;

        if (gameClassLvl <= 100 && gameClassExperience >= (int) (Math.pow(1 + 0.05, gameClassLvl) * 100)) {

            addGameClassLvl(1);
            takeGameClassExperience((int) (Math.pow(1 + 0.05, gameClassLvl) * 100));

            player.sendMessage("+1 уровень класса");

        }

        ScoreBoardInfo.updateScoreBoardInfo(player);
        player.sendMessage("+" + value + " опыта класса");

        return this;
    }


    public FeudalPlayer takeExperience(int value) {

        experience -= value;

        ScoreBoardInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " опыта атрибутов");

        return this;
    }

    public FeudalPlayer takeBalance(int value) {

        balance -= value;

        ScoreBoardInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " денег");

        return this;
    }

    public FeudalPlayer takeDeaths(int value) {
        deaths -= value;
        return this;
    }

    public FeudalPlayer takeKills(int value) {
        kills -= value;
        return this;
    }

    public FeudalPlayer takeStrengthLvl(int value) {
        strengthLvl -= value;
        return this;
    }

    public FeudalPlayer takeSurvivabilityLvl(int value) {
        survivabilityLvl -= value;
        return this;
    }

    public FeudalPlayer takeSpeedLvl(int value) {
        speedLvl -= value;
        return this;
    }

    public FeudalPlayer takeStaminaLvl(int value) {
        staminaLvl -= value;
        return this;
    }

    public FeudalPlayer takeLuckLvl(int value) {
        luckLvl -= value;
        return this;
    }

    public FeudalPlayer takeGameClassLvl(int value) {
        gameClassLvl -= value;
        return this;
    }

    public FeudalPlayer takeGameClassExperience(int value) {

        gameClassExperience -= value;

        ScoreBoardInfo.updateScoreBoardInfo(player);
        player.sendMessage("-" + value + " опыта класса");

        return this;
    }

    public FeudalPlayer addInvitations(String kingdomName) {
        invitations.add(kingdomName);
        return this;
    }

    public FeudalPlayer clearInvitations() {
        invitations.clear();
        return this;
    }

    public FeudalPlayer deleteInvitations(String kingdomName) {
        invitations.remove(kingdomName);
        return this;
    }

    public FeudalPlayer setLetters(List<String> letters) {
        playerLetters = letters;
        return this;
    }

    public FeudalPlayer addLetter(String letter) {
        playerLetters.add(letter);
        return this;
    }

    public FeudalPlayer clearLetters() {
        playerLetters.clear();
        return this;
    }

    public FeudalPlayer deleteLetter(String letter) {
        playerLetters.remove(letter);
        return this;
    }

}
