package feudal.commands.adminCommands;

import feudal.statistics.KingdomStatistics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GiveKingdomStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        KingdomStatistics kingdomStatistics = new KingdomStatistics(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        if (command.getName().equalsIgnoreCase("giveKingdomStats"))
            kingdomStatistics.setField(args[0], args[1], kingdomStatistics.getField(args[0], args[1]) + args[2]);

        return false;
    }
}
