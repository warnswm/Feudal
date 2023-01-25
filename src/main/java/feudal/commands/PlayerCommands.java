package feudal.commands;

import feudal.info.CacheKingdoms;
import feudal.info.KingdomInfo;
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

    final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    final KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

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

                if (!checkKingdomName(args[1])) {

                    player.sendMessage("Невозможно создать королевство с таким именем");

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

                createKingdom(args[1], player, banner, members);

                break;
            case "invite":

                if (CacheKingdoms.playerInKingdom(player)) {

                    player.sendMessage("Вы не находитесь в королевстве");

                    break;
                } else if (!CacheKingdoms.getKingdomInfo().get(player).getKing().equals(player.getUniqueId().toString())) {

                    player.sendMessage("Вы не король королевства");

                    break;
                } else if (Bukkit.getPlayer(args[1]) == null) {

                    player.sendMessage("Такой игрок не найден");

                    break;
                }

//                CacheKingdoms.getKingdomInfo().get(player).getMembers().add(Bukkit.getPlayer(args[1]).getUniqueId().toString());

//                player.sendMessage("Игрок доб");

                break;

        }

        return false;
    }
    private boolean checkKingdomName(String kingdomName) {
        //занято ли имя
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
