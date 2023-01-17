package feudal.commands.adminCommands;

import feudal.info.PlayerInfoDB;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ResetAPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        if (command.getName().equalsIgnoreCase("ResetPlayer"))
            playerInfoDB.resetAPlayer(Bukkit.getPlayer(args[0]));

        return false;
    }
}
