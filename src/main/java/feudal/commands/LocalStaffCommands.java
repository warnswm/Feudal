package feudal.commands;

import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
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

        FeudalPlayer feudalPlayer;
        FeudalKingdom feudalKingdom;

        switch (args[0].toLowerCase()) {

            case "help":

                player.sendMessage("/ls feudalplayer [nick] [methodName] [argument] \n (methodName: setExperience, setProfessionID, setBalance, setDeaths, setKills, setStrengthLvl, setSurvivabilityLvl, setSpeedLvl, setStaminaLvl, setLuckLvl, setProfessionLvl, setProfessionExperience, addExperience, addBalance, addDeaths, addKills, addStrengthLvl, addSurvivabilityLvl, addSpeedLvl, addStaminaLvl, addLuckLvl, addProfessionLvl, addProfessionExperience, takeExperience, takeBalance, takeDeaths, takeKills, takeStrengthLvl, takeSurvivabilityLvl, takeSpeedLvl, takeStaminaLvl, takeLuckLvl, takeProfessionLvl, takeProfessionExperience, addUpProfession, setUpProfession)");

                break;

            case "feudalplayer":

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

                    CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1])).getClass().getMethod(args[2], Integer.class).invoke(Integer.parseInt(args[3]));

                } catch (NoSuchMethodException e) {

                    player.sendMessage("Метод не найден!");

                } catch (InvocationTargetException | IllegalAccessException e) {

                    player.sendMessage("Не верные агрументы!");

                }

                break;

            case "sendletter":

                if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                }

                String[] text = new String[args.length - 2];
                System.arraycopy(args, 2, text, 0, args.length - 2);

                sendLetter(Bukkit.getPlayerExact(args[1]), text);

                break;

            default:
                player.sendMessage("Неизвестная команда!");

        }

        return false;

    }

    private void sendLetter(Player player, String[] text) {
        CacheFeudalPlayers.getFeudalPlayer(player).addLetter(player.getName(), String.join(" ", text));
    }
}
