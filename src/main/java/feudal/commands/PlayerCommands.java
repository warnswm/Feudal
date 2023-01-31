package feudal.commands;

import feudal.databaseAndCache.CacheKingdoms;
import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.KingdomInfo;
import feudal.view.generalMenu.GameClassUpMenu;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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
            case "withdraw": withdrawMoneyFromTheTreasury(kingdomInfo.getPlayerKingdom(player), player, Integer.parseInt(args[1])); break;
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

        createKingdom(kingdomName, player, members);

    }

    private boolean checkKingdomName(@NotNull String kingdomName) {
        return kingdomName.length() <= 16 && kingdomName.length() > 3 && !kingdomName.equalsIgnoreCase("notInTheKingdom");
    }

    private void createKingdom(@NotNull String kingdomName, @NotNull Player player, List<String> members) {

        kingdomInfo.createNewKingdom(kingdomName, player, members, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        kingdomInfo.setKingdomName(kingdomName)
                .setKing((String) kingdomInfo.getField(kingdomName, "king"))
                .setMembers((List<String>) kingdomInfo.getField(kingdomName, "members"))
                .setBarons((List<String>) kingdomInfo.getField(kingdomName, "barons"))
                .setTerritory((List<Chunk>) kingdomInfo.getField(kingdomName, "territory"));

        CacheKingdoms.getKingdomInfo().put(kingdomInfo.getPlayerKingdom(player), kingdomInfo);

    }
    private void withdrawMoneyFromTheTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        if (kingdomName.equalsIgnoreCase("notInTheKingdom")) {

            player.sendMessage("Вы не состоите в королевстве");
            return;

        }

        KingdomInfo kingdom = CacheKingdoms.getKingdomInfo().get(kingdomName);

        if (kingdom.getBalance() < colum) {

            player.sendMessage("В казне недостаточно средств");
            return;

        }

        System.out.println(kingdom.getBalance());

        kingdom.takeBalance(colum);

        CachePlayers.getPlayerInfo().get(player).addBalance(colum);

        System.out.println(kingdom.getBalance());

    }
}
