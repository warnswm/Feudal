package feudal.commands;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.visual.menus.GameClassSelectionMenu;
import feudal.visual.menus.GameClassUpMenu;
import feudal.visual.menus.TravelingMerchantMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalStaffCommands implements CommandExecutor {
    private static boolean isLs(CommandSender sender, String[] args) {
        return !(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (isLs(sender, args)) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        FeudalPlayer feudalPlayer;
        FeudalKingdom feudalKingdom;


        switch (args[0].toLowerCase()) {

            case "changegameclass":

                feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1]));
                feudalPlayer.setaClassID(Integer.parseInt(args[2]));

                break;

            case "addchunk":

                feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
                feudalKingdom.addTerritory(player.getLocation().getChunk());

                break;

            case "test":

                GameClassSelectionMenu.openClassSelection(player);
                break;

            case "test1":

                GameClassUpMenu.upgradeGameClass(player);
                break;

            case "test2":

                TravelingMerchantMenu.openTravelingMerchantMenu(player);

                break;

        }

        return false;
    }
}
