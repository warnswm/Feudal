package feudal.commands;

import feudal.auction.Auction;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomInfo;
import feudal.data.database.PlayerInfo;
import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.utils.wrappers.ItemStackWrapper;
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

        PlayerInfo playerInfo;
        KingdomInfo kingdomInfo;


        switch (args[0].toLowerCase()) {

            case "changegameclass":
                playerInfo = CachePlayersMap.getPlayerInfo().get(player);
                playerInfo.setaClassID(Integer.parseInt(args[2]));
                break;
            case "givekingdomstats":
                kingdomInfo = CacheKingdomsMap.getKingdomInfo().get(args[1]);
                kingdomInfo.setField(args[1], args[2], kingdomInfo.getField(args[1], args[2]) + args[3]);
                break;
            case "addchunk":
                kingdomInfo = CacheKingdomsMap.getKingdomInfo().get(args[1]);
                kingdomInfo.addTerritory(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(player.getLocation().getChunk())));
                break;
            case "addah":
                if (player.getInventory().getItemInMainHand() == null ||
                        Integer.parseInt(args[1]) > 1_000_000_000) break;
                Auction.addProduct(ItemStackWrapper.itemStackToItemStackWrapper(player.getInventory().getItemInMainHand(), Integer.parseInt(args[1])));
                break;

        }

        return false;
    }
}
