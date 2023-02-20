package feudal.commands;

import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class LocalStaffCommands implements CommandExecutor {
    private static boolean isLs(CommandSender sender, String[] args) {
        return !(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (isLs(sender, args)) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        switch (args[0].toLowerCase()) {

            case "help":

                player.sendMessage("/ls feudalplayerint [nick] [methodName] [int] \n (methodName: setExperience, setProfessionID, setBalance, setDeaths, setKills, setStrengthLvl, setSurvivabilityLvl, setSpeedLvl, setStaminaLvl, setLuckLvl, setProfessionLvl, setProfessionExperience, addExperience, addBalance, addDeaths, addKills, addStrengthLvl, addSurvivabilityLvl, addSpeedLvl, addStaminaLvl, addLuckLvl, addProfessionLvl, addProfessionExperience, takeExperience, takeBalance, takeDeaths, takeKills, takeStrengthLvl, takeSurvivabilityLvl, takeSpeedLvl, takeStaminaLvl, takeLuckLvl, takeProfessionLvl, takeProfessionExperience, addUpProfession, setUpProfession) \n" +
                        " \n /ls feudalplayerstr [nick] [methodName] [str] \n (methodName: setKingdomName, addInvitations, deleteInvitations, deleteLetter) \n" +
                        " \n /ls feudalplayer [nick] [methodName] \n (methodName: clearInvitations, clearLetters) \n" +
                        " \n /ls feudalkingdomint [kingdomName] [methodName] [int] \n (methodName: setBalance, setReputation, addTerritory, addBalance, addReputation, takeTerritory, takePrivateTerritory, takeBalance, takeReputation, setMaxNumberMembers, addMaxNumberMembers)" +
                        " \n /ls feudalkingdomstr [kingdomName] [methodName] [str] \n (methodName: setKingdomName)" +
                        " \n /ls feudalkingdom [kingdomName] [methodName] \n (methodName: takeAllTerritory, takeAllPrivateTerritory, clearInvitation)");

                break;

            case "feudalplayerint":

                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                } else if (args[3].length() > 10) {

                    player.sendMessage("Слишком большое число!");
                    break;

                }

                try {

                    FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1]));
                    feudalPlayer.getClass().getMethod(args[2], int.class).invoke(feudalPlayer, Integer.parseInt(args[3]));

                    player.sendMessage("Метод был выполнен!");

                } catch (NoSuchMethodException e) {

                    player.sendMessage("Метод не найден!");

                } catch (InvocationTargetException | IllegalAccessException e) {

                    player.sendMessage("Не верные агрументы!");

                }

                break;

            case "feudalplayerstr":

                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                }

                try {

                    FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1]));
                    feudalPlayer.getClass().getMethod(args[2], String.class).invoke(feudalPlayer, args[3]);

                    player.sendMessage("Метод был выполнен!");

                } catch (NoSuchMethodException e) {

                    player.sendMessage("Метод не найден!");

                } catch (InvocationTargetException | IllegalAccessException e) {

                    player.sendMessage("Не верные агрументы!");

                }

                break;


            case "feudalplayer":

                if (args.length < 3) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                }

                try {

                    FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1]));
                    feudalPlayer.getClass().getMethod(args[2]).invoke(feudalPlayer);

                    player.sendMessage("Метод был выполнен!");

                } catch (NoSuchMethodException e) {

                    player.sendMessage("Метод не найден!");

                } catch (InvocationTargetException | IllegalAccessException e) {

                    player.sendMessage("Не верные агрументы!");

                }

                break;

            case "feudalkingdomint":

                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

                    player.sendMessage("Королевство не найдено!");
                    break;

                } else if (args[3].length() > 10) {

                    player.sendMessage("Слишком большое число!");
                    break;

                }

                try {

                    FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
                    feudalKingdom.getClass().getMethod(args[2], int.class).invoke(Integer.parseInt(args[3]));

                    player.sendMessage("Метод был выполнен!");

                } catch (NoSuchMethodException e) {

                    player.sendMessage("Метод не найден!");

                } catch (InvocationTargetException | IllegalAccessException e) {

                    player.sendMessage("Не верные агрументы!");

                }

                break;

            case "feudalkingdomstr":

                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

                    player.sendMessage("Королевство не найдено!");
                    break;

                }

                try {

                    FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
                    feudalKingdom.getClass().getMethod(args[2], String.class).invoke(args[3]);

                    player.sendMessage("Метод был выполнен!");

                } catch (NoSuchMethodException e) {

                    player.sendMessage("Метод не найден!");

                } catch (InvocationTargetException | IllegalAccessException e) {

                    player.sendMessage("Не верные агрументы!");

                }

                break;

            case "feudalkingdom":

                if (args.length < 3) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

                    player.sendMessage("Королевство не найдено!");
                    break;

                }

                try {

                    FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
                    feudalKingdom.getClass().getMethod(args[2]).invoke(feudalKingdom);

                    player.sendMessage("Метод был выполнен!");

                } catch (NoSuchMethodException e) {

                    player.sendMessage("Метод не найден!");

                } catch (InvocationTargetException | IllegalAccessException e) {

                    player.sendMessage("Не верные агрументы!");

                }

                break;


            default:
                player.sendMessage("Неизвестная команда!");

        }

        return false;

    }
}
