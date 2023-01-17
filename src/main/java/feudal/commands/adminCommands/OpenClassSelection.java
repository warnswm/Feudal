package feudal.commands.adminCommands;

import feudal.view.ClassSelectionMenu;
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

        ClassSelectionMenu classSelectionMenu = new ClassSelectionMenu(player);

        if (command.getName().equalsIgnoreCase("openClassSelection"))
            classSelectionMenu.openClassSelection();

        return false;
    }
}
