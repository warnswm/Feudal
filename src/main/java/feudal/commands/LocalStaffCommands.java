package feudal.commands;

import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomInfo;
import feudal.data.database.PlayerInfo;
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

        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(player);
        KingdomInfo kingdomInfo = CacheKingdomsMap.getKingdomInfo().get(CacheKingdomsMap.playerInKingdom(player));


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
