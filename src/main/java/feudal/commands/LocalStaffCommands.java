package feudal.commands;

import feudal.info.CachePlayerInfoBuilder;
import feudal.info.KingdomInfoDB;
import feudal.utils.CachePlayers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LocalStaffCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        CachePlayerInfoBuilder cachePlayerInfoBuilder = CachePlayers.getPlayerInfo().get(player);
        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());


        switch (args[1].toLowerCase()) {

            case "changegameclass":
                cachePlayerInfoBuilder.setaClassID(Integer.parseInt(args[2]));
                break;
            case "givekingdomstats":
                kingdomInfoDB.setField(args[1], args[2], kingdomInfoDB.getField(args[1], args[2]) + args[3]);
                break;
        }

        if (command.getName().equalsIgnoreCase("changeGameClass"))
            cachePlayerInfoBuilder.setaClassID(Integer.parseInt(args[0]));

        return false;
    }
}
