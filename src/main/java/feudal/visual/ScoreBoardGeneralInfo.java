package feudal.visual;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gcEnums.GameClassesIDE;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoardGeneralInfo {

    public static void updateScoreBoardInfo(Player player) {

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Feudal", "Feudal");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Feudal");

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        Score name = objective.getScore("Имя: " + player.getName());
        Score kingdom = objective.getScore("Королевство: " + feudalPlayer.getKingdomName());
        Score gameClass = objective.getScore("Класс: " + GameClassesIDE.getNameByID(feudalPlayer.getAClassID()));
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
