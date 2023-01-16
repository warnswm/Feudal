package feudal.commands.adminCommands;

import feudal.statistics.PlayerStatistics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetAPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        PlayerStatistics playerStatistics = new PlayerStatistics("1", "1", "1");

        if (command.getName().equalsIgnoreCase("ResetPlayer"))
            playerStatistics.resetAPlayer(Bukkit.getPlayer(args[0]));

        return false;
    }
}
