package feudal.commands.adminCommands;

import feudal.info.PlayerInfoDB;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetAPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        PlayerInfoDB playerInfoDB = new PlayerInfoDB("1", "1", "1");

        if (command.getName().equalsIgnoreCase("ResetPlayer"))
            playerInfoDB.resetAPlayer(Bukkit.getPlayer(args[0]));

        return false;
    }
}
