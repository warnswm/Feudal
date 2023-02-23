package feudal.visual;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoardGeneralInfo {

    public static void createScoreBoard(Player player) {


        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Feudal", "Feudal");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Feudal");

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        String kingdomName = feudalPlayer.getKingdomName().equals("") ? "N/A" : feudalPlayer.getKingdomName();

        Score playerScore = objective.getScore("§bИгрок");
        Score kingdom = objective.getScore("Королевство ➡ " + kingdomName);
        Score profession = objective.getScore("Профессия ➡ §a" + ProfessionIDE.getNameByID(feudalPlayer.getProfessionID()));
        Score professionExp = objective.getScore("Уровень класса ➡ " + feudalPlayer.getProfessionLvl());
        Score attributeExp = objective.getScore("Опыт атрибутов ➡ " + feudalPlayer.getExperience());
        Score air = objective.getScore("");
        Score playerStatistics = objective.getScore("§bСтатистика");
        Score balance = objective.getScore("Золота ➡ " + feudalPlayer.getBalance());


        playerScore.setScore(7);
        kingdom.setScore(6);
        profession.setScore(5);
        professionExp.setScore(4);
        attributeExp.setScore(3);
        air.setScore(2);
        playerStatistics.setScore(1);
        balance.setScore(0);

        player.setScoreboard(scoreboard);

    }

    public static void updateScoreBoardInfo(Player player) {

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Feudal", "Feudal");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Feudal");

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        String kingdomName = feudalPlayer.getKingdomName().equals("") ? "N/A" : feudalPlayer.getKingdomName();

        scoreboard.resetScores("Королевство ➡ " + kingdomName);
        scoreboard.resetScores("Профессия ➡ §a" + ProfessionIDE.getNameByID(feudalPlayer.getProfessionID()));
        scoreboard.resetScores("Уровень класса ➡ " + feudalPlayer.getProfessionLvl());
        scoreboard.resetScores("Опыт атрибутов ➡ " + feudalPlayer.getExperience());
        scoreboard.resetScores("Золота ➡ " + feudalPlayer.getBalance());

    }
}
