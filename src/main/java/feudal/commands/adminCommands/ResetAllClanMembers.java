package feudal.commands.adminCommands;

import feudal.info.KingdomInfoDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetAllClanMembers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        KingdomInfoDB kingdomInfoDB = new KingdomInfoDB("1", "1", "1");

        if (command.getName().equalsIgnoreCase("ResetAllClanMembers"))
            kingdomInfoDB.resetAllClanMembers(args[0]);

        return false;
    }
}
