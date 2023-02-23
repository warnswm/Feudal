package feudal.commands;

import org.bukkit.Bukkit;
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

        switch (args[0].toLowerCase()) {

            case "addls":

                //add ls
                break;

            case "broadcast":

                Bukkit.broadcastMessage(args[1]);
                break;

        }

        return false;

    }
}
