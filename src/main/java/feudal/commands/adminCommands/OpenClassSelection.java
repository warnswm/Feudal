package feudal.commands.adminCommands;

import feudal.view.menu.GameClassSelectionMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenClassSelection implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        GameClassSelectionMenu gameClassSelectionMenu = new GameClassSelectionMenu(player);

        if (command.getName().equalsIgnoreCase("openClassSelection"))
            gameClassSelectionMenu.openClassSelection();

        return false;
    }
}
