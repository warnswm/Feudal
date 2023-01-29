package feudal.commands;

import feudal.auction.AuctionMenu;
import feudal.databaseAndCache.CacheKingdoms;
import feudal.databaseAndCache.KingdomInfo;
import feudal.utils.CreateItemUtil;
import feudal.view.generalMenu.GameClassUpMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCommands implements CommandExecutor {

    final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    final KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        switch (args[0]) {
            case "help": helpCommand(player); break;
            case "create": createKingdomCommand(player, args[1]); break;
            case "ah": ahCommand(player); break;
            case "gameclassupmenu":
                GameClassUpMenu gameClassUpMenu = new GameClassUpMenu(player);
                gameClassUpMenu.upgradeGameClass();
                break;
        }

        return false;
    }
    private void helpCommand(@NotNull Player player) {
        player.sendMessage("/f claim - захватить чанк\n" + "/f create - создать королевство\n" + "/f help - меню коамнд\n" + "/f invite - добавить игрока в королевство\n" + "/f kick - удалить игрока из королевства\n" + "/f m - меню королевства\n" + "/f map - soon\n" + "/f shield - soon\n" + "/f location - указать локацию королевства\n" + "/f ah - открыть аукцион\n");
    }

    private void createKingdomCommand(@NotNull Player player, String kingdomName) {

        if (!checkKingdomName(kingdomName))
            player.sendMessage("Невозможно создать королевство с таким именем");

        List<String> members = new ArrayList<>();
        members.add(player.getUniqueId().toString());
        ItemStack banner;


        if (player.getInventory().getItemInMainHand().getType().equals(Material.BANNER)) {

            ItemStack itemStack = player.getInventory().getItemInMainHand();
            itemStack.getItemMeta().setDisplayName("Флаг королевства '" + kingdomName + "'");

            banner = itemStack;

        } else banner = CreateItemUtil.createItem(Material.BANNER, 1, "Флаг королевства '" + kingdomName + "'");

        createKingdom(kingdomName, player, banner, members);

    }

    private void ahCommand(@NotNull Player player) {

        AuctionMenu auctionMenu = new AuctionMenu(player);
        auctionMenu.openAuctionMenu(1);

    }

    private boolean checkKingdomName(@NotNull String kingdomName) {
        return kingdomName.length() <= 16 && kingdomName.length() > 3 && !kingdomName.equalsIgnoreCase("notInTheKingdom");
    }

    private void createKingdom(String kingdomName, Player player, ItemStack banner, List<String> members) {

        kingdomInfo.createNewKingdom(kingdomName, player, members, Collections.EMPTY_LIST, Collections.EMPTY_LIST, banner);
        kingdomInfo.setKingdomName(kingdomName)
                .setKing(player.getUniqueId().toString())
                .setBanner(banner.toString())
                .setMembers(members)
                .setBarons(Collections.EMPTY_LIST)
                .setTerritory(Collections.EMPTY_LIST);

        CacheKingdoms.getKingdomInfo().put(kingdomInfo.getPlayerKingdom(player), kingdomInfo);
    }
}
