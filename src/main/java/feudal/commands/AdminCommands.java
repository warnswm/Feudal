package feudal.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) &&
                !sender.hasPermission("feudal.admin") &&
                !args[0].equals("admin")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args[1].toLowerCase().equals("addls")) {
            //add ls permission
        }


        return false;
    }
}
