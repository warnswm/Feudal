package feudal.commands;

import alterr.command.Command;
import alterr.command.paramter.Param;
import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.visual.ScoreBoardGeneralInfo;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class LocalStaffCommands {

    @Command(names = {"ls feudalplayerint", "ls fpi", "fpi"}, permission = "feudal.ls", playerOnly = true)
    public void feudalPlayerInt(@NotNull Player player, @Param(name = "player") Player target, @Param(name = "method") String methodName, @Param(name = "value") int value) {

        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(target);
            feudalPlayer.getClass().getMethod(methodName, int.class).invoke(feudalPlayer, value);

            player.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            player.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            player.sendMessage("Не верные агрументы!");

        }

        ScoreBoardGeneralInfo.updateScoreBoardInfo(target);

    }

    @Command(names = {"ls feudalplayerstr", "ls fps", "fps"}, permission = "feudal.ls", playerOnly = true)
    public void feudalPlayerString(@NotNull Player player, @Param(name = "player") Player target, @Param(name = "method") String methodName, @Param(name = "value") String value) {

        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(target);
            feudalPlayer.getClass().getMethod(methodName, String.class).invoke(feudalPlayer, value);

            player.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            player.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            player.sendMessage("Не верные агрументы!");

        }

        ScoreBoardGeneralInfo.updateScoreBoardInfo(target);

    }

    @Command(names = {"ls feudalplayer", "ls fp", "fp"}, permission = "feudal.ls", playerOnly = true)
    public void feudalPlayer(@NotNull Player player, @Param(name = "player") Player target, @Param(name = "method") String methodName) {

        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(target);
            feudalPlayer.getClass().getMethod(methodName).invoke(feudalPlayer);

            player.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            player.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            player.sendMessage("Не верные агрументы!");

        }

        ScoreBoardGeneralInfo.updateScoreBoardInfo(target);

    }

    @Command(names = {"ls feudalkingdomint", "ls fki", "fki"}, permission = "feudal.ls", playerOnly = true)
    public void feudalKingdomInt(@NotNull Player player, @Param(name = "kingomName") String target, @Param(name = "method") String methodName, @Param(name = "value") int value) {

        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(target);
            feudalKingdom.getClass().getMethod(methodName, int.class).invoke(feudalKingdom, value);

            player.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            player.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            player.sendMessage("Не верные агрументы!");

        }

    }

    @Command(names = {"ls feudalkingdomstr", "ls fks", "fks"}, permission = "feudal.ls", playerOnly = true)
    public void feudalKingdomString(@NotNull Player player, @Param(name = "kingomName") String target, @Param(name = "method") String methodName, @Param(name = "value") String value) {

        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(target);
            feudalKingdom.getClass().getMethod(methodName, String.class).invoke(feudalKingdom, value);

            player.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            player.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            player.sendMessage("Не верные агрументы!");

        }

    }

    @Command(names = {"ls feudalkingdom", "ls fk", "fk"}, permission = "feudal.ls", playerOnly = true)
    public void feudalKingdom(@NotNull Player player, @Param(name = "kingomName") String target, @Param(name = "method") String methodName, @Param(name = "value") String value) {

        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(target);
            feudalKingdom.getClass().getMethod(methodName).invoke(feudalKingdom);

            player.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            player.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            player.sendMessage("Не верные агрументы!");

        }

    }

    @Command(names = {"ls help", "ls"}, permission = "feudal.ls", playerOnly = true)
    public void help(@NotNull Player player) {

        player.sendMessage("/ls feudalplayerint [nick] [methodName] [int] \n (methodName: setExperience, setProfessionID, setBalance, setDeaths, setKills, setStrengthLvl, setSurvivabilityLvl, setSpeedLvl, setStaminaLvl, setLuckLvl, setProfessionLvl, setProfessionExperience, addExperience, addBalance, addDeaths, addKills, addStrengthLvl, addSurvivabilityLvl, addSpeedLvl, addStaminaLvl, addLuckLvl, addProfessionLvl, addProfessionExperience, takeExperience, takeBalance, takeDeaths, takeKills, takeStrengthLvl, takeSurvivabilityLvl, takeSpeedLvl, takeStaminaLvl, takeLuckLvl, takeProfessionLvl, takeProfessionExperience, addUpProfession, setUpProfession) \n" +
                " \n /ls feudalplayerstr [nick] [methodName] [str] \n (methodName: setKingdomName, addInvitations, deleteInvitations, deleteLetter) \n" +
                " \n /ls feudalplayer [nick] [methodName] \n (methodName: clearInvitations, clearLetters) \n" +
                " \n /ls feudalkingdomint [kingdomName] [methodName] [int] \n (methodName: setBalance, setReputation, addTerritory, addBalance, addReputation, takeTerritory, takeBalance, takeReputation, setMaxNumberMembers, addMaxNumberMembers)" +
                " \n /ls feudalkingdomstr [kingdomName] [methodName] [str] \n (methodName: setKingdomName)" +
                " \n /ls feudalkingdom [kingdomName] [methodName] \n (methodName: takeAllTerritory, clearInvitation)");
    }
}
