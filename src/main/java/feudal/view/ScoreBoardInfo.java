package feudal.view;

import feudal.databaseAndCache.CachePlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class ScoreBoardInfo {

    public static void createScoreBoardInfo(@NotNull Player player) {

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Feudal", "Feudal");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Feudal");

        Score name = objective.getScore("Имя: " + player.getName());
        Score kingdom = objective.getScore("Королевство: " + CachePlayers.getPlayerInfo().get(player).getKingdomName());
        Score gameClass = objective.getScore("Класс: " + CachePlayers.getPlayerInfo().get(player).getAClassID());
        Score classExp = objective.getScore("Опыта класса: " + CachePlayers.getPlayerInfo().get(player).getGameClassExperience());
        Score attributeExp = objective.getScore("Опыта атрибутов: " + CachePlayers.getPlayerInfo().get(player).getExperience());
        Score balance = objective.getScore("Баланс: " + CachePlayers.getPlayerInfo().get(player).getBalance());


        name.setScore(7);
        kingdom.setScore(6);
        gameClass.setScore(5);
        classExp.setScore(4);
        attributeExp.setScore(3);
        balance.setScore(2);

        player.setScoreboard(scoreboard);
    }
}
