package feudal.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        switch (args[0]) {
            case "help":
                player.sendMessage("/f claim - захватить чанк\n" +
                        "/f create - создать королевство\n" +
                        "/f help - меню коамнд\n" +
                        "/f invite - добавить игрока в королевство\n" +
                        "/f kick - удалить игрока из королевства\n" +
                        "/f m - меню королевства\n" +
                        "/f map - карта местности\n" +
                        "/f shield - ХУЙ ЕГО ЗНАЕТ, ШЕПАРД НЕ НАПИСАЛ\n" +
                        "/f location - указать локацию королевства\n" +
                        "/ah - открыть аукцион\n");

                break;
            case "create":

                if (args[1].length() > 16 || args[1].equalsIgnoreCase("notInTheKingdom")) {

                    player.sendMessage("Слишком длинное или недопустимое название");

                    break;
                }







                break;
            case "location":



        }

        return false;
    }
}
