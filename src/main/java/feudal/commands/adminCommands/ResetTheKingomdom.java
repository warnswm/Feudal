package feudal.commands.adminCommands;

import feudal.statistics.KingdomStatistics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetTheKingomdom implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        KingdomStatistics kingdomStatistics = new KingdomStatistics("1", "1", "1");

        if (command.getName().equalsIgnoreCase("ResetTheKingomdom"))
            kingdomStatistics.resetTheKingdom(args[0]);

        return false;
    }
}
