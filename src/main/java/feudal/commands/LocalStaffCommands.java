package feudal.commands;

import feudal.databaseAndCache.CacheKingdoms;
import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.KingdomInfo;
import feudal.databaseAndCache.PlayerInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalStaffCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);
        KingdomInfo kingdomInfo = CacheKingdoms.getKingdomInfo().get(CacheKingdoms.playerInKingdom(player));


        switch (args[1].toLowerCase()) {

            case "changegameclass":
                playerInfo.setaClassID(Integer.parseInt(args[2]));
                break;
            case "givekingdomstats":
                kingdomInfo.setField(args[1], args[2], kingdomInfo.getField(args[1], args[2]) + args[3]);
                break;
        }

        if (command.getName().equalsIgnoreCase("changeGameClass"))
            playerInfo.setaClassID(Integer.parseInt(args[0]));

        return false;
    }
}
