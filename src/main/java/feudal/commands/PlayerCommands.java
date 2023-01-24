package feudal.commands;

import feudal.info.CacheKingdomInfoBuilder;
import feudal.info.KingdomInfoDB;
import feudal.utils.CacheKingdoms;
import feudal.utils.CreateItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        final KingdomInfoDB kingdomInfoDB = new KingdomInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
        Player player = (Player) sender;

        switch (args[0]) {
            case "help":
                player.sendMessage("/f claim - захватить чанк\n" +
                        "/f create - создать королевство\n" +
                        "/f help - меню коамнд\n" +
                        "/f invite - добавить игрока в королевство\n" +
                        "/f kick - удалить игрока из королевства\n" +
                        "/f m - меню королевства\n" +
                        "/f map - карта местности\n" +
                        "/f shield - ХУЙ ЕГО ЗНАЕТ, ШЕПАРД НЕ НАПИСАЛ\n" +
                        "/f location - указать локацию королевства\n" +
                        "/ah - открыть аукцион\n");

                break;
            case "create":

                if (args[1].length() > 16 || args[1].equalsIgnoreCase("notInTheKingdom")) {

                    player.sendMessage("Слишком длинное или недопустимое название");

                    break;
                }

                List<String> members = new ArrayList<>();
                members.add(player.getUniqueId().toString());
                ItemStack banner;

                if (player.getItemInHand().getType().equals(Material.BANNER)) {

                    ItemStack itemStack = player.getItemInHand();
                    ItemMeta itemMeta = player.getItemInHand().getItemMeta();
                    itemMeta.setDisplayName("Флаг королевства '" + args[1] + "'");
                    itemStack.setItemMeta(itemMeta);

                    banner = itemStack;

                } else banner = CreateItemUtil.createItem(Material.BANNER, 1, "Флаг королевства '" + args[1] + "'");


                kingdomInfoDB.createNewKingdom(args[1], player, members, Collections.EMPTY_LIST, Collections.EMPTY_LIST, banner);

                CacheKingdomInfoBuilder cacheKingdomInfoBuilder = new CacheKingdomInfoBuilder()
                        .setKingdomName(args[1])
                        .setKing(player.getUniqueId().toString())
                        .setBanner(banner.toString())
                        .setMembers(members)
                        .setBarons(Collections.EMPTY_LIST)
                        .setTerritory(Collections.EMPTY_LIST);


                CacheKingdoms.getKingdomInfo().put(kingdomInfoDB.getPlayerKingdom(player), cacheKingdomInfoBuilder);

                break;
            case "location":


        }

        return false;
    }
}
