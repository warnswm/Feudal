package feudal.view;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
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

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

        Score name = objective.getScore("Имя: " + player.getName());
        Score kingdom = objective.getScore("Королевство: " + feudalPlayer.getKingdomName());
        Score gameClass = objective.getScore("Класс: " + feudalPlayer.getAClassID());
        Score classExp = objective.getScore("Опыта класса: " + feudalPlayer.getGameClassExperience());
        Score attributeExp = objective.getScore("Опыта атрибутов: " + feudalPlayer.getExperience());
        Score balance = objective.getScore("Баланс: " + feudalPlayer.getBalance());


        name.setScore(7);
        kingdom.setScore(6);
        gameClass.setScore(5);
        classExp.setScore(4);
        attributeExp.setScore(3);
        balance.setScore(2);

        player.setScoreboard(scoreboard);
    }
}
