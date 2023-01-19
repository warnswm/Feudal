package feudal.commands.adminCommands;

import feudal.view.menu.GameClassUpMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenUpgradeGameClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        GameClassUpMenu gameClassUpMenu = new GameClassUpMenu(player);

        if (command.getName().equalsIgnoreCase("openUpgradeGameClass"))
            gameClassUpMenu.upgradeGameClass();

        return false;
    }
}
