package feudal.commands.adminCommands;

import feudal.info.PlayerInfoDB;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GivePlayerStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        if (command.getName().equalsIgnoreCase("givePlayerStats"))
            playerInfoDB.setField(Bukkit.getPlayer(args[0]), args[1], playerInfoDB.getField(Bukkit.getPlayer(args[0]), args[1]));

        return false;
    }
}
