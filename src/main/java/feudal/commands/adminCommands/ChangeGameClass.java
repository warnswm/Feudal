package feudal.commands.adminCommands;

import feudal.info.PlayerInfoCache;
import feudal.utils.PlayerGameClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeGameClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        PlayerInfoCache playerInfoCache = PlayerGameClass.getPlayerInfo().get(player);

        if (command.getName().equalsIgnoreCase("changeGameClass"))
            playerInfoCache.setaClassID(Integer.parseInt(args[0]));

        return false;
    }
}
