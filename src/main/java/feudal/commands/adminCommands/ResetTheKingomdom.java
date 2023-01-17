package feudal.commands.adminCommands;

import feudal.info.KingdomInfoDB;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ResetTheKingomdom implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        if (command.getName().equalsIgnoreCase("ResetTheKingomdom"))
            kingdomInfoDB.resetTheKingdom(args[0]);

        return false;
    }
}
