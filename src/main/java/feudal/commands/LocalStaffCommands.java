package feudal.commands;

import feudal.auction.Auction;
import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.TabUtils;
import feudal.utils.wrappers.ItemStackWrapper;
import feudal.visual.menus.GameClassSelectionMenu;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LocalStaffCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls")) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        FeudalPlayer feudalPlayer;
        FeudalKingdom feudalKingdom;


        switch (args[0].toLowerCase()) {

            case "changegameclass":

                feudalPlayer = CachePlayersMap.getFeudalPlayer(player);
                feudalPlayer.setaClassID(Integer.parseInt(args[2]));
                break;
            case "addchunk":

                feudalKingdom = CacheKingdomsMap.getKingdomInfo().get(args[1]);
                feudalKingdom.addTerritory(player.getLocation().getChunk());
                break;

            case "addah":

                if (player.getInventory().getItemInMainHand() == null ||
                        Integer.parseInt(args[1]) > 1_000_000_000) break;
                Auction.addProduct(ItemStackWrapper.itemStackToItemStackWrapper(player.getInventory().getItemInMainHand(), Integer.parseInt(args[1])));
                break;

            case "spy":

                if (player.getGameMode().equals(GameMode.SPECTATOR)) {

                    TabUtils.showPlayer(player);

                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);

                    break;

                }

                TabUtils.hidePlayer(player);
                player.setGameMode(GameMode.SPECTATOR);

                if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 0, true, true));

                break;

            case "menu":

                GameClassSelectionMenu gameClassSelectionMenu = new GameClassSelectionMenu(player);
                gameClassSelectionMenu.openClassSelection();

                break;

        }

        return false;
    }
}
