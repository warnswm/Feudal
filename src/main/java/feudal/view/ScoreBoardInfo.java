package feudal.view;

import feudal.databaseAndCache.CachePlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoardInfo {

    public static void createScoreBoardInfo(Player player) {

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Feudal", "Feudal");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Feudal");

        Score score = objective.getScore("Имя: " + player.getName());
        Score score1 = objective.getScore("Королевство: " + CachePlayers.getPlayerInfo().get(player).getKingdomName());
        Score score2 = objective.getScore("Класс: " + CachePlayers.getPlayerInfo().get(player).getAClassID());
        Score score3 = objective.getScore("Опыта класса: " + CachePlayers.getPlayerInfo().get(player).getGameClassExperience());
        Score score4 = objective.getScore("Опыта атрибутов: " + CachePlayers.getPlayerInfo().get(player).getExperience());
        Score score5 = objective.getScore("Баланс: " + CachePlayers.getPlayerInfo().get(player).getBalance());


        score.setScore(6);
        score1.setScore(5);
        score2.setScore(4);
        score3.setScore(3);
        score4.setScore(2);
        score5.setScore(1);

        player.setScoreboard(scoreboard);
    }
}
