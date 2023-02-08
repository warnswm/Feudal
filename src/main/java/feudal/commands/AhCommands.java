package feudal.commands;

import feudal.auction.AuctionMenu;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AhCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String @NotNull [] args) {

        Player player = (Player) sender;

        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

        if (!args[0].equals("ah") || feudalPlayer.getAClassID() != GameClassesIDEnum.TRADER.getId()) return false;

        ahCommand(player);

        return false;
    }

    private void ahCommand(@NotNull Player player) {

        AuctionMenu auctionMenu = new AuctionMenu(player);
        auctionMenu.openAuctionMenu(1);

    }
}
