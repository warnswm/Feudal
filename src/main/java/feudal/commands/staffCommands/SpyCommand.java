package feudal.commands.staffCommands;

import feudal.utils.TabUtils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !command.getName().equalsIgnoreCase("spy") && !sender.hasPermission("feudal.staff"))
            return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        TabUtils tabUtils = new TabUtils(player);
        tabUtils.hidePlayer();

        player.setGameMode(GameMode.SPECTATOR);

        return false;
    }
}
