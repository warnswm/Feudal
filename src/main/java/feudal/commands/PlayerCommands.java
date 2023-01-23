package feudal.commands;

import feudal.utils.CreateItemUtil;
import feudal.view.menu.CreateKingdomMenu;
import feudal.view.menu.GameClassSelectionMenu;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerCommands implements CommandExecutor {
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
                        "/ah - открыть аукцион\n");

//                ScoreBoardInfo.createScoreBoardInfo(player);
                break;
            case "create":

                if (args[1].length() > 16 || args[1].equalsIgnoreCase("notInTheKingdom")) {

                    player.sendMessage("Слишком длинное или недопустимое название");

                    break;
                }

                CreateKingdomMenu createKingdomMenu = new CreateKingdomMenu(player);
                createKingdomMenu.openCreateKingdomMenu();

                createKingdomMenu.setSlotItem(1, CreateItemUtil.createItem(Material.NAME_TAG, 1, args[1]));

                if (player.getItemInHand().getType().equals(Material.BANNER)){

                    ItemStack itemStack = player.getItemInHand();
                    ItemMeta itemMeta = player.getItemInHand().getItemMeta();
                    itemMeta.setDisplayName("Флаг королевства " + args[1]);
                    itemStack.setItemMeta(itemMeta);

                    createKingdomMenu.setSlotItem(4, itemStack);
                }
                else
                    createKingdomMenu.setSlotItem(4, CreateItemUtil.createItem(Material.BANNER, 1, "Флаг королевства " + args[1]));

                break;
            case "выборклассанахуйбля":

                GameClassSelectionMenu gameClassSelectionMenu = new GameClassSelectionMenu(player);
                gameClassSelectionMenu.openClassSelection();

                break;
        }

        return false;
    }
}
