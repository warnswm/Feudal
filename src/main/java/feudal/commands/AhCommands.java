package feudal.commands;

import feudal.auction.AuctionMenu;
import feudal.data.cache.CachePlayers;
import feudal.data.database.PlayerInfo;
import feudal.utils.enums.GameClassesIDEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AhCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String @NotNull [] args) {

        Player player = (Player) sender;

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (!args[0].equals("ah") || playerInfo.getAClassID() != GameClassesIDEnum.TRADER.getId()) return false;

        ahCommand(player);

        return false;
    }

    private void ahCommand(@NotNull Player player) {

        AuctionMenu auctionMenu = new AuctionMenu(player);
        auctionMenu.openAuctionMenu(1);

    }
}
